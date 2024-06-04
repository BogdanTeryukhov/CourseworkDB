package com.example.courseworkdatabases.repository.connecter;

import com.example.courseworkdatabases.entity.connecter.HeroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroCardRepo extends JpaRepository<HeroCard, Long> {
    HeroCard findByHeroName(String heroName);
    HeroCard findByCardName(String cardName);
    HeroCard findByHeroNameAndCardName(String heroName, String cardName);
    List<HeroCard> findAllByCardNameAndCardBoost(String cardName, short cardBoost);
}
