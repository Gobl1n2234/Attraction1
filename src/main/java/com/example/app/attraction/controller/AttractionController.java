package com.example.app.attraction.controller;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.request.RequestOptions;
import com.example.app.attraction.service.impl.AttractionService;
import liquibase.pro.packaged.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;


    @GetMapping("/getNear")
    public ResponseEntity<List<AttractionDTO>> getNeared(@RequestBody RequestOptions requestOptions) {
        List<AttractionDTO> attractionDTOS = attractionService.getNearAttraction(requestOptions);

        return new ResponseEntity<>(attractionDTOS, HttpStatus.OK);
    }

    @GetMapping("/getAllByCity/{name}")
    public ResponseEntity<List<AttractionDTO>> getAllByCity(@PathVariable String name){
        return new ResponseEntity<>(attractionService.getAllByCity(name), HttpStatus.OK);
    }

    @GetMapping("/getNearRating")
    private ResponseEntity<List<AttractionDTO>> getNearByRating(@RequestBody RequestOptions requestOptions){
        return new ResponseEntity<>(attractionService.getNearAttraction(requestOptions), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AttractionDTO>> getAll() {
        return new ResponseEntity<>(attractionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getNearByCity")
    public ResponseEntity<List<AttractionDTO>> getNerByCity(@RequestBody RequestOptions requestOptions){
        return new ResponseEntity<>(attractionService.getNearByCity(requestOptions), HttpStatus.OK);
    }
}
