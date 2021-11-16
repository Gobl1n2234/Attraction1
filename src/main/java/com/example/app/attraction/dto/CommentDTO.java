package com.example.app.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDTO {
    private final Long id;
    private final Long userId;
    private final String text;

}
