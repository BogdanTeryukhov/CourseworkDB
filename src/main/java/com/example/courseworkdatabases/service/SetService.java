package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.entity.Set;
import jakarta.persistence.Column;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SetService {
    void saveSet(Set set);
    void deleteSetByName(String setName);
    Optional<Set> findSet(String name);
    Optional<List<Hero>> findHeroesBySetName(String setName);
    Optional<List<Map>> findMapsBySetName(String setName);
    List<Set> findAllSets();
    boolean setExists(String name);
    void deleteSet(String name);
}
