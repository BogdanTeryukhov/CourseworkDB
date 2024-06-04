package com.example.courseworkdatabases.impl;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.repository.MapRepo;
import com.example.courseworkdatabases.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private MapRepo mapRepo;
    @Override
    @TransactionTimeManagement()
    public void saveMap(Map map) {
        mapRepo.save(map);
    }

    @Override
    @TransactionTimeManagement()
    public void deleteMapByName(String mapName) {
        mapRepo.delete(mapRepo.findByName(mapName).orElseThrow());
    }

    @Override
    public Optional<List<Map>> findAllMapsBySetName(String setName) {
        return mapRepo.findAllBySetName(setName);
    }

    @Override
    public Optional<Map> findMap(String name) {
        return mapRepo.findByName(name);
    }

    @Override
    public List<Map> findAllMaps() {
        return mapRepo.findAll();
    }

    @Override
    public boolean mapExists(String name) {
        return mapRepo.existsByName(name);
    }

    @Override
    @TransactionTimeManagement()
    public void deleteMap(String name) {
        mapRepo.deleteByName(name);
    }

    @Override
    public void deleteAllMapsBySetName(String setName) {
        mapRepo.deleteAllBySetName(setName);
    }
}
