package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.repository.SidekickRepo;
import com.example.courseworkdatabases.service.SidekickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SidekickRepoImpl implements SidekickService {

    @Autowired
    private SidekickRepo sidekickRepo;

    @Override
    public Optional<List<Sidekick>> findAllByHealth(short health) {
        return Optional.of(sidekickRepo.findAll()
                .stream()
                .filter((sidekick -> sidekick.getHealth() == health))
                .toList());
    }

    @Override
    public Optional<List<Sidekick>> findAllByMove(short move) {
        return Optional.of(sidekickRepo.findAll()
                .stream()
                .filter((sidekick -> sidekick.getMove() == move))
                .toList());
    }

    @Override
    public List<Sidekick> findAllSidekicks() {
        return sidekickRepo.findAll();
    }

    @Override
    public Long getMaxSidekickId() {
        return sidekickRepo.findAll()
                .stream()
                .map((Sidekick::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }

    @Override
    public Optional<List<Sidekick>> findAllByAttack(String attack) {
        return Optional.of(sidekickRepo.findAll()
                .stream()
                .filter((sidekick -> Objects.equals(sidekick.getAttack(), attack)))
                .toList());
    }

    @Override
    public void saveSidekick(Sidekick sidekick) {
        sidekickRepo.save(sidekick);
    }

    @Override
    public void deleteSidekick(String name) {
        sidekickRepo.delete(sidekickRepo.findByName(name).orElseThrow());
    }

    @Override
    public Optional<Sidekick> findSidekick(String name) {
        return sidekickRepo.findByName(name);
    }
}
