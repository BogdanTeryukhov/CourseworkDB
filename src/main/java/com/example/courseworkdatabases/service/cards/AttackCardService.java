package com.example.courseworkdatabases.service.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AttackCardService {
    void saveAttackCard(AttackCard attackCard);
    void deleteAttackCard(String name, int boost);
    AttackCard findAttackCard(String name, int boost);
    List<AttackCard> findAllAttackCards();
    Long getMaxAttackCardId();
}
