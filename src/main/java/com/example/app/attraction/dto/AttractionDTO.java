package com.example.app.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AttractionDTO {

    private final Long id;
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Double ratingAvg;
    private final String cityName;
    private final String category;

    public AttractionDTO(Long id, String name, Double latitude, Double longitude, Double ratingAvg, String cityName, String category) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratingAvg = ratingAvg;
        this.cityName = cityName;
        this.category = category;
    }


}
