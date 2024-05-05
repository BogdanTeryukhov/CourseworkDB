package com.example.courseworkdatabases.repository.cards;

import com.example.courseworkdatabases.entity.cards.DefenseCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DefenseCardRepo extends JpaRepository<DefenseCard, Long> {
    Optional<DefenseCard> findByName(String name);
    DefenseCard findDefenseCardById(Long id);
    DefenseCard findDefenseCardByNameAndBoost(String name, int boost);
}
