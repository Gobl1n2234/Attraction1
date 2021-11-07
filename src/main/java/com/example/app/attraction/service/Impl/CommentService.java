package com.example.app.attraction.service.Impl;

import com.example.app.attraction.entity.Comment;
import com.example.app.attraction.repository.CommentRepository;
import com.example.app.attraction.service.ICommentService;

public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
