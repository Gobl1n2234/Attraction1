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
    }

    public AttractionDTO() {
    }

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double ratingAvg;
}
