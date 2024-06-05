package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.repository.SidekickRepo;
import com.example.courseworkdatabases.service.HeroService;
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
    @Autowired
    private HeroService heroService;

    @Override
    @TransactionTimeManagement()
    public void saveSidekick(Sidekick sidekick) {
        sidekickRepo.save(sidekick);
    }

    @Override
    public Optional<List<Sidekick>>findAllSidekicksWhereHealthGreaterThanValue(short value) {
        return sidekickRepo.findSidekickByHealthGreaterThan(value);
    }

    @Override
    public Optional<List<Sidekick>> findAllSidekicksWhereHealthLessThanValue(short value) {
        return sidekickRepo.findSidekickByHealthLessThan(value);
    }

    @Override
    public Optional<List<Sidekick>> findAllSidekicksWhereHealthEqualsValue(short value) {
        return sidekickRepo.findSidekickByHealthEquals(value);
    }

    @Override
    public Optional<List<Sidekick>> findAllSidekicksByAttack(String attack) {
        return sidekickRepo.findSidekickByAttack(attack);
    }

    @Override
    public Optional<List<Sidekick>> findAllSidekicksWhereMoveGreaterThanValue(short move) {
        return sidekickRepo.findSidekickByMoveGreaterThan(move);
    }

    @Override
    public Optional<List<Sidekick>> findAllSidekicksWhereMoveLessThanValue(short move) {
        return sidekickRepo.findSidekickByMoveLessThan(move);
    }

    @Override
    public Optional<List<Sidekick>> findAllSidekicksWhereMoveEqualsValue(short move) {
        return sidekickRepo.findSidekickByMoveEquals(move);
    }

    @Override
    @TransactionTimeManagement()
    public void deleteSidekick(String name) {
        if (heroService.findBySidekickName(name).isPresent()){
            heroService.findBySidekickName(name).get().setSidekickName(null);
        }
        sidekickRepo.delete(sidekickRepo.findSidekickByName(name).orElseThrow());
    }

    @Override
    public boolean sidekickExists(String name) {
        return sidekickRepo.existsByName(name);
    }

    @Override
    public Optional<Sidekick> findSidekick(String name) {
        return sidekickRepo.findSidekickByName(name);
    }
}
