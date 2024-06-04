package com.example.courseworkdatabases.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "card")
@Entity
public class Card {
    @Id
    Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private short value;

    @Column(name = "boost")
    private short boost;

    @Column(name = "effect")
    private String effect;

    @Column(name = "uniq")
    private boolean uniq;

    @Column(name = "type")
    private String type;

    public Card(String name, short value, short boost, String effect, boolean uniq, String type) {
        this.name = name;
        this.value = value;
        this.boost = boost;
        this.effect = effect;
        this.uniq = uniq;
        this.type = type;
    }
}
