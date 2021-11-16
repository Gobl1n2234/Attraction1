package com.example.app.attraction.controller;

import com.example.app.attraction.dto.RatingDTO;
import com.example.app.attraction.dto.facade.RatingFacade;
import com.example.app.attraction.entity.Rating;
import com.example.app.attraction.service.impl.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class RatingController {


    private final RatingService ratingService;
    private final RatingFacade ratingFacade;

    public RatingController(RatingService ratingService, RatingFacade ratingFacade) {
        this.ratingService = ratingService;
        this.ratingFacade = ratingFacade;
    }

    /**
     * 3. Выставить оценку достопримечательности
     */@PostMapping("/{attractName}/add")
   public ResponseEntity<RatingDTO> add(@RequestBody RatingDTO ratingDTO, @PathVariable String attractName){
        return new ResponseEntity<>(ratingFacade.ratingToDto(ratingService.add(attractName, ratingDTO)), HttpStatus.OK);
    }
    //Удалить оценку
    @DeleteMapping("/{ratingId}/delete")
    public ResponseEntity<Rating> deleteRating (@PathVariable("ratingId") String ratingId){
        ratingService.delete(Long.parseLong(ratingId));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
