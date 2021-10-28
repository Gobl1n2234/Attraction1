package com.example.app.attraction.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "city")
    private Set<Attraction> listAttraction;

}
