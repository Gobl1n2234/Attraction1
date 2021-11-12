package com.example.app.attraction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность Города
 */
@Entity
@Setter
@Getter
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city" )
    private Set<Attraction> listAttraction;

    public City( String name) {
        this.name = name;
    }

    public City() {
    }
}
