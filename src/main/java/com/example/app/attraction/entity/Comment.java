package com.example.app.attraction.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность Комментария
 */
@Entity
@Setter
@Getter
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_id", unique = true)
    private Long userId;

    @NotNull
    @Column(name = "date", updatable = false)
    private LocalDateTime date;

    @NotNull
    @Column(name = "rating")
    private Integer rating;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Attraction attraction;
}
