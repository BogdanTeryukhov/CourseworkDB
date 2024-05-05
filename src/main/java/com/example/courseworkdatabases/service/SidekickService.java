package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SidekickService {
    Optional<List<Sidekick>> findAllByHealth(short health);
    Optional<List<Sidekick>> findAllByAttack(String attack);
    Optional<List<Sidekick>> findAllByMove(short move);
    List<Sidekick> findAllSidekicks();
    Long getMaxSidekickId();
    void saveSidekick(Sidekick sidekick);
    void deleteSidekick(String name);

    Optional<Sidekick> findSidekick(String name);
}
