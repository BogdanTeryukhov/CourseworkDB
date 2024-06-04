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

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private HeroCardRepo heroCardRepo;

    @Override
    @TransactionTimeManagement()
    public void saveCard(Card card) {
        cardRepo.save(card);
    }

    @Override
    public void setUniqueWhileAdding(Card card) {
        cardRepo
                .findAll()
                .stream()
                .filter((current) -> Objects.equals(current.getName(), card.getName()))
                .forEach((current) -> current.setUniq(false));
    }

    @Override
    @TransactionTimeManagement()
    public void deleteCard(String name, short boost) {
        List<HeroCard> heroCards = heroCardRepo.findAllByCardNameAndCardBoost(name, boost);
        heroCardRepo.deleteAll(heroCards);
        cardRepo.delete(cardRepo.findCardByNameAndBoost(name, boost));
    }

    @Override
    public boolean ifCardExist(String name, short boost) {
        return cardRepo.existsByNameAndBoost(name, boost);
    }

}
