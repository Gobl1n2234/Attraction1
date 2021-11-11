package com.example.app.attraction.service;

import com.example.app.attraction.entity.Rating;

public interface IRatingService {

    //Добавление оценки достопремечательности
    void add(Rating rating);

    //Редактирование оценки
    void update(Rating rating);

    //Удаление Оценки
    void delete(Rating rating);


}
