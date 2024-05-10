package com.example.conduit.controller.article;

import com.example.conduit.controller.profile.ProfileDTO;
import com.example.conduit.security.CustomUserDetails;
import com.example.conduit.service.article.ArticleService;
import com.example.conduit.service.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{slug}")
    public String article(@PathVariable("slug") String slug) {
        return "article";
    }

    @GetMapping
    public String list(Model model) throws URISyntaxException, MalformedURLException {
        var articles = articleService.findAll()
                .stream().map(article -> new ArticlePreviewDTO(
                        "slug",
                        article.getTitle(),
                        article.getDescription(),
                        article.getBody(),
                        article.getTagList().stream().map(Tag::getName).toList(),
                        article.getCreatedAt(),
                        article.getUpdatedAt(),
                        false,
                        0,
                        new ProfileDTO(
                                article.getAuthor().getUsername(),
                                null,
                                article.getAuthor().getImage(),
                                false
                        ))
                )
                .toList();

        var mockList = List.of(
                new ArticlePreviewDTO(
                        "how-to-train-your-dragon",
                        "Ever wonder how?",
                        "You have to believe",
                        "body",
                        List.of("training", "dragons"),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        false,
                        29,
                        new ProfileDTO(
                                "Eric Simons",
                                null,
                                "http://i.imgur.com/Qr71crq.jpg",
                                false
                        )
                ),
                new ArticlePreviewDTO(
                        "how-to-train-your-dragon",
                        "Ever wonder how?",
                        "You have to believe",
                        "body",
                        List.of("training", "dragons"),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        false,
                        29,
                        new ProfileDTO(
                                "Eric Simons",
                                null,
                                "http://i.imgur.com/Qr71crq.jpg",
                                false
                        )
                ),
                new ArticlePreviewDTO(
                        "how-to-train-your-dragon",
                        "Ever wonder how?",
                        "You have to believe",
                        "body",
                        List.of("training", "dragons"),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        false,
                        29,
                        new ProfileDTO(
                                "Eric Simons",
                                null,
                                "http://i.imgur.com/Qr71crq.jpg",
                                false
                        )
                )
        );

        var articleList = new LinkedList<>();
        articleList.addAll(articles);
        articleList.addAll(mockList);
        model.addAttribute("articleList", articleList);

        return "article/article-preview";
    }

    @PostMapping
    public String create(
            @Validated NewForm form,
            BindingResult bindingResult,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/editor";
        }

        articleService.create(
                customUserDetails.user(),
                form.title(),
                form.description(),
                form.body(),
                form.tagList()
        );

        return "redirect:/";
    }

    public record NewForm(
            String title,
            String description,
            String body,
            List<String> tagList
    ) {
    }
}
