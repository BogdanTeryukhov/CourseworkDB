package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.repository.HeroRepo;
import com.example.courseworkdatabases.service.HeroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HeroRepoImpl implements HeroService {

    @Autowired
    private HeroRepo heroRepo;


    @Override
    public Optional<Hero> findHeroByName(String name) {
        return heroRepo.findByName(name);
    }

    @Override
    public List<Hero> findAllHeroes() {
        return heroRepo.findAll();
    }

    @Override
    public Long findCorrectHeroId() {
        return heroRepo.findAll().stream()
                .map((Hero::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }

    @Override
    public void saveHero(Hero hero) {
        heroRepo.save(hero);
    }

    @Override
    @Transactional
    public void deleteHeroByName(String name) {
        heroRepo.deleteByName(name);
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
    public List<Hero> findAllHeroesWhereNumberOfSidekicksGreaterThanValue(short numberOfSidekicks) {
        return heroRepo.findHeroByNumberOfSidekicksGreaterThan(numberOfSidekicks);
    }

    @Override
    public List<Hero> findAllHeroesWhereNumberOfSidekicksLessThanValue(short numberOfSidekicks) {
        return heroRepo.findHeroByNumberOfSidekicksLessThan(numberOfSidekicks);
    }

    @Override
    public List<Hero> findAllHeroesWhereNumberOfSidekicksEqualsValue(short numberOfSidekicks) {
        return heroRepo.findHeroByNumberOfSidekicksEquals(numberOfSidekicks);
    }

    @Override
    public List<Hero> findAllHeroesBySetName(String setName) {
        return heroRepo.findHeroBySetName(setName);
    }
}
