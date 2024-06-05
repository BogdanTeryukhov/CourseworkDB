package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.MapService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @GetMapping("/positions/greater/{numOfPos}")
    public List<Map> heroesHealthGreaterThan(@PathVariable short numOfPos){
        return mapService.findAllWhereNumOfPosGreaterThan(numOfPos).orElse(new ArrayList<>());
    }

    @GetMapping("/positions/less/{numOfPos}")
    public List<Map> heroesHealthLessThan(@PathVariable short numOfPos){
        return mapService.findAllWhereNumOfPosLessThan(numOfPos).orElse(new ArrayList<>());
    }

    @GetMapping("/positions/equals/{numOfPos}")
    public List<Map> heroesHealthEquals(@PathVariable short numOfPos){
        return mapService.findAllWhereNumOfPosEquals(numOfPos).orElse(new ArrayList<>());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMap(@RequestBody Map map){
        if (mapService.mapExists(map.getName())){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getCreateErrorString(map),
                    HttpStatus.BAD_REQUEST
            );
        }
        mapService.saveMap(map);
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(map), HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateMap(@PathVariable String name, @RequestBody Map map) throws CantChangeIdException {
        Map currentMap = mapService.findMap(name).orElseThrow();
        if (!Objects.equals(map.getName(), currentMap.getName())){
            throw new CantChangeIdException("You can`t change ID");
        }
        currentMap.setSetName(map.getSetName());
        currentMap.setNumOfPos(map.getNumOfPos());
        return new ResponseEntity<>(CrudMessagesUtil.getUpdateString(map), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteMap(@PathVariable String name){
        if (!mapService.mapExists(name)){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getDeleteErrorString(new Map()),
                    HttpStatus.BAD_REQUEST
            );
        }
        mapService.deleteMapByName(name);
        return new ResponseEntity<>(CrudMessagesUtil.getDeleteString(new Map()), HttpStatus.OK);
    }
}
