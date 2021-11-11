package com.example.app.attraction.dto;

import com.example.app.attraction.entity.Rating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingDTO {
    private Long id;
    private Long userId;
    private Integer rating;

    public RatingDTO(Rating rating) {
        this.id = rating.getId();
        this.userId = rating.getUserId();
        this.rating = rating.getRating();
    }

    public RatingDTO() {
    }
}
