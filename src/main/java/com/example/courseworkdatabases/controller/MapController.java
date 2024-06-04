package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @PostMapping("/add")
    public String addMap(@RequestBody Map map){
        map.setId(mapService.getMaxMapId() + 1);
        mapService.saveMap(map);
        return "Map has been saved";
    }

    @PutMapping("/update/{name}")
    public String updateMap(@PathVariable String name, @RequestBody Map map) throws CantChangeIdException {
        Map currentMap = mapService.findMap(name).orElseThrow();
        if (!Objects.equals(map.getId(), currentMap.getId())){
            throw new CantChangeIdException("You can`t change ID");
        }
        currentMap.setName(map.getName());
        currentMap.setSetName(map.getSetName());
        currentMap.setNumOfPos(map.getNumOfPos());
        return "Map has been updated";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteMap(@PathVariable String name){
        mapService.deleteMapByName(name);
        return "Map has been deleted";
    }
}
