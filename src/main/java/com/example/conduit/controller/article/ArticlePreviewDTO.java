package com.example.conduit.controller.article;

import com.example.conduit.controller.profile.ProfileDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ArticlePreviewDTO(
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount,
        ProfileDTO author
) {
}
