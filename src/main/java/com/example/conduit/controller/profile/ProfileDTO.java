package com.example.conduit.controller.profile;

public record ProfileDTO(
        String username,
        String bio,
        String image,
        boolean following
) {
}
