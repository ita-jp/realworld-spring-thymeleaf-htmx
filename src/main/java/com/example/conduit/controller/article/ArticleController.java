package com.example.conduit.controller.article;

import com.example.conduit.controller.profile.ProfileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("/{slug}")
    public String article(@PathVariable("slug") String slug) {
        return "article";
    }

    @GetMapping
    public String list(Model model) throws URISyntaxException, MalformedURLException {
        model.addAttribute("articleList", List.of(
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
                                new URI("http://i.imgur.com/Qr71crq.jpg").toURL(),
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
                                new URI("http://i.imgur.com/Qr71crq.jpg").toURL(),
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
                                new URI("http://i.imgur.com/Qr71crq.jpg").toURL(),
                                false
                        )
                )
        ));

        return "index :: article-preview";
    }
}
