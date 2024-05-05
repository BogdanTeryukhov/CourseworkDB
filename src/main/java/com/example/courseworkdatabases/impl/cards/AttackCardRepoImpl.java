package com.example.courseworkdatabases.impl.cards;

import com.example.courseworkdatabases.controller.CardController;
import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.repository.cards.AttackCardRepo;
import com.example.courseworkdatabases.service.cards.AttackCardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttackCardRepoImpl implements AttackCardService {

    @Autowired
    private AttackCardRepo attackCardRepo;

    @Override
    public void saveAttackCard(AttackCard attackCard) {
        attackCardRepo.save(attackCard);
    }

    @Override
    @Transactional
    public void deleteAttackCard(String name, int boost) {
        attackCardRepo.delete(attackCardRepo.findAttackCardByNameAndBoost(name, boost));
    }

    @Override
    public AttackCard findAttackCard(String name, int boost) {
        return attackCardRepo.findAttackCardByNameAndBoost(name, boost);
    }

    @Override
    public List<AttackCard> findAllAttackCards() {
        return attackCardRepo.findAll();
    }

    @Override
    public Long getMaxAttackCardId() {
        return attackCardRepo.findAll().stream()
                .map((AttackCard::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
