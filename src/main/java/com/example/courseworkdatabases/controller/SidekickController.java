package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.SidekickService;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class SidekickController {

    @Autowired
    private SidekickService sidekickService;

    @PostMapping("/addsidekick")
    public String sidekick(@RequestBody Sidekick sidekick){
        sidekick.setId(sidekickService.getMaxSidekickId() + 1);
        sidekickService.saveSidekick(sidekick);
        return "Sidekick has been added";
    }

    @PutMapping("/sidekick/update{name}")
    public String updateSidekick(@PathVariable String name, @RequestBody Sidekick sidekick) throws CantChangeIdException {
        Sidekick currentSidekick = sidekickService.findSidekick(name).orElseThrow();
        if (!Objects.equals(sidekick.getId(), currentSidekick.getId())){
            throw new CantChangeIdException("You can`t change ID");
        }
        currentSidekick.setName(sidekick.getName());
        currentSidekick.setAttack(sidekick.getAttack());
        currentSidekick.setMove(sidekick.getMove());
        currentSidekick.setAttack(sidekick.getAttack());
        return "Sidekick has been updated";
    }

    @DeleteMapping("/sidekick/delete/{name}")
    public String addSidekick(@PathVariable String name){
        sidekickService.deleteSidekick(name);
        return "Sidekick has been deleted";
    }
}
