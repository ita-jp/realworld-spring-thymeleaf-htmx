package com.example.conduit.controller.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagController {

    @GetMapping("/popular-tags")
    public String list(Model model) {
        var tagList = List.of("reactjs", "angularjs", "dragons", "baseball", "ruby");
        model.addAttribute("tagList", tagList);
        return "popular-tags";
    }
}
