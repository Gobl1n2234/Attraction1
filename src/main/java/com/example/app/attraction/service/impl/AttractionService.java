package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Rating;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.custom.impl.AttractionRepositoryCustomImpl;
import com.example.app.attraction.request.RequestOptions;
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

    private List<AttractionDTO> ratingAvgToDto(List<Attraction> attractionList){
        List<AttractionDTO> attractionDTO = new ArrayList<>();
        for (Attraction at: attractionList
        ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            attractionDTO.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTO;
    }

    @Override
    public Attraction add(Attraction attraction) {
        return attractionRepository.save(attraction);
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
    public Attraction getByName(String name) {
       return attractionRepository.findByName(name);
    }

    @Override
    public List<AttractionDTO> getAllByCity(String name) {
        return ratingAvgToDto(attractionRepositoryCustom.getAllByCity(name));
    }

    public List<AttractionDTO> getAll(){
        return ratingAvgToDto(attractionRepository.findAll());
    }

    @Override
    public List<AttractionDTO> getNear(RequestOptions ro) {
        return ratingAvgToDto(attractionRepositoryCustom.findNearest(ro.getCount(), ro.getLatitude(), ro.getLongitude(), ro.getDistance()));

    }

    @Override
    public List<AttractionDTO> getNearByCity(RequestOptions ro) {
        return ratingAvgToDto(attractionRepositoryCustom.findNearByCity(ro.getCount(), ro.getLatitude(),
                                                                        ro.getLongitude(), ro.getDistance(),
                                                                        ro.getCity()));
    }

    @Override
    public List<AttractionDTO> getNearByCityFilterCategory(RequestOptions ro) {
        return ratingAvgToDto(attractionRepositoryCustom.findNearByCityFilterByCategory(ro.getCount(), ro.getLatitude(),
                                                                                        ro.getLongitude(), ro.getDistance(),
                                                                                        ro.getCity(), ro.getCategory()));
    }

    @Override
    public List<AttractionDTO> getNearByCityFilterRating(RequestOptions ro) {
        List<Attraction> attraction = (attractionRepositoryCustom.findNearByCity(ro.getCount(), ro.getLatitude(),
                                                                                ro.getLongitude(), ro.getDistance(),
                                                                                 ro.getCity()));
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
        ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( ro.getRating().doubleValue() <= ratingAvg || ro.getRating().doubleValue() + 1 < ratingAvg)
                attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;
    }


    @Override
    public List<AttractionDTO> getNearByCityFilterCategoryAndRating(RequestOptions ro) {
        List<Attraction> attraction = (attractionRepositoryCustom.findNearByCityFilterByCategory(ro.getCount(), ro.getLatitude(),
                                                                                                ro.getLongitude(), ro.getDistance(),
                                                                                                ro.getCity(), ro.getCategory()));
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
        ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( ro.getRating().doubleValue() <= ratingAvg || ro.getRating().doubleValue() + 1 < ratingAvg)
                attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;

    }

    @Override
    public List<AttractionDTO> getNearFilterCategory(RequestOptions ro) {
        return ratingAvgToDto(attractionRepositoryCustom.findNearFilterByCategory(ro.getCount(), ro.getLatitude(), ro.getLongitude(), ro.getDistance(), ro.getCategory()));
    }

    @Override
    public List<AttractionDTO> getNearFilterRating(RequestOptions ro) {
        List<Attraction> attraction = attractionRepositoryCustom.findNearest(ro.getCount(), ro.getLatitude(), ro.getLongitude(), ro.getDistance());
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
             ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( ro.getRating().doubleValue() <= ratingAvg || ro.getRating().doubleValue() + 1 < ratingAvg)
            attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;
    }

    @Override
    public List<AttractionDTO> getNearFilterRatingAndCategory(RequestOptions ro) {
        List<Attraction> attraction = attractionRepositoryCustom.findNearFilterByCategory(ro.getCount(), ro.getLatitude(),
                                                                                            ro.getLongitude(), ro.getDistance(), ro.getCategory());
        List<AttractionDTO> attractionDTOS = new ArrayList<>();
        for (Attraction at: attraction
        ) {
            double ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
            if( ro.getRating().doubleValue() <= ratingAvg && ro.getRating().doubleValue() + 1 < ratingAvg)
                attractionDTOS.add(new AttractionDTO(at, ratingAvg));
        }
        return attractionDTOS;
    }


}
