package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SidekickRepo extends JpaRepository<Sidekick, String> {
    Optional<Sidekick> findSidekickByName(String name);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.health > ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByHealthGreaterThan(short value);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.health < ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByHealthLessThan(short value);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.health = ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByHealthEquals(short value);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.attack = ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByAttack(String attack);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.move > ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByMoveGreaterThan(short value);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.move < ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByMoveLessThan(short value);
    @Query(value = "SELECT s.* FROM unmatched.Sidekick s WHERE s.move = ?1", nativeQuery = true)
    Optional<List<Sidekick>> findSidekickByMoveEquals(short value);
    boolean existsByName(String name);
    void deleteByName(String name);
}
