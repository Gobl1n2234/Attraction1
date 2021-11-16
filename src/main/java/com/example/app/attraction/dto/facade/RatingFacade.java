package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.RatingDTO;
import com.example.app.attraction.entity.Rating;



public class RatingFacade {

    public static RatingDTO ratingToDto(Rating rating){
        return new RatingDTO(rating.getId(), rating.getId(), rating.getRating());
    }
}
