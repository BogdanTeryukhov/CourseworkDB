package com.example.courseworkdatabases.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sidekick")
public class Sidekick {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "health")
    private short health;

    @Column(name = "move")
    private short move;

    @Column(name = "attack")
    private String attack;

    public Sidekick(String name, short health, short move, String attack) {
        this.name = name;
        this.health = health;
        this.move = move;
        this.attack = attack;
    }
}
