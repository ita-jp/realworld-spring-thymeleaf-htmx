package com.example.conduit.controller.user;

import com.example.conduit.security.CustomUserDetails;
import com.example.conduit.security.CustomUserDetailsService;
import com.example.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping
    public String register(Form form) {
        userService.register(form.email, form.password, form.username);
        return "redirect:/login";
    }

    @GetMapping("/me")
    public String showMe(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        userService.findByEmail(principal.getName())
                .ifPresentOrElse(
                        user -> model.addAttribute("user", user),
                        () -> {
                            throw new IllegalStateException("User not found");
                        }
                );
        return "setting";
    }

    @PutMapping("/me")
    public String updateMe(UpdateForm form, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.update(userDetails.getId(), form.email, form.password, form.username, form.bio, form.image);

        var updatedUserDetails = customUserDetailsService.loadUserByUsername(form.email);
        var newAuth = UsernamePasswordAuthenticationToken.authenticated(
                updatedUserDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/";
    }

    public record Form(String email, String password, String username) {
    }

    public record UpdateForm(String email, String password, String username, String bio, String image) {
    }
}
