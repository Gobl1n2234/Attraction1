package com.example.app.attraction.service;

import com.example.app.attraction.dto.CommentDTO;
import com.example.app.attraction.entity.Comment;

import java.util.List;

public interface ICommentService {
    //Добавление отзыва достопремечательности
    Comment add(String attractionName, CommentDTO commentDTO);

    List<CommentDTO> getAllByAttraction(String name);

    //Удаление отзыва
    void delete(Long commentId);
}
