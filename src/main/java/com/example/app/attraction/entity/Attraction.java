package com.example.app.attraction.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "attraction")
    private Set<Rating> ratings;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "attraction")
    private Set<Comment> comments;
}
