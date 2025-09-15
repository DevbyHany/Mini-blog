package cz.dominik.miniblog.controller;

import cz.dominik.miniblog.dto.ArticleForm;
import cz.dominik.miniblog.model.Article;
import cz.dominik.miniblog.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/articles")
public class ArticlePageController {

    private final ArticleService articleService;

    public ArticlePageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // DETAIL — GET /articles/{id}
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Article a = articleService.findById(id);
        model.addAttribute("article", a);
        return "articles/detail";
    }

    // LIST — GET /articles  (pagination)
    @GetMapping
    public String list(Model model,
                       @PageableDefault(size = 10, sort = {"createdAt", "id"},
                               direction = Sort.Direction.DESC)
                       Pageable pageable) {
        Page<Article> page = articleService.findAll(pageable);
        model.addAttribute("page", page);
        return "articles/list";
    }


    // NEW FORM — GET /articles/new
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new ArticleForm());
        return "articles/new";
    }

    // CREATE — POST /articles
    @PostMapping
    public String create(@Valid @ModelAttribute("form") ArticleForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("globalError", "Zkontroluj zvýrazněná pole.");
            return "articles/new";
        }
        Article saved = articleService.create(form);
        ra.addFlashAttribute("success", "Článek byl úspěšně vytvořen.");
        return "redirect:/articles/" + saved.getId();
    }

    // EDIT FORM — GET /articles/{id}/edit
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Article a = articleService.findById(id);
        ArticleForm form = new ArticleForm();
        form.setTitle(a.getTitle());
        form.setContent(a.getContent());
        model.addAttribute("form", form);
        model.addAttribute("id", id);
        return "articles/edit";
    }

    // UPDATE — POST /articles/{id}/edit
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("form") ArticleForm form,
                         BindingResult bindingResult,
                         RedirectAttributes ra,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            return "articles/edit";
        }
        articleService.update(id, form);
        ra.addFlashAttribute("success", "Změny byly uloženy.");
        return "redirect:/articles/" + id;
    }

    // DELETE — POST /articles/{id}/delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        articleService.delete(id);
        ra.addFlashAttribute("success", "Článek byl smazán.");
        return "redirect:/articles";
    }
}