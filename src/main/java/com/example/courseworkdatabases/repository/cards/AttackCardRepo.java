package com.example.courseworkdatabases.repository.cards;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.cards.AttackCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttackCardRepo extends JpaRepository<AttackCard, Long> {
    Optional<AttackCard> findByName(String name);
    AttackCard findAttackCardById(Long id);
    AttackCard findAttackCardByNameAndBoost(String name, int boost);
}
