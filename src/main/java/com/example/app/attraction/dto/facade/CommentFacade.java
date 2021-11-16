package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.CommentDTO;
import com.example.app.attraction.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {

    public CommentDTO commentToDto(Comment comment){
        return new CommentDTO(comment.getId(), comment.getId(), comment.getText());
    }
}
