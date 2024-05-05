package com.example.courseworkdatabases.entity.cards;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schemecard")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchemeCard {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "boost")
    private short boost;

    @Column(name = "effect")
    private String effect;

    @Column(name = "is_unique")
    private boolean isUnique;

    public SchemeCard(String name, short boost, String effect, boolean isUnique) {
        this.name = name;
        this.boost = boost;
        this.effect = effect;
        this.isUnique = isUnique;
    }
}
