package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.entity.composite.CardID;
import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.repository.CardRepo;
import com.example.courseworkdatabases.repository.connecter.HeroCardRepo;
import com.example.courseworkdatabases.service.CardService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private HeroCardRepo heroCardRepo;

    @Override
    public Optional<List<Card>> findAllWhereBoostGreaterThan(short boost) {
        return cardRepo.findCardsWhereBoostGreaterThan(boost);
    }

    @Override
    public Optional<List<Card>> findAllWhereBoostLessThan(short boost) {
        return cardRepo.findCardsWhereBoostLessThan(boost);
    }

    @Override
    public Optional<List<Card>> findAllWhereBoostEquals(short boost) {
        return cardRepo.findCardsWhereBoostEquals(boost);
    }

    @Override
    public Optional<List<Card>> findAllWhereValueGreaterThan(short value) {
        return cardRepo.findCardsWhereValueGreaterThan(value);
    }

    @Override
    public Optional<List<Card>> findAllWhereValueLessThan(short value) {
        return cardRepo.findCardsWhereValueLessThan(value);
    }

    @Override
    public Optional<List<Card>> findAllWhereValueEquals(short value) {
        return cardRepo.findCardsWhereValueEquals(value);
    }

    @Override
    public Optional<List<Card>> findCardsByType(String type) {
        return cardRepo.findCardsByType(type);
    }

    @Override
    public Optional<List<Card>> findAllUniqueCards() {
        return Optional.of(cardRepo.findAll().stream().filter(Card::isUniq).toList());
    }

    @Override
    public Optional<List<Card>> findAllWhereEffectExists() {
        return Optional.of(cardRepo.findAll().stream().filter(card -> card.getEffect() != null).toList());
    }

    @Override
    public Optional<List<Card>> findAllByHero(String heroName) {
        return cardRepo.findAllCardsByHero(heroName);
    }

    @Override
    public Optional<List<Card>> findAllBySidekick(String sidekickName) {
        return cardRepo.findAllCardsBySidekick(sidekickName);
    }

    @Override
    public Optional<List<Card>> findAllByHeroCardNumberGreaterThan(String heroName, short numOfCards) {
        return cardRepo.findAllCardsByHeroGreaterThan(heroName, numOfCards);
    }

    @Override
    public Optional<List<Card>> findAllByHeroCardNumberLessThan(String heroName, short numOfCards) {
        return cardRepo.findAllCardsByHeroLessThan(heroName, numOfCards);
    }

    @Override
    public Optional<List<Card>> findAllByHeroCardNumberEquals(String heroName, short numOfCards) {
        return cardRepo.findAllCardsByHeroEquals(heroName, numOfCards);
    }

    @Override
    @TransactionTimeManagement()
    public void saveCard(Card card) {
        cardRepo.save(card);
    }

    @Override
    public Card setUniqueWhileAdding(Card currentCard) {
        List<Card> cardsAlreadyExists = cardRepo.findAll();
        boolean isIn = false;
        for (Card card: cardsAlreadyExists) {
            if (card.getName().equals(currentCard.getName())){
                isIn = true;
                card.setUniq(false);
                cardRepo.save(card);
            }
        }
        if (isIn){
            currentCard.setUniq(false);
        }
        return currentCard;
    }

    @Override
    @TransactionTimeManagement()
    public void deleteCard(String name, short boost) {
        if (cardRepo.findCardByNameAndBoost(name, boost).isPresent()){
            List<HeroCard> heroCards = heroCardRepo.findAllByCardNameAndCardBoost(name, boost);
            heroCardRepo.deleteAll(heroCards);
            cardRepo.delete(cardRepo.findCardByNameAndBoost(name, boost).get());
        }
    }

    @Override
    public boolean ifCardExist(String name, short boost) {
        return cardRepo.existsByNameAndBoost(name, boost);
    }

}
