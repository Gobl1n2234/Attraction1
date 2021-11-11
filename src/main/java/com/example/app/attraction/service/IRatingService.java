package com.example.app.attraction.service;

import com.example.app.attraction.dto.RatingDTO;
import com.example.app.attraction.entity.Rating;

public interface IRatingService {

    //Добавление оценки достопремечательности
    Rating add(String attractionName,RatingDTO ratingDTO);

    //Удаление Оценки
    void delete(Long ratingId);


}
