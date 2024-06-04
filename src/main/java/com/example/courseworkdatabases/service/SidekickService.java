package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SidekickService {
    List<Sidekick> findAllSidekicks();
    void saveSidekick(Sidekick sidekick);

    List<Sidekick> findAllSidekicksWhereHealthGreaterThanValue(short value);
    List<Sidekick> findAllSidekicksWhereHealthLessThanValue(short value);
    List<Sidekick> findAllSidekicksWhereHealthEqualsValue(short value);
    List<Sidekick> findAllSidekicksByAttack(String attack);
    List<Sidekick> findAllSidekicksWhereMoveGreaterThanValue(short move);
    List<Sidekick> findAllSidekicksWhereMoveLessThanValue(short move);
    List<Sidekick> findAllSidekicksWhereMoveEqualsValue(short move);
    void deleteSidekick(String name);
    boolean sidekickExists(String name);
    Optional<Sidekick> findSidekick(String name);
}
