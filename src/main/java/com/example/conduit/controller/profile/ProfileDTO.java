package com.example.conduit.controller.profile;

import java.net.URL;

public record ProfileDTO(
        String username,
        String bio,
        URL image,
        boolean following
) {
}
