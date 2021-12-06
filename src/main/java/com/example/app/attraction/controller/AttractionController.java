package com.example.app.attraction.controller;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.kafka.ProducerKaf;
import com.example.app.attraction.request.RequestOptions;
import com.example.app.attraction.service.impl.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attraction")
public class AttractionController {

    private final AttractionService attractionService;
    private final ProducerKaf producerKaf;

    public AttractionController(AttractionService attractionService, ProducerKaf producerKaf) {
        this.attractionService = attractionService;
        this.producerKaf = producerKaf;
    }


    //Получить все достопремечательности
    @GetMapping("/getAll")
    public ResponseEntity<List<AttractionDTO>> getAll() {
        return new ResponseEntity<>(attractionService.getAll(), HttpStatus.OK);
    }

    //Сохранение достопремечательности
    @PostMapping("/add")
    public ResponseEntity<AttractionDTO> add(@RequestBody AttractionDTO attractionDTO){
        return new ResponseEntity<>(attractionService.add(attractionDTO), HttpStatus.OK);
    }

    //Получить все в указанном городу
    @GetMapping("/{name}/getAllByCity")
    public ResponseEntity<List<AttractionDTO>> getAllByCity(@PathVariable String name){
        return new ResponseEntity<>(attractionService.getAllByCity(name), HttpStatus.OK);
    }

    /**
    * 1. Получить список достопримечательностей, находящихся в указанном радиусе от него
    * (координаты пользователя передаются в запросе) с возможностью их фильтрации по категории и(или) средней оценке.
    * Максимальное количество предлагаемых достопримечательностей и параметр,
    * по которым они должны быть отсортированы также может быть указан (по умолчанию - 10 достопримечательностей,
    * отсортированных по близости к пользователю)
     */
    @GetMapping("/getNear")
    public ResponseEntity<List<AttractionDTO>> getNeared(@RequestBody RequestOptions ro) {
        RequestOptions requestOptions = new RequestOptions(1, 12.2, 12.1, 100.2);
        List<AttractionDTO> attractionDTOS = attractionService.getNear(requestOptions);
        System.out.println(attractionDTOS);
        if(ro.getRating() == null && ro.getCategory() == null) {
            return new ResponseEntity<>(attractionService.getNear(ro), HttpStatus.OK);
        }else if(ro.getRating() == null){
            return new ResponseEntity<>(attractionService.getNearFilterCategory(ro), HttpStatus.OK);
        }else if(ro.getCategory() == null){
            return new ResponseEntity<>(attractionService.getNearFilterRating(ro), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(attractionService.getNearFilterRatingAndCategory(ro), HttpStatus.OK);
        }
    }

    /**
     * 2. Получить список достопримечательностей в указанном городе
     * (возможности фильтрации и сортировки должны быть такими же, как и в пункте 1 )
     */
    @GetMapping("/getNearByCity")
    public ResponseEntity<List<AttractionDTO>> getNerByCity(@RequestBody RequestOptions ro){
        if(ro.getRating() == null && ro.getCategory() == null) {
            return new ResponseEntity<>(attractionService.getNearByCity(ro), HttpStatus.OK);
        }else if(ro.getRating() == null){
            return new ResponseEntity<>(attractionService.getNearByCityFilterCategory(ro), HttpStatus.OK);
        }else if(ro.getCategory() == null){
            return new ResponseEntity<>(attractionService.getNearByCityFilterRating(ro), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(attractionService.getNearByCityFilterCategoryAndRating(ro), HttpStatus.OK);
        }
    }

    /**
     * 5. Посмотреть информацию и среднюю оценку по конкретной достопримечательности
     */
    @GetMapping("/{name}/getByName")
    public ResponseEntity<AttractionDTO> getByName(@PathVariable String name){
        return new ResponseEntity<>(attractionService.getByName(name), HttpStatus.OK);
    }

}
