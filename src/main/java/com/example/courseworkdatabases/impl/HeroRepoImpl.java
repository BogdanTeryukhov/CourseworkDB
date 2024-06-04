package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.repository.HeroRepo;
import com.example.courseworkdatabases.repository.SidekickRepo;
import com.example.courseworkdatabases.repository.connecter.HeroCardRepo;
import com.example.courseworkdatabases.service.HeroService;
import com.example.courseworkdatabases.service.connecter.HeroCardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HeroRepoImpl implements HeroService {

    @Autowired
    private HeroRepo heroRepo;
    @Autowired
    private SidekickRepo sidekickRepo;
    @Autowired
    private HeroCardRepo heroCardRepo;

    @Override
    public Optional<Hero> findHeroByName(String name) {
        return heroRepo.findByName(name);
    }

    @Override
    public List<Hero> findAllHeroes() {
        return heroRepo.findAll();
    }


    @Override
    @TransactionTimeManagement()
    public void saveHero(Hero hero) {
        heroRepo.save(hero);
    }

    @Override
    @Transactional
    @TransactionTimeManagement()
    public void deleteHeroByName(String name) {
        Hero hero = heroRepo.findByName(name).orElseThrow();
        heroRepo.deleteByName(name);
        sidekickRepo.delete(sidekickRepo.findSidekickByName(hero.getSidekickName()).orElseThrow());
        heroCardRepo.deleteAllByHeroName(name);
    }

    @Override
    @TransactionTimeManagement()
    public void deleteAllHeroesBySetName(String setName) {
        List<Hero> heroesBySetName = heroRepo.findAllBySetName(setName).orElse(new ArrayList<>());
        for (Hero hero: heroesBySetName) {
            heroRepo.deleteByName(hero.getName());
            sidekickRepo.delete(sidekickRepo.findSidekickByName(hero.getSidekickName()).orElseThrow());
            heroCardRepo.deleteAllByHeroName(hero.getName());
        }
    }

    @Override
    public List<Hero> findAllHeroesWhereHealthGreaterThanValue(short value) {
        return heroRepo.findHeroByHealthGreaterThan(value);
    }

    @Override
    public List<Hero> findAllHeroesWhereHealthLessThanValue(short value) {
        return heroRepo.findHeroByHealthLessThan(value);
    }

    @Override
    public List<Hero> findAllHeroesWhereHealthEqualsValue(short value) {
        return heroRepo.findHeroByHealthEquals(value);
    }

    @Override
    public List<Hero> findAllHeroesByAttack(String attack) {
        return heroRepo.findHeroByAttack(attack);
    }

    @Override
    public List<Hero> findAllHeroesWhereMoveGreaterThanValue(short move) {
        return heroRepo.findHeroByMoveGreaterThan(move);
    }

    @Override
    public List<Hero> findAllHeroesWhereMoveLessThanValue(short move) {
        return heroRepo.findHeroByMoveLessThan(move);
    }

    @Override
    public List<Hero> findAllHeroesWhereMoveEqualsValue(short move) {
        return heroRepo.findHeroByMoveEquals(move);
    }

    @Override
    public List<Hero> findAllHeroesWhereNumberOfSidekicksGreaterThanValue(short move) {
        List<String> sidekicks = sidekickRepo.findAll().stream().filter(sidekick -> sidekick.getNumberOfSidekicks() > move).map(sidekick -> sidekick.getName()).toList();
        return heroRepo.findAll().stream().filter(hero -> sidekicks.contains(hero.getSidekickName())).toList();
    }

    @Override
    public List<Hero> findAllHeroesWhereNumberOfSidekicksLessThanValue(short move) {
        List<String> sidekicks = sidekickRepo.findAll().stream().filter(sidekick -> sidekick.getNumberOfSidekicks() < move).map(sidekick -> sidekick.getName()).toList();
        return heroRepo.findAll().stream().filter(hero -> sidekicks.contains(hero.getSidekickName())).toList();
    }

    @Override
    public List<Hero> findAllHeroesWhereNumberOfSidekicksEqualsValue(short move) {
        List<String> sidekicks = sidekickRepo.findAll().stream().filter(sidekick -> sidekick.getNumberOfSidekicks() == move).map(sidekick -> sidekick.getName()).toList();
        return heroRepo.findAll().stream().filter(hero -> sidekicks.contains(hero.getSidekickName())).toList();
    }

    @Override
    public Optional<List<Hero>> findAllHeroesBySetName(String setName) {
        return heroRepo.findAllBySetName(setName);
    }

    @Override
    public boolean heroExists(String name) {
        return heroRepo.existsByName(name);
    }

    @Override
    public void updateHero(String prevName, Hero hero) throws CantChangeIdException {
        Hero currentHero = heroRepo.findByName(prevName).orElseThrow();
        if (!Objects.equals(currentHero.getName(), hero.getName())){
            throw new CantChangeIdException("You can`t change the ID");
        }
        currentHero.setHealth(hero.getHealth() == 0 ? currentHero.getHealth() : hero.getHealth());
        currentHero.setMove(hero.getMove() == 0 ? currentHero.getMove() : hero.getMove());
        currentHero.setAbility(hero.getAbility() == null ? currentHero.getAbility() : hero.getAbility());
        currentHero.setAttack(hero.getAttack() == null ? currentHero.getAttack() : hero.getAttack());
        currentHero.setSidekickName(hero.getSidekickName() == null ?
                currentHero.getSidekickName() : hero.getSidekickName());
        currentHero.setSetName(hero.getSetName() == null ?
                currentHero.getSetName() : hero.getSetName());
        heroRepo.save(currentHero);
    }
}
