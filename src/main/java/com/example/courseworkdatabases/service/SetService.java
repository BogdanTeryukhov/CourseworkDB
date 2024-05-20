package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Set;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SetService {
    void saveSet(Set set);
    void deleteSetByName(String setName);

    Optional<Set> findSet(String name);

    List<Set> findAllSets();
    Long getMaxSetId();
}
