package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.entity.Set;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SetService {
    Optional<Set> findSet(String name);
    Optional<List<Hero>> findHeroesBySetName(String setName);
    Optional<List<Map>> findMapsBySetName(String setName);
    Optional<List<Set>> findAllByNumberOfHeroesGreaterThan(short numberOfHeroes);
    Optional<List<Set>> findAllByNumberOfHeroesLessThan(short numberOfHeroes);
    Optional<List<Set>> findAllByNumberOfHeroesEquals(short numberOfHeroes);
    Optional<List<Set>> findAllByNumberOfMapsGreaterThan(short numberOfMaps);
    Optional<List<Set>> findAllByNumberOfMapsLessThan(short numberOfMaps);
    Optional<List<Set>> findAllByNumberOfMapsEquals(short numberOfMaps);
    void saveSet(Set set);
    void deleteSetByName(String setName);
    boolean setExists(String name);
}
