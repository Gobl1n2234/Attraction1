package com.example.app.attraction.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private Long userId;
    private LocalDateTime date;
    private String text;
}
