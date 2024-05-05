package com.example.courseworkdatabases.repository.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.SchemeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchemeCardRepo extends JpaRepository<SchemeCard, Long> {
    Optional<SchemeCard> findByName(String name);
    SchemeCard findSchemeCardById(Long id);
    SchemeCard findSchemeCardByNameAndBoost(String name, int boost);
}
