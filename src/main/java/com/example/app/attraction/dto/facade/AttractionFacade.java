package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import org.springframework.stereotype.Component;

public class AttractionFacade {

    public static AttractionDTO attractionToDto(Attraction attraction, Double rating){
        return new AttractionDTO(attraction.getId(), attraction.getName(), attraction.getLatitude(),
                attraction.getLongitude(), rating, attraction.getCity().getName(), attraction.getCategory().getName());
    }

}
