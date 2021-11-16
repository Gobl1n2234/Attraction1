package com.example.app.attraction.controller;

import com.example.app.attraction.dto.CommentDTO;
import com.example.app.attraction.dto.facade.CommentFacade;
import com.example.app.attraction.entity.Comment;
import com.example.app.attraction.service.impl.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    private final CommentService commentService;
    private final CommentFacade commentFacade;

    public CommentController(CommentService commentService, CommentFacade commentFacade) {
        this.commentService = commentService;
        this.commentFacade = commentFacade;
    }

    /**
     * 4. Написать отзыв о посещении достопримечательности
     */
    @PostMapping("/{attractName}/add")
    public ResponseEntity<CommentDTO> add(@RequestBody CommentDTO commentDTO, @PathVariable String attractName){
        return new ResponseEntity<>(commentFacade.commentToDto(commentService.add(attractName, commentDTO)), HttpStatus.OK);
    }

    //Удалить отзыв
    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<Comment> deleteComment (@PathVariable("commentId") String commentId){
        commentService.delete(Long.parseLong(commentId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 6. Посмотреть отзывы о конкретной достопримечательности
     */
    @GetMapping("/{name}/getAllByAttraction")
    public ResponseEntity<List<CommentDTO>> getAllByAttraction(@PathVariable String name){
        return new ResponseEntity<>(commentService.getAllByAttraction(name), HttpStatus.OK);
    }
}
