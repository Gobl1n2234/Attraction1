package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.CommentDTO;
import com.example.app.attraction.entity.Comment;



public class CommentFacade {

    public static CommentDTO commentToDto(Comment comment){
        return new CommentDTO(comment.getId(), comment.getId(), comment.getText());
    }
}
