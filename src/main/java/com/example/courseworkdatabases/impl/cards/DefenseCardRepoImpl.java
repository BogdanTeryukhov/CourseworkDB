package com.example.courseworkdatabases.impl.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.DefenseCard;
import com.example.courseworkdatabases.repository.cards.DefenseCardRepo;
import com.example.courseworkdatabases.service.cards.DefenseCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefenseCardRepoImpl implements DefenseCardService {

    @Autowired
    private DefenseCardRepo defenseCardRepo;

    @Override
    public void saveDefenseCard(DefenseCard defenseCard) {
        defenseCardRepo.save(defenseCard);
    }

    @Override
    public void deleteDefenseCardById(String name, int boost) {
        defenseCardRepo.delete(defenseCardRepo.findDefenseCardByNameAndBoost(name, boost));
    }

    @Override
    public DefenseCard findDefenseCard(String name, int boost) {
        return defenseCardRepo.findDefenseCardByNameAndBoost(name, boost);
    }

    @Override
    public List<DefenseCard> findAllDefenseCards() {
        return defenseCardRepo.findAll();
    }

    @Override
    public Long getMaxDefenseCardId() {
        return defenseCardRepo.findAll().stream()
                .map((DefenseCard::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
