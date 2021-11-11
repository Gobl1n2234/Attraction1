package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.RatingDTO;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Rating;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.RatingRepository;
import com.example.app.attraction.service.IRatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;
    private final AttractionRepository attractionRepository;

    public RatingService(RatingRepository ratingRepository, AttractionRepository attractionRepository) {
        this.ratingRepository = ratingRepository;
        this.attractionRepository = attractionRepository;

    }

    @Override
    public Rating add(String attractionName, RatingDTO ratingDTO) {
        Attraction attraction = attractionRepository.findByName(attractionName);

        Rating rating = new Rating();
        rating.setAttraction(attraction);
        rating.setRating(ratingDTO.getRating());
        rating.setUserId(ratingDTO.getUserId());

       return ratingRepository.save(rating);
    }


    @Override
    public void delete(Long ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        rating.ifPresent(ratingRepository::delete);
    }
}
