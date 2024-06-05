package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.entity.Set;
import com.example.courseworkdatabases.repository.SetRepo;
import com.example.courseworkdatabases.service.HeroService;
import com.example.courseworkdatabases.service.MapService;
import com.example.courseworkdatabases.service.SetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {
    @Autowired
    private SetRepo setRepo;
    @Autowired
    private HeroService heroService;
    @Autowired
    private MapService mapService;

    @Override
    public Optional<List<Set>> findAllByNumberOfHeroesGreaterThan(short numberOfHeroes) {
        return setRepo.findSetsByNumberOfHeroesGreaterThan(numberOfHeroes);
    }

    @Override
    public Optional<List<Set>> findAllByNumberOfHeroesLessThan(short numberOfHeroes) {
        return setRepo.findSetsByNumberOfHeroesLessThan(numberOfHeroes);
    }

    @Override
    public Optional<List<Set>> findAllByNumberOfHeroesEquals(short numberOfHeroes) {
        return setRepo.findSetsByNumberOfHeroesEquals(numberOfHeroes);
    }

    @Override
    public Optional<List<Set>> findAllByNumberOfMapsGreaterThan(short numberOfMaps) {
        return setRepo.findSetsByNumberOfMapsGreaterThan(numberOfMaps);
    }

    @Override
    public Optional<List<Set>> findAllByNumberOfMapsLessThan(short numberOfMaps) {
        return setRepo.findSetsByNumberOfMapsLessThan(numberOfMaps);
    }

    @Override
    public Optional<List<Set>> findAllByNumberOfMapsEquals(short numberOfMaps) {
        return setRepo.findSetsByNumberOfMapsEquals(numberOfMaps);
    }

    @Override
    @TransactionTimeManagement()
    public void saveSet(Set set) {
        setRepo.save(set);
    }

    @Override
    @Modifying
    @Transactional
    @TransactionTimeManagement()
    public void deleteSetByName(String setName) {
        heroService.deleteAllHeroesBySetName(setName);
        mapService.deleteAllMapsBySetName(setName);
        setRepo.deleteByName(setName);
    }

    @Override
    public Optional<Set> findSet(String name) {
        return setRepo.findSetByName(name);
    }

    @Override
    public Optional<List<Hero>> findHeroesBySetName(String setName) {
        return heroService.findAllHeroesBySetName(setName);
    }

    @Override
    public Optional<List<Map>> findMapsBySetName(String setName) {
        return mapService.findAllMapsBySetName(setName);
    }


    @Override
    public boolean setExists(String name) {
        return setRepo.existsByName(name);
    }

}
