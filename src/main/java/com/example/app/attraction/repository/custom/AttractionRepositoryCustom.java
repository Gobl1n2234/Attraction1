package com.example.app.attraction.repository.custom;

import com.example.app.attraction.entity.Attraction;

import java.util.List;

public interface AttractionRepositoryCustom {

    //Найти ближайшие без фильтров
    List<Attraction> findNearest(Integer count, Double latitude, Double longitude, Double distance);

    //Найти ближайшие с фильтром категории
    List<Attraction> findNearFilterByCategory(Integer count, Double latitude, Double longitude, Double distance, String category);

    //Найти ближайшие с фильтрацие по городу
    List<Attraction> findNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city);

    //Найти ближайшие по городу и фильтрацией по категорие
    List<Attraction> findNearByCityFilterByCategory(Integer count, Double latitude, Double longitude, Double distance, String city, String category);

}
