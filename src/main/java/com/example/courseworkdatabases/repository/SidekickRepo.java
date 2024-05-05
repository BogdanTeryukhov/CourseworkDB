package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Sidekick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SidekickRepo extends JpaRepository<Sidekick, Long> {
    Optional<Sidekick> findByName(String name);
}
