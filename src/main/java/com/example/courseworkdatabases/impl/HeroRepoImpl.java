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
    public List<Hero> findAllByHealth(short health) {
        return heroRepo.findAll()
                .stream()
                .filter((hero -> hero.getHealth() == health))
                .toList();
    }

    @Override
    public Optional<List<Hero>> findAllByMove(short move) {
        return Optional.of(heroRepo.findAll()
                .stream()
                .filter((hero -> hero.getMove() == move))
                .toList());
    }

    @Override
    public Optional<List<Hero>> findAllByAttack(String attack) {
        return Optional.of(heroRepo.findAll()
                .stream()
                .filter((hero -> Objects.equals(hero.getAttack(), attack)))
                .toList());
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
}
