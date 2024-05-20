package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.repository.reposView.HeroMapViewRepository;
import com.example.courseworkdatabases.service.HeroService;
import com.example.courseworkdatabases.entity.view.HeroMapView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/hero")
public class HeroController {
    @Autowired
    private HeroService heroService;
    @Autowired
    private HeroMapViewRepository heroMapViewRepository;

    @GetMapping("/get")
    public List<Hero> heroes(){
        return heroService.findAllHeroes();
    }

    @GetMapping("/map")
    public List<HeroMapView> heroMapsView(){
        return heroMapViewRepository.findAll();
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
        return heroService.findAllHeroesBySetName(setName);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewHero(@RequestBody Hero hero){
        if (heroService.heroExists(hero.getName())){
            return new ResponseEntity<>(
                    "Hero is already exists! You can`t make another hero with same name",
                    HttpStatus.BAD_REQUEST
            );
        }
        hero.setId(heroService.findCorrectHeroId() + 1);
        heroService.saveHero(hero);
        return new ResponseEntity<>("Hero has been saved", HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public String editHero(@PathVariable String name, @RequestBody Hero hero) throws CantChangeIdException {
        Hero currentHero = heroService.findHeroByName(name).orElseThrow();
        if (!Objects.equals(currentHero.getId(), hero.getId())){
            throw new CantChangeIdException("You can`t change the ID");
        }

        currentHero.setName(currentHero.getName());
        currentHero.setHealth(hero.getHealth() == 0 ? currentHero.getHealth() : hero.getHealth());
        currentHero.setMove(hero.getMove() == 0 ? currentHero.getMove() : hero.getMove());
        currentHero.setAbility(hero.getAbility() == null ? currentHero.getAbility() : hero.getAbility());
        currentHero.setAttack(hero.getAttack() == null ? currentHero.getAttack() : hero.getAttack());
        currentHero.setSidekickName(hero.getSidekickName() == null ?
                currentHero.getSidekickName() : hero.getSidekickName());
        currentHero.setNumberOfSidekicks(hero.getNumberOfSidekicks() == 0 ?
                currentHero.getNumberOfSidekicks() : hero.getNumberOfSidekicks());
        currentHero.setSetName(hero.getSetName() == null ?
                currentHero.getSetName() : hero.getSetName());
        heroService.saveHero(currentHero);
        return "Hero has been edited";
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteHero(@PathVariable String name){
        if (!heroService.heroExists(name)){
            return new ResponseEntity<>(
                    "Hero is not exists!",
                    HttpStatus.BAD_REQUEST
            );
        }
        heroService.deleteHeroByName(name);
        return new ResponseEntity<>("Hero has been deleted", HttpStatus.OK);
    }
}
