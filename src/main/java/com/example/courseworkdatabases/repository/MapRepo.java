package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Map;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MapRepo extends JpaRepository<Map, String> {
    Optional<Map> findByName(String name);
    Optional<List<Map>> findAllByNumOfPos(short numOfPos);
    Optional<List<Map>> findAllBySetName(String setName);
    boolean existsByName(String name);
    void deleteByName(String name);
    void deleteAllBySetName(String setName);
}
