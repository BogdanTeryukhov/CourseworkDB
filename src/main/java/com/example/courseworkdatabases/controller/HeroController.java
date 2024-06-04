package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.service.HeroService;
import com.example.courseworkdatabases.service.SidekickService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping("/get")
    public List<Hero> heroes(){
        return heroService.findAllHeroes();
    }


    @GetMapping("/health/greater/{health}")
    public List<Hero> heroesHealthGreaterThan(@PathVariable short health){
        return heroService.findAllHeroesWhereHealthGreaterThanValue(health);
    }

    @GetMapping("/health/less/{health}")
    public List<Hero> heroesHealthLessThan(@PathVariable short health){
        return heroService.findAllHeroesWhereHealthLessThanValue(health);
    }

    @GetMapping("/health/equals/{health}")
    public List<Hero> heroesHealthEquals(@PathVariable short health){
        return heroService.findAllHeroesWhereHealthEqualsValue(health);
    }

    @GetMapping("/attack/equals/{attack}")
    public List<Hero> heroesAttackEquals(@PathVariable String attack){
        return heroService.findAllHeroesByAttack(attack);
    }

    @GetMapping("/move/greater/{move}")
    public List<Hero> heroesMoveGreaterThan(@PathVariable short move){
        return heroService.findAllHeroesWhereMoveGreaterThanValue(move);
    }

    @GetMapping("/move/less/{move}")
    public List<Hero> heroesMoveLessThan(@PathVariable short move){
        return heroService.findAllHeroesWhereMoveLessThanValue(move);
    }

    @GetMapping("/move/equals/{move}")
    public List<Hero> heroesMoveEquals(@PathVariable short move){
        return heroService.findAllHeroesWhereMoveEqualsValue(move);
    }

    @GetMapping("/sidekicks/greater/{numberOfSidekicks}")
    public List<Hero> heroesSidekicksGreaterThan(@PathVariable short numberOfSidekicks){
        return heroService.findAllHeroesWhereNumberOfSidekicksGreaterThanValue(numberOfSidekicks);
    }

    @GetMapping("/sidekicks/less/{numberOfSidekicks}")
    public List<Hero> heroesSidekicksLessThan(@PathVariable short numberOfSidekicks){
        return heroService.findAllHeroesWhereNumberOfSidekicksLessThanValue(numberOfSidekicks);
    }

    @GetMapping("/sidekicks/equals/{numberOfSidekicks}")
    public List<Hero> heroesSidekicksEquals(@PathVariable short numberOfSidekicks){
        return heroService.findAllHeroesWhereNumberOfSidekicksEqualsValue(numberOfSidekicks);
    }

    @GetMapping("/setname/equals/{setName}")
    public List<Hero> heroesSetNameEquals(@PathVariable String setName){
        return heroService.findAllHeroesBySetName(setName).get();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewHero(@RequestBody Hero hero){
        if (heroService.heroExists(hero.getName())){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getCreateErrorString(hero),
                    HttpStatus.BAD_REQUEST
            );
        }
        heroService.saveHero(hero);
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(hero), HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateHero(@PathVariable String name, @RequestBody Hero hero) throws CantChangeIdException {
        heroService.updateHero(name, hero);
        return new ResponseEntity<>(CrudMessagesUtil.getUpdateString(hero), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteHero(@PathVariable String name){
        if (!heroService.heroExists(name)){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getDeleteErrorString(new Hero()),
                    HttpStatus.BAD_REQUEST
            );
        }
        heroService.deleteHeroByName(name);
        return new ResponseEntity<>(CrudMessagesUtil.getDeleteString(new Hero()), HttpStatus.OK);
    }
}
