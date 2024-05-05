package com.example.courseworkdatabases.impl;

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
    public void saveMap(Map map) {
        mapRepo.save(map);
    }

    @Override
    public void deleteMapByName(String mapName) {
        mapRepo.delete(mapRepo.findByName(mapName).orElseThrow());
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
    public Long getMaxMapId() {
        return mapRepo.findAll()
                .stream()
                .map((Map::getId))
                .mapToLong(Long::longValue)
                .max()
                .orElse(1L);
    }
}
