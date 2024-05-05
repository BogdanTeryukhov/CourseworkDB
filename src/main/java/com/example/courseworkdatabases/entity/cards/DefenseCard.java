package com.example.courseworkdatabases.entity.cards;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "defensecard")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefenseCard {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private short value;

    @Column(name = "boost")
    private short boost;

    @Column(name = "effect")
    private String effect;

    @Column(name = "is_unique")
    private boolean isUnique;

    public DefenseCard(String name, short value, short boost, String effect, boolean isUnique) {
        this.name = name;
        this.value = value;
        this.boost = boost;
        this.effect = effect;
        this.isUnique = isUnique;
    }
}
