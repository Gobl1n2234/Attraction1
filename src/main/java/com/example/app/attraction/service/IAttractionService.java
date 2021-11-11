package com.example.app.attraction.service;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.request.RequestOptions;

import java.util.List;

public interface IAttractionService {
    //Добавление достопремечательности
    void add(Attraction attraction);

    //Редактирование достопремечаткльности
    void update(Attraction attraction);

    //Удаление достопремечательности
    void delete(Attraction attraction);

    //Получить все достоп. в указанном городе
    List<AttractionDTO> getAllByCity(String name);

    //Найти ближайшие без фильтров
    List<AttractionDTO> getNearAttraction(RequestOptions requestOptions);

    //Найти ближайшие с фильтрацие по городу
    List<AttractionDTO> getNearByCity(RequestOptions requestOptions);

    //Найти ближайшие по городу и фильтрацией по категорие
    List<AttractionDTO> getNearByCityFilerCityAndCategory(RequestOptions requestOptions);

    //Найти ближайшие по городу и фильтрацией по категорие и рейтингу
    List<AttractionDTO> getNearByCityFilerCityAndCategoryAndRating(RequestOptions requestOptions);

    //Найти ближайшие с фильтром категории
    List<AttractionDTO> getNearAttractionFilterCategory(RequestOptions requestOptions);

    //Найти ближайшие с фильтром рейтинг
    List<AttractionDTO> getNearAttractionFilterRating(RequestOptions requestOptions);

    //Найти ближайшие с двумя фильтрами
    List<AttractionDTO> getNearAttractionFilterRatingAndCategory(RequestOptions requestOptions);
}
