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
    Optional<Set> findSetByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
