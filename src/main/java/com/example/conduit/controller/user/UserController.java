package com.example.conduit.controller.user;

import com.example.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public String register(Form form) {
        userService.register(form.email, form.password, form.username);
        return "redirect:/";
    }

    public record Form(String email, String password, String username) {
    }
}
