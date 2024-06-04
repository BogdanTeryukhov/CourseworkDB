package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
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
    Optional<Hero> findHeroByName(String name);
    List<Hero> findAllHeroes();
    Long findCorrectHeroId();
    void saveHero(Hero hero);
    List<Hero> findAllHeroesWhereHealthGreaterThanValue(short value);
    List<Hero> findAllHeroesWhereHealthLessThanValue(short value);
    List<Hero> findAllHeroesWhereHealthEqualsValue(short value);
    List<Hero> findAllHeroesByAttack(String attack);
    List<Hero> findAllHeroesWhereMoveGreaterThanValue(short move);
    List<Hero> findAllHeroesWhereMoveLessThanValue(short move);
    List<Hero> findAllHeroesWhereMoveEqualsValue(short move);
    List<Hero> findAllHeroesWhereNumberOfSidekicksGreaterThanValue(short move);
    List<Hero> findAllHeroesWhereNumberOfSidekicksLessThanValue(short move);
    List<Hero> findAllHeroesWhereNumberOfSidekicksEqualsValue(short move);
    List<Hero> findAllHeroesBySetName(String setName);
    boolean heroExists(String name);
    void deleteHeroByName(String name);
}
