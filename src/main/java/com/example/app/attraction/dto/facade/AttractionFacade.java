package com.example.app.attraction.dto.facade;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import org.springframework.stereotype.Component;

@Component
public class AttractionFacade {

    public AttractionDTO toDTO(Attraction attraction){
        AttractionDTO attractionDTO = new AttractionDTO();
        attractionDTO.setId(attraction.getId());
        attractionDTO.setName(attraction.getName());
        attractionDTO.setLatitude(attraction.getLatitude());
        attractionDTO.setLongitude(attraction.getLongitude());
        return attractionDTO;
    }
}
