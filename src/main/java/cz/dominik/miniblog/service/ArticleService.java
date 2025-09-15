package cz.dominik.miniblog.service;

import cz.dominik.miniblog.dto.ArticleForm;
import cz.dominik.miniblog.exception.NotFoundException;
import cz.dominik.miniblog.model.Article;
import cz.dominik.miniblog.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article", id));
    }

    public Article create(ArticleForm form) {
        Article a = new Article();
        a.setTitle(form.getTitle());
        a.setContent(form.getContent());
        return articleRepository.save(a);
    }

    public Article update(Long id, ArticleForm form) {
        Article a = findById(id);
        a.setTitle(form.getTitle());
        a.setContent(form.getContent());
        return articleRepository.save(a);
    }

    public void delete(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        }
    }
}