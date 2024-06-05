package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Sidekick;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.SidekickService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sidekick")
public class SidekickController {

    @Autowired
    private SidekickService sidekickService;

    @PostMapping("/add")
    public ResponseEntity<String> sidekick(@RequestBody Sidekick sidekick){
        if (sidekickService.sidekickExists(sidekick.getName())){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getCreateErrorString(sidekick),
                    HttpStatus.BAD_REQUEST
            );
        }
        sidekickService.saveSidekick(sidekick);
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(sidekick), HttpStatus.OK);
    }

    @GetMapping("/health/greater/{health}")
    public List<Sidekick> heroesHealthGreaterThan(@PathVariable short health){
        return sidekickService.findAllSidekicksWhereHealthGreaterThanValue(health).orElse(new ArrayList<>());
    }

    @GetMapping("/health/less/{health}")
    public List<Sidekick> heroesHealthLessThan(@PathVariable short health){
        return sidekickService.findAllSidekicksWhereHealthLessThanValue(health).orElse(new ArrayList<>());
    }

    @GetMapping("/health/equals/{health}")
    public List<Sidekick> heroesHealthEquals(@PathVariable short health){
        return sidekickService.findAllSidekicksWhereHealthEqualsValue(health).orElse(new ArrayList<>());
    }

    @GetMapping("/attack/equals/{attack}")
    public List<Sidekick> heroesAttackEquals(@PathVariable String attack){
        return sidekickService.findAllSidekicksByAttack(attack).orElse(new ArrayList<>());
    }

    @GetMapping("/move/greater/{move}")
    public List<Sidekick> heroesMoveGreaterThan(@PathVariable short move){
        return sidekickService.findAllSidekicksWhereMoveGreaterThanValue(move).orElse(new ArrayList<>());
    }

    @GetMapping("/move/less/{move}")
    public List<Sidekick> heroesMoveLessThan(@PathVariable short move){
        return sidekickService.findAllSidekicksWhereMoveLessThanValue(move).orElse(new ArrayList<>());
    }

    @GetMapping("/move/equals/{move}")
    public List<Sidekick> heroesMoveEquals(@PathVariable short move){
        return sidekickService.findAllSidekicksWhereMoveEqualsValue(move).orElse(new ArrayList<>());
    }


    @PutMapping("/update{name}")
    public ResponseEntity<String> updateSidekick(@PathVariable String name, @RequestBody Sidekick sidekick) throws CantChangeIdException {
        Sidekick currentSidekick = sidekickService.findSidekick(name).orElseThrow();
        if (!Objects.equals(sidekick.getName(), currentSidekick.getName())){
            throw new CantChangeIdException("You can`t change ID");
        }
        currentSidekick.setHealth(sidekick.getHealth());
        currentSidekick.setAttack(sidekick.getAttack());
        currentSidekick.setMove(sidekick.getMove());
        currentSidekick.setNumberOfSidekicks(sidekick.getNumberOfSidekicks());

        return new ResponseEntity<>(CrudMessagesUtil.getUpdateString(sidekick), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> addSidekick(@PathVariable String name){
        if (!sidekickService.sidekickExists(name)){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getDeleteErrorString(new Sidekick()),
                    HttpStatus.BAD_REQUEST
            );
        }
        sidekickService.deleteSidekick(name);
        return new ResponseEntity<>(CrudMessagesUtil.getDeleteString(new Sidekick()), HttpStatus.OK);
    }
}
