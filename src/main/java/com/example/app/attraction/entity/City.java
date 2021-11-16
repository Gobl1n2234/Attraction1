package com.example.app.attraction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Objects;
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

    @OneToMany(mappedBy = "city" )
    private Set<Attraction> listAttraction;

    public City( String name) {
        this.name = name;
    }

    public City() {
    }

}
