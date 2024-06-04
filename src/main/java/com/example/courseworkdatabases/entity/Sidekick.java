package com.example.courseworkdatabases.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sidekick")
public class Sidekick {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "health")
    private short health;

    @Column(name = "move")
    private short move;

    @Column(name = "attack")
    private String attack;

    @Column(name = "number_of_sidekicks")
    private short numberOfSidekicks;
}
