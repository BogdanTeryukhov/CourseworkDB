package com.example.courseworkdatabases.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "map")
public class Map {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "num_of_pos")
    private short numOfPos;

    @Column(name = "set_name")
    private String setName;

    public Map(String name, short numOfPos) {
        this.name = name;
        this.numOfPos = numOfPos;
    }

}
