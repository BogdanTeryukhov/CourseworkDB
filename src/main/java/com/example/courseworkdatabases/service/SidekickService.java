package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SidekickService {
    Optional<Sidekick> findSidekick(String name);
    Optional<List<Sidekick>> findAllSidekicksWhereHealthGreaterThanValue(short value);
    Optional<List<Sidekick>> findAllSidekicksWhereHealthLessThanValue(short value);
    Optional<List<Sidekick>> findAllSidekicksWhereHealthEqualsValue(short value);
    Optional<List<Sidekick>> findAllSidekicksByAttack(String attack);
    Optional<List<Sidekick>> findAllSidekicksWhereMoveGreaterThanValue(short move);
    Optional<List<Sidekick>> findAllSidekicksWhereMoveLessThanValue(short move);
    Optional<List<Sidekick>> findAllSidekicksWhereMoveEqualsValue(short move);
    void saveSidekick(Sidekick sidekick);
    void deleteSidekick(String name);
    boolean sidekickExists(String name);
}
