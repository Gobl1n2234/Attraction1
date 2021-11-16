package com.example.app.attraction.repository;

import com.example.app.attraction.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //Получить все комментарии по указанной достопремечательности
    List<Comment> findAllByAttraction_Name(String name);
}
