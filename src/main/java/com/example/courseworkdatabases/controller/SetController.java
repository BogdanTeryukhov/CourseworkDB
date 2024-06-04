package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Set;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.SetService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/set")
public class SetController {
    @Autowired
    private SetService setService;

    @PostMapping("/add")
    public ResponseEntity<String> addSet(@RequestBody Set set) {
        if (setService.setExists(set.getName())){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getCreateErrorString(set),
                    HttpStatus.BAD_REQUEST
            );
        }
        setService.saveSet(set);
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(set), HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateSet(@PathVariable String name, @RequestBody Set set) throws CantChangeIdException {
        Set currentSet = setService.findSet(name).orElseThrow();
        if (!Objects.equals(set.getName(), currentSet.getName())){
            throw new CantChangeIdException("You can`t change id");
        }

        int numOfHeroes = setService.findHeroesBySetName(name).isPresent() ? setService.findHeroesBySetName(name).get().size() : 0;
        if (numOfHeroes > set.getNumberOfHeroes()){
            return new ResponseEntity<>(
                    "Number of heroes in set should be more or equal than heroes!", HttpStatus.BAD_REQUEST
            );
        }
        currentSet.setNumberOfHeroes(set.getNumberOfHeroes());

        int numOfMaps = setService.findMapsBySetName(name).isPresent() ? setService.findMapsBySetName(name).get().size() : 0;
        if (numOfMaps > set.getNumberOfMaps()){
            return new ResponseEntity<>(
                    "Number of maps in set should be more or equal than maps!", HttpStatus.BAD_REQUEST
            );
        }
        currentSet.setNumberOfMaps(set.getNumberOfMaps());
        setService.saveSet(set);
        return new ResponseEntity<>(
                CrudMessagesUtil.getUpdateString(set), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteSet(@PathVariable String name) {
        if (!setService.setExists(name)){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getDeleteErrorString(new Set()), HttpStatus.BAD_REQUEST
            );
        }
        setService.deleteSetByName(name);
        return new ResponseEntity<>(CrudMessagesUtil.getDeleteString(new Set()), HttpStatus.OK);
    }
}
