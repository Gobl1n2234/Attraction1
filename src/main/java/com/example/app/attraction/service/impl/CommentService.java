package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.CommentDTO;
import com.example.app.attraction.dto.facade.CommentFacade;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Comment;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.CommentRepository;
import com.example.app.attraction.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final AttractionRepository attractionRepository;

    public CommentService(CommentRepository commentRepository, AttractionRepository attractionRepository) {
        this.commentRepository = commentRepository;
        this.attractionRepository = attractionRepository;
    }

    @Override
    public Comment add(String attractionName, CommentDTO commentdto) {
        Attraction attraction = attractionRepository.findByName(attractionName);

        Comment comment = new Comment();
        comment.setAttraction(attraction);
        comment.setText(commentdto.getText());
        comment.setUserId(commentdto.getUserId());

        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> getAllByAttraction(String name) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        List<Comment> commentsList = commentRepository.findAllByAttraction_Name(name);
        for (Comment comment: commentsList
        ) {
            commentDTOS.add(CommentFacade.commentToDto(comment));
        }
        return commentDTOS;
    }

    @Override
    public void delete(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.ifPresent(commentRepository::delete);
    }
}
