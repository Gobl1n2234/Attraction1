package com.example.app.attraction.dto;

import com.example.app.attraction.entity.Attraction;
import lombok.Data;

@Data
public class AttractionDTO {

    public AttractionDTO(Attraction attraction, Double rating) {
        this.ratingAvg = rating;
        this.id = attraction.getId();
        this.name = attraction.getName();
        this.latitude = attraction.getLatitude();
        this.longitude = attraction.getLongitude();
        this.cityName = attraction.getCity().getName();
        this.category = attraction.getCategory().getName();
    }

    public AttractionDTO(Attraction attraction) {
        this.name = attraction.getName();
        this.latitude = attraction.getLatitude();
        this.longitude = attraction.getLongitude();
        this.cityName = attraction.getCity().getName();
        this.category = attraction.getCategory().getName();
    }

    public AttractionDTO() {
    }

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double ratingAvg;
    private String cityName;
    private String category;
}
