package com.example.app.attraction.service;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.request.RequestOptions;

import java.util.List;

public interface IAttractionService {
    //Добавление достопремечательности
    Attraction add( AttractionDTO attractionDTO);

    //Редактирование достопремечаткльности
    void update(Attraction attraction);

    //Удаление достопремечательности
    void delete(Attraction attraction);

    Attraction getByName(String name);

    //Получить все достоп. в указанном городе
    List<AttractionDTO> getAllByCity(String name);

    //Найти ближайшие без фильтров
    List<AttractionDTO> getNear(RequestOptions requestOptions);

    //Найти ближайшие с фильтрацие по городу
    List<AttractionDTO> getNearByCity(RequestOptions requestOptions);

    //Найти ближайшие по городу и фильтрацией по категорие
    List<AttractionDTO> getNearByCityFilterCategory(RequestOptions requestOptions);

    List<AttractionDTO> getNearByCityFilterRating(RequestOptions requestOptions);

    //Найти ближайшие по городу и фильтрацией по категорие и рейтингу
    List<AttractionDTO> getNearByCityFilterCategoryAndRating(RequestOptions requestOptions);

    //Найти ближайшие с фильтром категории
    List<AttractionDTO> getNearFilterCategory(RequestOptions requestOptions);

    //Найти ближайшие с фильтром рейтинг
    List<AttractionDTO> getNearFilterRating(RequestOptions requestOptions);

    //Найти ближайшие с двумя фильтрами
    List<AttractionDTO> getNearFilterRatingAndCategory(RequestOptions requestOptions);


}
