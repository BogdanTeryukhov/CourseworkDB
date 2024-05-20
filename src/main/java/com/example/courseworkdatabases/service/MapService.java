package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Map;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MapService {
    void saveMap(Map map);
    void deleteMapByName(String mapName);

    Optional<Map> findMap(String name);
    List<Map> findAllMaps();
    Long getMaxMapId();
}
