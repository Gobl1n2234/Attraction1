package com.example.app.attraction.request;

import lombok.Getter;

@Getter
public class RequestOptions {

    private final Integer count;
    private final Double latitude;
    private final Double longitude;
    private final Double distance;
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
