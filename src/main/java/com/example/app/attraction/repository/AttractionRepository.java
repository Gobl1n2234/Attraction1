package com.example.app.attraction.repository;

import com.example.app.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AttractionRepository extends JpaRepository<Attraction,Long> {

    //Получить все достоп. в указанном городе
    List<Attraction> findAllByCity_Name(String name);

    Attraction findByName(String name);

    @Query("select a from Attraction a join fetch a.comments where a.id =:id")
    Attraction findByComment(@Param("id") Long id);

}
