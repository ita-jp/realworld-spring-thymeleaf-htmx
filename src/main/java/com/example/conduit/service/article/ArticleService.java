package com.example.conduit.service.article;

import com.example.conduit.repository.ArticleRepository;
import com.example.conduit.service.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article create(User author, String title, String description, String body, List<String> tagList) {
        var newArticle = new Article(
                null,
                title,
                description,
                body,
                author,
                List.of() // TODO tag
        );
        return articleRepository.save(newArticle);
    }
}
