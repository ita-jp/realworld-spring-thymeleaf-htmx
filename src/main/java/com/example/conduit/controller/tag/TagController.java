package com.example.conduit.controller.tag;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.List;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final SpringTemplateEngine templateEngine;


    @PostConstruct
    private void configureTemplateEngine() {
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(TemplateMode.HTML);
        stringTemplateResolver.setCacheable(false);

        templateEngine.addTemplateResolver(stringTemplateResolver);
    }

    @GetMapping
    @ResponseBody
    public String list(Model model) {
        var tagList = List.of("reactjs", "angularjs", "dragons", "baseball", "ruby");
        model.addAttribute("tagList", tagList);

        // Prepare the HTML content
        String htmlContent = """
               <div class="sidebar">
                   <p>Popular Tags</p>
               
                   <div class="tag-list" >
                       <a th:each="tag : ${tagList}" href="#" class="tag-pill tag-default" th:text="${tag}"></a>
                   </div>
               </div>
               """;

        var context = new Context();
        context.setVariable("tagList", tagList);
        return templateEngine.process(htmlContent, context);
    }
}
