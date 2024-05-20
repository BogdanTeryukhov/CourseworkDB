package com.example.courseworkdatabases.repository;

import com.example.courseworkdatabases.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    Card findCardByNameAndAndBoost(String name, short boost);
    boolean existsByNameAndBoost(String name, short boost);
}
