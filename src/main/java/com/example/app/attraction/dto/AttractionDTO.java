package com.example.app.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AttractionDTO {

    private final Long id;
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Double ratingAvg;
    private final String cityName;
    private final String category;

}
