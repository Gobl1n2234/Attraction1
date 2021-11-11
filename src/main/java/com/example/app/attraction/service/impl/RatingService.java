package com.example.app.attraction.service.impl;

import com.example.app.attraction.entity.Rating;
import com.example.app.attraction.repository.RatingRepository;
import com.example.app.attraction.service.IRatingService;

public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void add(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void update(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }
}
