package com.example.courseworkdatabases.repository.connecter;

import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.entity.connecter.HeroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroCardRepo extends JpaRepository<HeroCard, Long> {
    void deleteAllByHeroName(String heroName);
    HeroCard findByHeroNameAndCardName(String heroName, String cardName);
    List<HeroCard> findAllByCardNameAndCardBoost(String cardName, short cardBoost);
    boolean existsByHeroNameAndAndCardName(String heroName, String cardName);
}
