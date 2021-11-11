package com.example.app.attraction.repository;

import com.example.app.attraction.dto.AttractionDTO;
import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AttractionRepository extends JpaRepository<Attraction,Long> {

    //Получить все достоп. в указанном городе
    List<Attraction> findAllByCity_Name(String name);

    @Query("select a from Attraction a join fetch a.comments where a.id =:id")
    Attraction findByComment(@Param("id") Long id);

}
