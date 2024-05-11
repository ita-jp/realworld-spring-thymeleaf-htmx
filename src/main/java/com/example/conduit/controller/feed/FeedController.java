package com.example.conduit.controller.feed;

import com.example.conduit.controller.article.ArticlePreviewDTO;
import com.example.conduit.controller.profile.ProfileDTO;
import com.example.conduit.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedController {

    private final ArticleService articleService;

    @GetMapping ("/global-feed")
    public String globalFeed(Model model) {
        var as = articleService.findAll();
        model.addAttribute("articleList", as);

        return "article/article-preview";
    }

    @GetMapping("/your-feed")
    public String yourFeed(Model model) {

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
                )
        );

        model.addAttribute("articleList", mockList);

        return "article/article-preview";
    }
}
