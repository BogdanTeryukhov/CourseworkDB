package com.example.courseworkdatabases.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "set")
public class Set {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "number_of_heroes")
    private short numberOfHeroes;
    @Column(name = "number_of_maps")
    private short numberOfMaps;
}
