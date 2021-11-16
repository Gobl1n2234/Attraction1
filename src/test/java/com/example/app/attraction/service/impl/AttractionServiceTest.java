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



}