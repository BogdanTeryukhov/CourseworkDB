package com.example.courseworkdatabases.repository.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.VersatileCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VersatileCardRepo extends JpaRepository<VersatileCard, Long> {
    Optional<VersatileCard> findByName(String name);
    VersatileCard findVersatileCardById(Long id);
    VersatileCard findVersatileCardByNameAndBoost(String name, int boost);
}
