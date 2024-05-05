package com.example.courseworkdatabases.service.cards;

import com.example.courseworkdatabases.entity.cards.SchemeCard;
import com.example.courseworkdatabases.entity.cards.VersatileCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SchemeCardService {
    void saveSchemeCard(SchemeCard schemeCard);
    void deleteSchemeCardByNameAndBoost(String name, int boost);
    SchemeCard findSchemeCard(String name, int boost);
    List<SchemeCard> findAllSchemeCards();
    Long getMaxSchemeCardId();

}
