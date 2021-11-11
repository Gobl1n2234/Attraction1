package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.RatingDTO;
import com.example.app.attraction.entity.Rating;
import org.springframework.stereotype.Component;


@Component
public class RatingFacade {

    public RatingDTO toDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setDate(rating.getDate());
        ratingDTO.setUserId(rating.getUserId());

        return ratingDTO;
    }
}
