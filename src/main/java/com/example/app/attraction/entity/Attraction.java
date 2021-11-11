package com.example.app.attraction.entity;

import lombok.AllArgsConstructor;
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
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(city, that.city) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude, city, category);
    }

    public Attraction(Long id, String name, Double latitude, Double longitude, City city, Category category) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.category = category;
    }
}
