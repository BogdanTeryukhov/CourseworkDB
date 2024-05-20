package com.example.courseworkdatabases.entity.connecter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "herocard")
public class HeroCard {

    @Id
    private Long id;

    @Column(name = "hero_name")
    private String heroName;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_boost")
    private int cardBoost;

    @Column(name = "card_number")
    private short cardNumber;

    @Column(name = "belong")
    private String belong;

    public HeroCard(String heroName, String cardName, int cardBoost, short cardNumber, String belong) {
        this.heroName = heroName;
        this.cardName = cardName;
        this.cardBoost = cardBoost;
        this.cardNumber = cardNumber;
        this.belong = belong;
    }
}
