package com.example.app.attraction.repository;

import com.example.app.attraction.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select avg(r.rating) from Rating r where r.attraction.id =:id")
    Double findAvgRating(@Param("id") Long id);
}
