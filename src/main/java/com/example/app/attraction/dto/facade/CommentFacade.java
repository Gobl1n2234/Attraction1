package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.CommentDTO;
import com.example.app.attraction.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {

    public CommentDTO toDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setDate(comment.getDate());
        commentDTO.setText(comment.getText());
        commentDTO.setUserId(comment.getUserId());

        return commentDTO;
    }
}
