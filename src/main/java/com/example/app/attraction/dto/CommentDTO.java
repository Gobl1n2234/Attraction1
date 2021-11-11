package com.example.app.attraction.dto;

import com.example.app.attraction.entity.Comment;
import lombok.Data;



@Data
public class CommentDTO {
    private Long id;
    private Long userId;
    private String text;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.text = comment.getText();
    }

    public CommentDTO() {
    }
}
