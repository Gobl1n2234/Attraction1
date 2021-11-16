package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.RatingDTO;
import com.example.app.attraction.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingFacade {

    public RatingDTO ratingToDto(Rating rating){
        return new RatingDTO(rating.getId(), rating.getId(), rating.getRating());
    }
}
