package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Map;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MapRepo extends JpaRepository<Map, String> {
    Optional<Map> findByName(String name);
    Optional<List<Map>> findAllBySetName(String setName);
    @Query(value = "SELECT m.* FROM unmatched.Map m WHERE m.num_of_pos > ?1", nativeQuery = true)
    Optional<List<Map>> findMapWhereNumOfPosGreaterThan(short numOfPos);
    @Query(value = "SELECT m.* FROM unmatched.Map m WHERE m.num_of_pos < ?1", nativeQuery = true)
    Optional<List<Map>> findMapWhereNumOfPosLessThan(short numOfPos);
    @Query(value = "SELECT m.* FROM unmatched.Map m WHERE m.num_of_pos = ?1", nativeQuery = true)
    Optional<List<Map>> findMapWhereNumOfPosEquals(short numOfPos);
    boolean existsByName(String name);
    void deleteAllBySetName(String setName);
}
