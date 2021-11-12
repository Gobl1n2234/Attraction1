package com.example.app.attraction.service.impl;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.*;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.CategoryRepository;
import com.example.app.attraction.repository.CityRepository;
import com.example.app.attraction.repository.custom.AttractionRepositoryCustom;
import com.example.app.attraction.repository.custom.impl.AttractionRepositoryCustomImpl;
import com.example.app.attraction.request.RequestOptions;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.doReturn;


@SpringBootTest
class AttractionServiceTest {

    private  List<Rating> ratings = new ArrayList<>();
    private  List<Comment> comment = new ArrayList<>();
    private final City samara = new City( "sdfsadg");
    private final City moscow = new City("agas");
    private final Category category1 = new Category( "asdg");
    private final Category category2 = new Category("asdg");
    private final Attraction attraction2 = new Attraction(2L, "Космопорт", 45.34, 55.13, moscow, category1);
    private final Attraction attraction1 = new Attraction(1L, "asf", 45.34, 55.13, moscow, category1);




     @Autowired
     AttractionService attractionService;

     @MockBean
     AttractionRepositoryCustomImpl attractionRepositoryCustom;



    @Test
    void getByName() {

    }

    @Test
    void getAllByCity() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getNear() {
        Rating rating = new Rating(1L,1L,LocalDateTime.now(), 3);
        Rating rating2 = new Rating(2L,2L,LocalDateTime.now(), 3);
        Set<Rating> ratings = new HashSet<>();
        ratings.add(rating);
        ratings.add(rating2);
        attraction1.setRatings(ratings);
        attraction2.setRatings(ratings);
        RequestOptions requestOptions = new RequestOptions(1, 12.2, 12.1, 100.2);
        doReturn(Arrays.asList(attraction1, attraction2)).when(attractionRepositoryCustom).findNearest(1, 12.2, 12.1, 100.2);

        List<AttractionDTO> attractionDTOS = attractionService.getNear(requestOptions);

        // Assert the response
        Assertions.assertEquals(1, attractionDTOS.size(), "findAll should return 2 widgets");
    }

    @Test
    void getNearByCity() {
    }
}