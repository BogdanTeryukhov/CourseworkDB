package com.example.courseworkdatabases.impl.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.SchemeCard;
import com.example.courseworkdatabases.repository.cards.SchemeCardRepo;
import com.example.courseworkdatabases.service.cards.SchemeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeCardRepoImpl implements SchemeCardService {
    @Autowired
    private SchemeCardRepo schemeCardRepo;

    @Override
    public void saveSchemeCard(SchemeCard schemeCard) {
        schemeCardRepo.save(schemeCard);
    }

    @Override
    public void deleteSchemeCardByNameAndBoost(String name, int boost) {
        schemeCardRepo.delete(schemeCardRepo.findSchemeCardByNameAndBoost(name, boost));
    }

    @Override
    public SchemeCard findSchemeCard(String name, int boost) {
        return schemeCardRepo.findSchemeCardByNameAndBoost(name, boost);
    }

    @Override
    public List<SchemeCard> findAllSchemeCards() {
        return schemeCardRepo.findAll();
    }

    @Override
    public Long getMaxSchemeCardId() {
        return schemeCardRepo.findAll().stream()
                .map((SchemeCard::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
