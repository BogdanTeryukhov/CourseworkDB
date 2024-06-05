package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepo extends JpaRepository<Hero, String> {
    Optional<Hero> findByName(String name);
    Optional<List<Hero>> findAllBySetName(String setName);
    Optional<Hero> findHeroBySidekickName(String sidekickName);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.health > ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByHealthGreaterThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.health < ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByHealthLessThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.health = ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByHealthEquals(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.attack = ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByAttack(String attack);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.move > ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByMoveGreaterThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.move < ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByMoveLessThan(short value);
    @Query(value = "SELECT h.* FROM unmatched.Hero h WHERE h.move = ?1", nativeQuery = true)
    Optional<List<Hero>> findHeroByMoveEquals(short value);

    boolean existsByName(String name);
    void deleteByName(String name);
}
