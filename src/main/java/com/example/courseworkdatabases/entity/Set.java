package com.example.courseworkdatabases.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "set")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_heroes")
    private short numberOfHeroes;

    public Set(String name, short numberOfHeroes) {
        this.name = name;
        this.numberOfHeroes = numberOfHeroes;
    }
}
