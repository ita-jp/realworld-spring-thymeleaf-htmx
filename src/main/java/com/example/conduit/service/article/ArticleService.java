package com.example.conduit.service.article;

import com.example.conduit.repository.ArticleRepository;
import com.example.conduit.service.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article create(User author, String title, String description, String body, List<String> tagList) {
        var newArticle = new Article(
                null,
                UUID.randomUUID().toString(),
                title,
                description,
                body,
                LocalDateTime.now(),
                LocalDateTime.now(),
                author,
                List.of(), // TODO tag
                0L
        );
        return articleRepository.save(newArticle);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
