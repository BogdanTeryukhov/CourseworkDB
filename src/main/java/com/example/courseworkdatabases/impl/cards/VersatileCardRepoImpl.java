package com.example.courseworkdatabases.impl.cards;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.VersatileCard;
import com.example.courseworkdatabases.repository.cards.VersatileCardRepo;
import com.example.courseworkdatabases.service.cards.VersatileCardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersatileCardRepoImpl implements VersatileCardService {

    @Autowired
    private VersatileCardRepo versatileCardRepo;

    @Override
    public void saveVersatileCard(VersatileCard versatileCard) {
        versatileCardRepo.save(versatileCard);
    }

    @Override
    public void deleteVersatileCardByNameAndBoost(String name, int boost) {
        versatileCardRepo.delete(versatileCardRepo.findVersatileCardByNameAndBoost(name, boost));
    }

    @Override
    public VersatileCard findVersatileCard(String name, int boost) {
        return versatileCardRepo.findVersatileCardByNameAndBoost(name, boost);
    }

    @Override
    public List<VersatileCard> findAllVersatileCards() {
        return versatileCardRepo.findAll();
    }

    @Override
    public Long getMaxVersatileCardId() {
        return versatileCardRepo.findAll().stream()
                .map((VersatileCard::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
