package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepo extends JpaRepository<Hero, Long> {
    Optional<Hero> findByName(String name);
    void deleteByName(String name);
}
