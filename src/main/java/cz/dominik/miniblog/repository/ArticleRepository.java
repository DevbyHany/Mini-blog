package cz.dominik.miniblog.repository;

import cz.dominik.miniblog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByTitleContainingIgnoreCase(String q, Pageable pageable);
    Page<Article> findAll(Pageable pageable);
}
