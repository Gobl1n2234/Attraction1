package com.example.app.attraction.request;

import lombok.Data;

@Data
public class RequestOptions {

    private Integer count;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private String city;
    private String category;
    private Integer rating;

    public RequestOptions(Integer count, Double latitude, Double longitude, Double distance) {
        this.count = count;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

}
