package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetRepo extends JpaRepository<Set, Long> {
    Optional<Set> findSetByName(String name);
}
