package com.example.courseworkdatabases.impl.connecter;

import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.repository.connecter.HeroCardRepo;
import com.example.courseworkdatabases.service.connecter.HeroCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroCardRepoImpl implements HeroCardService {
    @Autowired
    private HeroCardRepo heroCardRepo;

    @Override
    public void saveHeroCard(HeroCard heroCard) {
        heroCardRepo.save(heroCard);
    }

    @Override
    public void deleteHeroCard(String heroName, String cardName) {
        heroCardRepo.delete(heroCardRepo.findByHeroNameAndCardName(heroName, cardName));
    }

    @Override
    public List<HeroCard> findAllHeroCards() {
        return heroCardRepo.findAll();
    }


    @Override
    public Long getMaxHeroCardId() {
        return heroCardRepo.findAll().stream()
                .map((HeroCard::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
