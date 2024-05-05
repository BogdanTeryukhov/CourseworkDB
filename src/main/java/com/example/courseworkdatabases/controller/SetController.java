package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Set;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
public class SetController {
    @Autowired
    private SetService setService;

    @PostMapping("/set/add")
    public String addSet(@RequestBody Set set) {
        set.setId(setService.getMaxSetId() + 1);
        setService.saveSet(set);
        return "Set has been saved";
    }

    @PutMapping("/set/update/{name}")
    public String updateSet(@PathVariable String name, @RequestBody Set set) throws CantChangeIdException {
        Set currentSet = setService.findSet(name).orElseThrow();
        if (!Objects.equals(set.getId(), currentSet.getId())){
            throw new CantChangeIdException("You can`t change id");
        }
        currentSet.setName(set.getName());
        currentSet.setNumberOfHeroes(set.getNumberOfHeroes());
        return "Set has been updated";
    }

    @DeleteMapping("/set/delete/{name}")
    public String deleteSet(@PathVariable String name) {
        setService.deleteSetByName(name);
        return "Set has been deleted";
    }
}
