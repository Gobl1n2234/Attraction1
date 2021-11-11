package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Rating;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.RatingRepository;
import com.example.app.attraction.repository.custom.impl.AttractionRepositoryCustomImpl;
import com.example.app.attraction.service.IAttractionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionService implements IAttractionService {

    private final AttractionRepositoryCustomImpl attractionRepositoryCustom;
    private final AttractionRepository attractionRepository;


    public AttractionService(AttractionRepositoryCustomImpl attractionRepositoryCustom, AttractionRepository attractionRepository) {
        this.attractionRepositoryCustom = attractionRepositoryCustom;
        this.attractionRepository = attractionRepository;

    }


    @Override
    public void add(Attraction attraction) {
    attractionRepository.save(attraction);
    }

    @Override
    public void update(Attraction attraction) {
    attractionRepository.save(attraction);
    }

    @Override
    public void delete(Attraction attraction) {
    attractionRepository.delete(attraction);
    }

    @Override
    public List<Attraction> getAllByCity(String name) {
        return attractionRepository.findAllByCity_Name(name);
    }

    @Override
    public List<Attraction> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance) {
        return attractionRepositoryCustom.findNearest(count, latitude, longitude, distance);
    }

    @Override
    public List<Attraction> getNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city) {
        return attractionRepositoryCustom.findNearByCity(count, latitude, longitude, distance, city);
    }

    @Override
    public List<Attraction> getNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city, String category) {
        return attractionRepositoryCustom.findNearByCityFilterByCategory(count, latitude, longitude, distance, city, category);
    }

    //Todo
    @Override
    public List<AttractionDTO> getNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city, Integer rating, String category) {
        List<Attraction> attraction = attractionRepositoryCustom.findNearByCityFilterByCategory(count, latitude, longitude, distance, city, category);
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
        ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( rating.doubleValue() <= ratingAvg && rating.doubleValue() + 1 < ratingAvg)
                attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;
    }

    @Override
    public List<Attraction> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance, String category) {
        return attractionRepositoryCustom.findNearFilterByCategory(count, latitude, longitude, distance, category);
    }
    //Todo
    @Override
    public List<AttractionDTO> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance, Integer rating) {
        List<Attraction> attraction = attractionRepositoryCustom.findNearest(count, latitude, longitude, distance);
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
             ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( rating.doubleValue() <= ratingAvg && rating.doubleValue() + 1 < ratingAvg)
            attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;
    }
    //Todo
    @Override
    public List<AttractionDTO> getNearAttraction(Integer count, Double latitude, Double longitude, Double distance, Integer rating, String category) {
        List<Attraction> attraction = attractionRepositoryCustom.findNearFilterByCategory(count, latitude, longitude, distance, category);
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
        ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( rating.doubleValue() <= ratingAvg && rating.doubleValue() + 1 < ratingAvg)
                attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;
    }
}
