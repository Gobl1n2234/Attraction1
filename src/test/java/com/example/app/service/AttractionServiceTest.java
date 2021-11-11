package com.example.app.service;

import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Category;
import com.example.app.attraction.entity.City;
import com.example.app.attraction.repository.AttractionRepository;
import com.example.app.attraction.repository.custom.AttractionRepositoryCustom;
import com.example.app.attraction.service.IAttractionService;
import com.example.app.attraction.service.impl.AttractionService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(classes = AttractionService.class)
public class AttractionServiceTest {


    private final City samara = new City(1L, "Samara");
    private final City moscow = new City(2L,"Москва");
    private final Category category1 = new Category(1L, "Тц");
    private final Category category2 = new Category(2L,"Музей");
    private final Attraction attraction1 = new Attraction(1L, "Дворец", 12.3, 34.23, samara, category2);
    private final Attraction attraction2 = new Attraction(1L, "Космопорт", 45.34, 55.13, moscow, category1);

    @MockBean
    private AttractionRepositoryCustom attractionRepositoryCustom;

    @MockBean
    private AttractionRepository attractionRepository;

    @MockBean
    private AttractionService attractionService;

    @Test
    @DisplayName("Test findNeared Success")
    public void testNearAttract(){
        doReturn(Arrays.asList(attraction1, attraction2)).when(attractionRepository).save(any());

        List<Attraction> attractionList = attractionService.getNearAttraction(2,23.2,32.2,300.1);

        Assertions.assertEquals(2,attractionList.size());
    }

    //-276
    //323

    //2
}
