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

    List<Hero> findAllByHealth(short health);
    Optional<List<Hero>> findAllByMove(short move);
    Optional<List<Hero>> findAllByAttack(String attack);
    List<Hero> findAllHeroes();
    Long findCorrectHeroId();
    void saveHero(Hero hero);
    void deleteHeroByName(String name);
}
