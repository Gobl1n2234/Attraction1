package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.*;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.CategoryRepository;
import com.example.app.attraction.repository.CityRepository;
import com.example.app.attraction.repository.RatingRepository;
import com.example.app.attraction.repository.custom.impl.AttractionRepositoryCustomImpl;
import com.example.app.attraction.request.RequestOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AttractionServiceTest {

    private final Long id1 = 1L;
    private final Long id2 = 2L;
    private final String cosmo = "Космопорт";
    private final String azia = "АзияМол";
    private  final Set<Rating> ratings = new HashSet<>();
    private final City samara = new City( "Samara");
    private final Category tradeCenterCategory = new Category( "Тц");
    private final Attraction CosmoAttraction = new Attraction(id1, cosmo, 45.34, 55.13, samara, tradeCenterCategory);
    private final Attraction AziaAttraction = new Attraction(id2, azia, 45.34, 55.13, samara, tradeCenterCategory);

    @Mock
    private AttractionRepository attractionRepository;

    @Mock
    private CityRepository cityRepo;

    @Mock
    private CategoryRepository categoryRepo;

    @Mock
    private AttractionRepositoryCustomImpl attractionRepositoryCustom;

    @Mock
    private RatingRepository ratingRepository;


    @InjectMocks
    AttractionService attractionService;


    @Test
    void getByName() {

        when(attractionRepository.findByName(cosmo)).thenReturn(AziaAttraction);

        AttractionDTO attraction4 = attractionService.getByName(cosmo);

        assertThat(attraction4.getName()).isEqualTo(AziaAttraction.getName());

        Mockito.verify(attractionRepository, Mockito.times(1)).findByName(cosmo);
    }

    @Test
    void getAll() {
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        doReturn(Arrays.asList(AziaAttraction, CosmoAttraction)).when(attractionRepository).findAll();

        List<AttractionDTO> attractionDTOS = attractionService.getAll();

        List<String> nameAttrac = new ArrayList<>();
        nameAttrac.add(attractionDTOS.get(0).getName());
        nameAttrac.add(attractionDTOS.get(1).getName());

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(attractionDTOS.size()).isEqualTo(2);
        assertThat(nameAttrac).containsOnly(cosmo, azia);
        assertThat(nameCategory).containsOnly(tradeCenterCategory.getName());

        Mockito.verify(attractionRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getNear() {
        List<Attraction> attractionList = new ArrayList<>();
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        when(attractionRepositoryCustom.findNearest(2,12.1,12.2,100.1)).thenReturn(attractionList);
        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        List<AttractionDTO> attractionDTOS = attractionService.getNear(requestOptions);

        List<String> name = new ArrayList<>();
        name.add(attractionDTOS.get(0).getName());
        name.add(attractionDTOS.get(1).getName());

        assertThat(name).containsOnly(cosmo, azia);

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearest(2,12.1,12.2,100.1);

    }


    @Test
    void getNearByCity() {
        List<Attraction> attractionList = new ArrayList<>();
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        when(attractionRepositoryCustom.findNearByCity(2,12.1,12.2,100.1,samara.getName())).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCity(samara.getName());
        List<AttractionDTO> attractionDTOS = attractionService.getNearByCity(requestOptions);

        List<String> nameCity = new ArrayList<>();
        nameCity.add(attractionDTOS.get(0).getCityName());
        nameCity.add(attractionDTOS.get(1).getCityName());

        assertThat(nameCity).containsOnly(samara.getName());

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearByCity(2,12.1,12.2,100.1, samara.getName());
    }

    @Test
    void getNearByCityFilterCategory() {
        List<Attraction> attractionList = new ArrayList<>();
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        when(attractionRepositoryCustom.findNearByCityFilterByCategory(2,12.1,12.2,100.1,samara.getName(), tradeCenterCategory.getName() )).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCity(samara.getName());
        requestOptions.setCategory(tradeCenterCategory.getName());
        List<AttractionDTO> attractionDTOS = attractionService.getNearByCityFilterCategory(requestOptions);

        List<String> nameCity = new ArrayList<>();
        nameCity.add(attractionDTOS.get(0).getCityName());
        nameCity.add(attractionDTOS.get(1).getCityName());

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCity).containsOnly(samara.getName());
        assertThat(nameCategory).containsExactlyInAnyOrder(tradeCenterCategory.getName(), tradeCenterCategory.getName());

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearByCityFilterByCategory(2,12.1,12.2,100.1, samara.getName(), tradeCenterCategory.getName());
    }

    @Test
    void getNearByCityFilterRating() {
        List<Attraction> attractionList = new ArrayList<>();
        Rating rating = new Rating();
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        rating.setRating(4);
        rating1.setRating(3);
        rating2.setRating(1);
        ratings.add(rating);
        ratings.add(rating1);
        ratings.add(rating2);
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        Double ratingAvg = null;
        for (Attraction at: attractionList
        ) {
            ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
        }
        when(attractionRepositoryCustom.findNearByCity(2,12.1,12.2,100.1,samara.getName())).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCity(samara.getName());
        requestOptions.setRating(2);
        List<AttractionDTO> attractionDTOS = attractionService.getNearByCityFilterRating(requestOptions);

        Double ratingAvgDto = attractionDTOS.get(0).getRatingAvg();
        Double ratingAvgDto1 = attractionDTOS.get(1).getRatingAvg();

        List<String> nameCity = new ArrayList<>();
        nameCity.add(attractionDTOS.get(0).getCityName());
        nameCity.add(attractionDTOS.get(1).getCityName());

        assertThat(nameCity).containsOnly(samara.getName());

        assertThat(ratingAvgDto).isEqualTo(ratingAvg);
        assertThat(ratingAvgDto1).isEqualTo(ratingAvg);


        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearByCity(2,12.1,12.2,100.1, samara.getName());

    }

    @Test
    void getNearByCityFilterCategoryAndRating() {
        List<Attraction> attractionList = new ArrayList<>();
        Rating rating = new Rating();
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        rating.setRating(4);
        rating1.setRating(3);
        rating2.setRating(1);
        ratings.add(rating);
        ratings.add(rating1);
        ratings.add(rating2);
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        Double ratingAvg = null;
        for (Attraction at: attractionList
        ) {
            ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
        }
        when(attractionRepositoryCustom.findNearByCityFilterByCategory(2,12.1,12.2,100.1,samara.getName(), tradeCenterCategory.getName())).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCity(samara.getName());
        requestOptions.setCategory(tradeCenterCategory.getName());
        requestOptions.setRating(2);
        List<AttractionDTO> attractionDTOS = attractionService.getNearByCityFilterCategoryAndRating(requestOptions);

        Double ratingAvgDto = attractionDTOS.get(0).getRatingAvg();
        Double ratingAvgDto1 = attractionDTOS.get(1).getRatingAvg();

        List<String> nameCity = new ArrayList<>();
        nameCity.add(attractionDTOS.get(0).getCityName());
        nameCity.add(attractionDTOS.get(1).getCityName());

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCity).containsOnly(samara.getName());

        assertThat(nameCategory).containsExactlyInAnyOrder(tradeCenterCategory.getName(), tradeCenterCategory.getName());

        assertThat(ratingAvgDto).isEqualTo(ratingAvg);
        assertThat(ratingAvgDto1).isEqualTo(ratingAvg);


        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearByCityFilterByCategory(2,12.1,12.2,100.1, samara.getName(),tradeCenterCategory.getName());
    }

    @Test
    void getNearFilterCategory() {
        List<Attraction> attractionList = new ArrayList<>();
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        when(attractionRepositoryCustom.findNearFilterByCategory(2,12.1,12.2,100.1, tradeCenterCategory.getName() )).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCategory(tradeCenterCategory.getName());
        List<AttractionDTO> attractionDTOS = attractionService.getNearFilterCategory(requestOptions);

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCategory).containsExactlyInAnyOrder(tradeCenterCategory.getName(), tradeCenterCategory.getName());

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearFilterByCategory(2,12.1,12.2,100.1, tradeCenterCategory.getName());
    }

    @Test
    void getNearFilterRating() {
        List<Attraction> attractionList = new ArrayList<>();
        Rating rating = new Rating();
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        rating.setRating(4);
        rating1.setRating(3);
        rating2.setRating(1);
        ratings.add(rating);
        ratings.add(rating1);
        ratings.add(rating2);
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        Double ratingAvg = null;
        for (Attraction at: attractionList
        ) {
            ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
        }
        when(attractionRepositoryCustom.findNearest(2,12.1,12.2,100.1)).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setRating(2);
        List<AttractionDTO> attractionDTOS = attractionService.getNearFilterRating(requestOptions);

        Double ratingAvgDto = attractionDTOS.get(0).getRatingAvg();
        Double ratingAvgDto1 = attractionDTOS.get(1).getRatingAvg();

        assertThat(ratingAvgDto).isEqualTo(ratingAvg);
        assertThat(ratingAvgDto1).isEqualTo(ratingAvg);


        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearest(2,12.1,12.2,100.1);
    }

    @Test
    void getNearFilterRatingAndCategory() {
        List<Attraction> attractionList = new ArrayList<>();
        Rating rating = new Rating();
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        rating.setRating(4);
        rating1.setRating(3);
        rating2.setRating(1);
        ratings.add(rating);
        ratings.add(rating1);
        ratings.add(rating2);
        AziaAttraction.setRatings(ratings);
        CosmoAttraction.setRatings(ratings);
        attractionList.add(AziaAttraction);
        attractionList.add(CosmoAttraction);
        Double ratingAvg = null;
        for (Attraction at: attractionList
        ) {
            ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
        }
        when(attractionRepositoryCustom.findNearFilterByCategory(2,12.1,12.2,100.1, tradeCenterCategory.getName())).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCategory(tradeCenterCategory.getName());
        requestOptions.setRating(2);
        List<AttractionDTO> attractionDTOS = attractionService.getNearFilterRatingAndCategory(requestOptions);

        Double ratingAvgDto = attractionDTOS.get(0).getRatingAvg();
        Double ratingAvgDto1 = attractionDTOS.get(1).getRatingAvg();

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCategory).containsExactlyInAnyOrder(tradeCenterCategory.getName(), tradeCenterCategory.getName());

        assertThat(ratingAvgDto).isEqualTo(ratingAvg);
        assertThat(ratingAvgDto1).isEqualTo(ratingAvg);

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearFilterByCategory(2,12.1,12.2,100.1,tradeCenterCategory.getName());
    }
}

