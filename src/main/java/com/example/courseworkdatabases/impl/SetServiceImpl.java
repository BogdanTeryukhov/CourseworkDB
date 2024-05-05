package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.entity.Set;
import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.repository.SetRepo;
import com.example.courseworkdatabases.service.SetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {
    @Autowired
    private SetRepo setRepo;

    @Override
    public void saveSet(Set set) {
        setRepo.save(set);
    }

    @Override
    public void deleteSetByName(String setName) {
        setRepo.delete(setRepo.findSetByName(setName).get());
    }

    @Override
    public Optional<Set> findSet(String name) {
        return setRepo.findSetByName(name);
    }

    @Override
    public List<Set> findAllSets() {
        return setRepo.findAll();
    }

    @Override
    public Long getMaxSetId() {
        return setRepo.findAll()
                .stream()
                .map((Set::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
