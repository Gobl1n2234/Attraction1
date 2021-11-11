package com.example.app.attraction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Сущность Оценки
 */
@Entity
@Setter
@Getter
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;



    @Column(name = "date", updatable = false, nullable = false)
    private LocalDateTime date;


    @Column(name = "rating", nullable = false)
    private Integer rating;


    @ManyToOne(fetch = FetchType.EAGER)
    private Attraction attraction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(id, rating1.id) && Objects.equals(userId, rating1.userId) && Objects.equals(date, rating1.date) && Objects.equals(rating, rating1.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, rating);
    }

    @PrePersist
    protected void onCreate()
    {
        this.date = LocalDateTime.now();
    }
}
