package com.example.courseworkdatabases.service.cards;

import com.example.courseworkdatabases.entity.cards.DefenseCard;
import com.example.courseworkdatabases.entity.cards.VersatileCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DefenseCardService {
    void saveDefenseCard(DefenseCard defenseCard);
    void deleteDefenseCardById(String name, int boost);
    DefenseCard findDefenseCard(String name, int boost);
    List<DefenseCard> findAllDefenseCards();
    Long getMaxDefenseCardId();
}
