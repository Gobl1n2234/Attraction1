package com.example.app.attraction.service;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;

import java.util.List;

public interface IAttractionService {
    //Добавление достопремечательности
    void add(Attraction attraction);

    //Редактирование достопремечаткльности
    void update(Attraction attraction);

    //Удаление достопремечательности
    void delete(Attraction attraction);

    //Получить все достоп. в указанном городе
    List<Attraction> getAllByCity(String name);

    //Найти ближайшие без фильтров
    List<Attraction> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance);

    //Найти ближайшие с фильтрацие по городу
    List<Attraction> getNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city);

    //Найти ближайшие по городу и фильтрацией по категорие
    List<Attraction> getNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city, String category);

    //Найти ближайшие по городу и фильтрацией по категорие и рейтингу
    List<AttractionDTO> getNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city, Integer rating, String category);

    //Найти ближайшие с фильтром категории
    List<Attraction> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance, String category);

    //Найти ближайшие с фильтром рейтинг
    List<AttractionDTO> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance, Integer rating);

    //Найти ближайшие с двумя фильтрами
    List<AttractionDTO> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance, Integer rating, String category);
}
