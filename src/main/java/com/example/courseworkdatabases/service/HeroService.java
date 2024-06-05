package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.repository.HeroRepo;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
public interface HeroService {
    Optional<List<Hero>> findAllHeroes();
    Optional<List<Hero>> findAllHeroesWhereHealthGreaterThanValue(short value);
    Optional<List<Hero>> findAllHeroesWhereHealthLessThanValue(short value);
    Optional<List<Hero>> findAllHeroesWhereHealthEqualsValue(short value);
    Optional<List<Hero>> findAllHeroesByAttack(String attack);
    Optional<List<Hero>> findAllHeroesWhereMoveGreaterThanValue(short move);
    Optional<List<Hero>> findAllHeroesWhereMoveLessThanValue(short move);
    Optional<List<Hero>> findAllHeroesWhereMoveEqualsValue(short move);
    Optional<List<Hero>> findAllHeroesWhereNumberOfSidekicksGreaterThanValue(short numberOfSidekicks);
    Optional<List<Hero>> findAllHeroesWhereNumberOfSidekicksLessThanValue(short numberOfSidekicks);
    Optional<List<Hero>> findAllHeroesWhereNumberOfSidekicksEqualsValue(short numberOfSidekicks);
    Optional<List<Hero>> findAllHeroesBySetName(String setName);
    Optional<Hero> findBySidekickName(String sidekickName);
    void saveHero(Hero hero);
    boolean heroExists(String name);
    void updateHero(String prevName, Hero hero) throws CantChangeIdException;
    void deleteHeroByName(String name);
    void deleteAllHeroesBySetName(String setName);
}
