package com.example.courseworkdatabases.service.connecter;

import com.example.courseworkdatabases.entity.connecter.HeroCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HeroCardService {
    void saveHeroCard(HeroCard heroCard);
    void deleteHeroCard(String heroName, String cardName);

    List<HeroCard> findAllHeroCards();

    Long getMaxHeroCardId();
}
