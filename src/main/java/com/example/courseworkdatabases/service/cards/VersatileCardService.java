package com.example.courseworkdatabases.service.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.VersatileCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VersatileCardService {
    void saveVersatileCard(VersatileCard versatileCard);
    void deleteVersatileCardByNameAndBoost(String name, int boost);
    VersatileCard findVersatileCard(String name, int boost);
    List<VersatileCard> findAllVersatileCards();
    Long getMaxVersatileCardId();
}
