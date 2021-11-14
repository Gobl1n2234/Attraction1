package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.*;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.CategoryRepository;
import com.example.app.attraction.repository.CityRepository;
import com.example.app.attraction.repository.custom.impl.AttractionRepositoryCustomImpl;
import com.example.app.attraction.request.RequestOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AttractionServiceTest {

    private final Long id1 = 1L;
    private final Long id2 = 2L;
    private final Long id3 = 3L;
    private final String cosmo = "Космопорт";
    private final String azia = "АзияМол";
    private final String stroi = "СтройКомплекс";
    private  Set<Rating> ratings = new HashSet<>();
    private final City samara = new City( "Samara");
    private final City moscow = new City("Moscow");
    private final Category category1 = new Category( "Тц");
    private final Category category2 = new Category("Дворец");
    private final Attraction attraction2 = new Attraction(id1, cosmo, 45.34, 55.13, samara, category1);
    private final Attraction attraction1 = new Attraction(id2, azia, 45.34, 55.13, samara, category1);

    @Mock
    private AttractionRepository attractionRepository;

    @Mock
    private CityRepository cityRepo;

    @Mock
    private CategoryRepository categoryRepo;

    @Mock
    private AttractionRepositoryCustomImpl attractionRepositoryCustom;


    @InjectMocks
    AttractionService attractionService;


    @Test
    void getByName() {
        Attraction attraction = new Attraction();
        when(attractionRepository.findByName(cosmo)).thenReturn(attraction);

        Attraction attraction4 = attractionService.getByName(cosmo);

        assertThat(attraction4.getName()).isEqualTo(attraction.getName());

        Mockito.verify(attractionRepository, Mockito.times(1)).findByName(cosmo);
    }

    @Test
    void getAll() {
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        doReturn(Arrays.asList(attraction1, attraction2)).when(attractionRepository).findAll();

        List<AttractionDTO> attractionDTOS = attractionService.getAll();

        List<String> nameAttrac = new ArrayList<>();
        nameAttrac.add(attractionDTOS.get(0).getName());
        nameAttrac.add(attractionDTOS.get(1).getName());

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(attractionDTOS.size()).isEqualTo(2);
        assertThat(nameAttrac).containsOnly(cosmo, azia);
        assertThat(nameCategory).containsOnly(category1.getName());

        Mockito.verify(attractionRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getNear() {
        List<Attraction> attractionList = new ArrayList<>();
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
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
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
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
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
        when(attractionRepositoryCustom.findNearByCityFilterByCategory(2,12.1,12.2,100.1,samara.getName(), category1.getName() )).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCity(samara.getName());
        requestOptions.setCategory(category1.getName());
        List<AttractionDTO> attractionDTOS = attractionService.getNearByCityFilterCategory(requestOptions);

        List<String> nameCity = new ArrayList<>();
        nameCity.add(attractionDTOS.get(0).getCityName());
        nameCity.add(attractionDTOS.get(1).getCityName());

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCity).containsOnly(samara.getName());
        assertThat(nameCategory).containsExactlyInAnyOrder(category1.getName(), category1.getName());

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearByCityFilterByCategory(2,12.1,12.2,100.1, samara.getName(), category1.getName());
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
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
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
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
        Double ratingAvg = null;
        for (Attraction at: attractionList
        ) {
            ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
        }
        when(attractionRepositoryCustom.findNearByCityFilterByCategory(2,12.1,12.2,100.1,samara.getName(), category1.getName())).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCity(samara.getName());
        requestOptions.setCategory(category1.getName());
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

        assertThat(nameCategory).containsExactlyInAnyOrder(category1.getName(), category1.getName());

        assertThat(ratingAvgDto).isEqualTo(ratingAvg);
        assertThat(ratingAvgDto1).isEqualTo(ratingAvg);


        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearByCityFilterByCategory(2,12.1,12.2,100.1, samara.getName(),category1.getName());
    }

    @Test
    void getNearFilterCategory() {
        List<Attraction> attractionList = new ArrayList<>();
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
        when(attractionRepositoryCustom.findNearFilterByCategory(2,12.1,12.2,100.1, category1.getName() )).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCategory(category1.getName());
        List<AttractionDTO> attractionDTOS = attractionService.getNearFilterCategory(requestOptions);

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCategory).containsExactlyInAnyOrder(category1.getName(), category1.getName());

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearFilterByCategory(2,12.1,12.2,100.1, category1.getName());
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
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
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
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        attractionList.add(attraction1);
        attractionList.add(attraction2);
        Double ratingAvg = null;
        for (Attraction at: attractionList
        ) {
            ratingAvg = at.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(0);
        }
        when(attractionRepositoryCustom.findNearFilterByCategory(2,12.1,12.2,100.1, category1.getName())).thenReturn(attractionList);

        RequestOptions requestOptions = new RequestOptions(2, 12.1, 12.2, 100.1);
        requestOptions.setCategory(category1.getName());
        requestOptions.setRating(2);
        List<AttractionDTO> attractionDTOS = attractionService.getNearFilterRatingAndCategory(requestOptions);

        Double ratingAvgDto = attractionDTOS.get(0).getRatingAvg();
        Double ratingAvgDto1 = attractionDTOS.get(1).getRatingAvg();

        List<String> nameCategory = new ArrayList<>();
        nameCategory.add(attractionDTOS.get(0).getCategory());
        nameCategory.add(attractionDTOS.get(1).getCategory());

        assertThat(nameCategory).containsExactlyInAnyOrder(category1.getName(), category1.getName());

        assertThat(ratingAvgDto).isEqualTo(ratingAvg);
        assertThat(ratingAvgDto1).isEqualTo(ratingAvg);

        Mockito.verify(attractionRepositoryCustom, Mockito.times(1)).findNearFilterByCategory(2,12.1,12.2,100.1,category1.getName());
    }
}