package com.example.app.attraction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Сущность Достопримечательности
 */
@Entity
@Setter
@Getter
@Table(name = "Attraction")
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", unique = true, nullable = false)
    private String name;


    @Column(name = "latitude", nullable = false)
    private Double latitude;


    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @ManyToOne
    private City city;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "attraction")
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "id")
    private Set<Comment> comments;
}
