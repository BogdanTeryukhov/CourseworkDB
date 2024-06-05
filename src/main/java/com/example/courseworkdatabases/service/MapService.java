package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MapService {
    Optional<Map> findMap(String name);
    Optional<List<Map>> findAllMapsBySetName(String setName);
    Optional<List<Map>> findAllWhereNumOfPosGreaterThan(short numOfPos);
    Optional<List<Map>> findAllWhereNumOfPosLessThan(short numOfPos);
    Optional<List<Map>> findAllWhereNumOfPosEquals(short numOfPos);
    void saveMap(Map map);
    void deleteMapByName(String mapName);
    boolean mapExists(String name);
    void deleteAllMapsBySetName(String setName);
}
