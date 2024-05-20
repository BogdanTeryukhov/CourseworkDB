package com.example.courseworkdatabases.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "hero")
public class Hero {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "health")
    private short health;

    @Column(name = "move")
    private short move;

    @Column(name = "ability")
    private String ability;

    @Column(name = "attack")
    private String attack;

    @Column(name = "sidekick_name")
    private String sidekickName;

    @Column(name = "number_of_sidekicks")
    private short numberOfSidekicks;

    @Column(name = "set_name")
    private String setName;


    public Hero(String name, short health, short move, String ability, String attack) {
        this.name = name;
        this.health = health;
        this.move = move;
        this.ability = ability;
        this.attack = attack;
    }

    public Hero(String name, short health, short move, String ability, String attack, String sidekickName, String setName) {
        this.name = name;
        this.health = health;
        this.move = move;
        this.ability = ability;
        this.attack = attack;
        this.sidekickName = sidekickName;
        this.setName = setName;
    }
}
