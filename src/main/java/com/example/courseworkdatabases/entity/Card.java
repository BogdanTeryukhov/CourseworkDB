package com.example.courseworkdatabases.entity;

import com.example.courseworkdatabases.entity.composite.CardID;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "card")
@Entity
@IdClass(CardID.class)
public class Card {

    @Id
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "boost")
    private short boost;

    @Column(name = "value")
    private short value;

    @Column(name = "effect")
    private String effect;

    @Column(name = "uniq")
    private boolean uniq;

    @Column(name = "type")
    private String type;

}
