package com.example.app.service;

import com.example.app.attraction.service.Impl.AttractionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AttractionServiceTest {

    @Mock
    private AttractionService mockedAttractionService;

    @Test
    public void getNearAttractionTest(){
        System.out.println(mockedAttractionService.getNearAttraction(3, 34.13,43.12, 100.23));
    }
}
