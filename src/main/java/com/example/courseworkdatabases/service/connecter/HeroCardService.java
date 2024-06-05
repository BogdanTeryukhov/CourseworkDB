package com.example.courseworkdatabases.service.connecter;

import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.exception.NoHeroOrCardAddedException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HeroCardService {
    Long getMaxHeroCardId();
    void saveHeroCard(HeroCard heroCard) throws NoHeroOrCardAddedException;
    void deleteHeroCard(String heroName, String cardName);
    boolean heroCardExists(String heroName, String cardName);
}
