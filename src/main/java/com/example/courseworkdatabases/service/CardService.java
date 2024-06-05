package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.entity.composite.CardID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CardService {
    Optional<List<Card>> findAllWhereBoostGreaterThan(short boost);
    Optional<List<Card>> findAllWhereBoostLessThan(short boost);
    Optional<List<Card>> findAllWhereBoostEquals(short boost);
    Optional<List<Card>> findAllWhereValueGreaterThan(short value);
    Optional<List<Card>> findAllWhereValueLessThan(short value);
    Optional<List<Card>> findAllWhereValueEquals(short value);
    Optional<List<Card>> findAllByHeroCardNumberGreaterThan(String heroName, short numOfCards);
    Optional<List<Card>> findAllByHeroCardNumberLessThan(String heroName, short numOfCards);
    Optional<List<Card>> findAllByHeroCardNumberEquals(String heroName, short numOfCards);
    Optional<List<Card>> findCardsByType(String type);
    Optional<List<Card>> findAllByHero(String heroName);
    Optional<List<Card>> findAllBySidekick(String sidekickName);
    Optional<List<Card>> findAllUniqueCards();
    Optional<List<Card>> findAllWhereEffectExists();
    Card setUniqueWhileAdding(Card card);
    void saveCard(Card card);
    void deleteCard(String name, short boost);
    boolean ifCardExist(String name, short boost);
}
