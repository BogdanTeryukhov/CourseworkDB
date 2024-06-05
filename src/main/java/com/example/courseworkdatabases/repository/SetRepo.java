package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetRepo extends JpaRepository<Set, String> {
    @Query(value = "SELECT s.* FROM unmatched.Set s WHERE s.number_of_heroes > ?1", nativeQuery = true)
    Optional<List<Set>> findSetsByNumberOfHeroesGreaterThan(short numberOfHeroes);
    @Query(value = "SELECT s.* FROM unmatched.Set s WHERE s.number_of_heroes < ?1", nativeQuery = true)
    Optional<List<Set>> findSetsByNumberOfHeroesLessThan(short numberOfHeroes);
    @Query(value = "SELECT s.* FROM unmatched.Set s WHERE s.number_of_heroes = ?1", nativeQuery = true)
    Optional<List<Set>> findSetsByNumberOfHeroesEquals(short numberOfHeroes);
    @Query(value = "SELECT s.* FROM unmatched.Set s WHERE s.number_of_maps > ?1", nativeQuery = true)
    Optional<List<Set>> findSetsByNumberOfMapsGreaterThan(short numberOfMaps);
    @Query(value = "SELECT s.* FROM unmatched.Set s WHERE s.number_of_maps < ?1", nativeQuery = true)
    Optional<List<Set>> findSetsByNumberOfMapsLessThan(short numberOfMaps);
    @Query(value = "SELECT s.* FROM unmatched.Set s WHERE s.number_of_maps = ?1", nativeQuery = true)
    Optional<List<Set>> findSetsByNumberOfMapsEquals(short numberOfMaps);
    Optional<Set> findSetByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
