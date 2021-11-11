package com.example.app.attraction.service;

import com.example.app.attraction.entity.Comment;

public interface ICommentService {
    //Добавление отзыва достопремечательности
    void add(Comment comment);

    //Редактирование отзыва
    void update(Comment comment);

    //Удаление отзыва
    void delete(Comment comment);
}
