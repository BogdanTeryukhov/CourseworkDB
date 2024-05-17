package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepo extends JpaRepository<Hero, Long> {
    Optional<Hero> findByName(String name);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.health > ?1", nativeQuery = true)
    List<Hero> findHeroByHealthGreaterThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.health < ?1", nativeQuery = true)
    List<Hero> findHeroByHealthLessThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.health = ?1", nativeQuery = true)
    List<Hero> findHeroByHealthEquals(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.attack = ?1", nativeQuery = true)
    List<Hero> findHeroByAttack(String attack);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.move > ?1", nativeQuery = true)
    List<Hero> findHeroByMoveGreaterThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.move < ?1", nativeQuery = true)
    List<Hero> findHeroByMoveLessThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.move = ?1", nativeQuery = true)
    List<Hero> findHeroByMoveEquals(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.number_of_sidekicks > ?1", nativeQuery = true)
    List<Hero> findHeroByNumberOfSidekicksGreaterThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.number_of_sidekicks < ?1", nativeQuery = true)
    List<Hero> findHeroByNumberOfSidekicksLessThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.number_of_sidekicks = ?1", nativeQuery = true)
    List<Hero> findHeroByNumberOfSidekicksEquals(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.set_name = ?1", nativeQuery = true)
    List<Hero> findHeroBySetName(String setName);
    void deleteByName(String name);
}
