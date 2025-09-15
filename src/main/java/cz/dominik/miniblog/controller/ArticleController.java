package cz.dominik.miniblog.controller;

import cz.dominik.miniblog.dto.ArticleForm;
import cz.dominik.miniblog.exception.NotFoundException;
import cz.dominik.miniblog.model.Article;
import cz.dominik.miniblog.repository.ArticleRepository;
import cz.dominik.miniblog.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // List@GetMapping
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    // Detail
    @GetMapping("/{id}")
    public Article findOne(@PathVariable Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article", id));
    }

    // Find by title
    @GetMapping("/search")
    public Page<Article> search(
            @RequestParam String q,
            @PageableDefault(size = 5, sort = "createdAt", direction = org.springframework.data.domain.Sort.Direction.DESC)
            Pageable pageable
    ) {
        return articleRepository.findByTitleContainingIgnoreCase(q, pageable);
    }

    @PostMapping("/articles")
    public String create(@Valid @ModelAttribute("form") ArticleForm form,
                         BindingResult br,
                         RedirectAttributes ra) {
        if (br.hasErrors()) return "new";
        Article saved = articleService.create(form);
        ra.addFlashAttribute("success", "Článek byl úspěšně vytvořen.");
        return "redirect:/articles/" + saved.getId();
    }

    @PostMapping("/articles/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("form") ArticleForm form,
                         BindingResult br,
                         RedirectAttributes ra) {
        if (br.hasErrors()) return "edit";
        articleService.update(id, form);
        ra.addFlashAttribute("success", "Změny byly uloženy.");
        return "redirect:/articles/" + id;
    }

    @PostMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        articleService.delete(id);
        ra.addFlashAttribute("success", "Článek byl smazán.");
        return "redirect:/articles";
    }
}