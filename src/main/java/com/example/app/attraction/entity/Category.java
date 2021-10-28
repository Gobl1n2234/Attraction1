package com.example.app.attraction.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность Категории
 */
@Entity
@Setter
@Getter
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;
}
