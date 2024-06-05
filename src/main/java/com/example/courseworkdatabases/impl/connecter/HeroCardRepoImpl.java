package com.example.courseworkdatabases.impl.connecter;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import com.example.courseworkdatabases.entity.composite.CardID;
import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.exception.NoHeroOrCardAddedException;
import com.example.courseworkdatabases.repository.CardRepo;
import com.example.courseworkdatabases.repository.HeroRepo;
import com.example.courseworkdatabases.repository.connecter.HeroCardRepo;
import com.example.courseworkdatabases.service.CardService;
import com.example.courseworkdatabases.service.HeroService;
import com.example.courseworkdatabases.service.connecter.HeroCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroCardRepoImpl implements HeroCardService {
    @Autowired
    private HeroCardRepo heroCardRepo;
    @Autowired
    private HeroRepo heroRepo;
    @Autowired
    private CardRepo cardRepo;

    @Override
    @TransactionTimeManagement()
    public void saveHeroCard(HeroCard heroCard) throws NoHeroOrCardAddedException {
        if (heroRepo.findById(heroCard.getHeroName()).isEmpty()
                || cardRepo.findById(new CardID(heroCard.getCardName(), (short) heroCard.getCardBoost())).isEmpty()){
            throw new NoHeroOrCardAddedException();
        }
        heroCardRepo.save(heroCard);
    }

    @Override
    @TransactionTimeManagement()
    public void deleteHeroCard(String heroName, String cardName) {
        heroCardRepo.delete(heroCardRepo.findByHeroNameAndCardName(heroName, cardName));
    }

    @Override
    public Long getMaxHeroCardId() {
        return heroCardRepo.findAll().stream()
                .map((HeroCard::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L);
    }

    @Override
    public boolean heroCardExists(String heroName, String cardName) {
        return heroCardRepo.existsByHeroNameAndAndCardName(heroName, cardName);
    }
}
