package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MapRepo extends JpaRepository<Map, Long> {
    Optional<Map> findByName(String name);
    Optional<List<Map>> findAllByNumOfPos(short numOfPos);
}
