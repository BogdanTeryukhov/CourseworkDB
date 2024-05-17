package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.exception.CantChangeIdException;
import com.example.courseworkdatabases.repository.reposView.HeroMapViewRepository;
import com.example.courseworkdatabases.service.HeroService;
import com.example.courseworkdatabases.entity.view.HeroMapView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class HeroController {
    @Autowired
    private HeroService heroService;
    @Autowired
    private HeroMapViewRepository heroMapViewRepository;

    @GetMapping("/heroes")
    public List<Hero> heroAdd(){
        return heroService.findAllHeroes();
    }

    @GetMapping("/heroMapView")
    public List<HeroMapView> heroMapsView(){
        return heroMapViewRepository.findAll();
    }

    @GetMapping("/hero/health/greater/{health}")
    public List<Hero> heroesHealthGreaterThan(@PathVariable short health){
        return heroService.findAllHeroesWhereHealthGreaterThanValue(health);
    }

    @GetMapping("/hero/health/less/{health}")
    public List<Hero> heroesHealthLessThan(@PathVariable short health){
        return heroService.findAllHeroesWhereHealthLessThanValue(health);
    }

    @GetMapping("/hero/health/equals/{health}")
    public List<Hero> heroesHealthEquals(@PathVariable short health){
        return heroService.findAllHeroesWhereHealthEqualsValue(health);
    }

    @GetMapping("/hero/attack/equals/{attack}")
    public List<Hero> heroesAttackEquals(@PathVariable String attack){
        return heroService.findAllHeroesByAttack(attack);
    }

    @GetMapping("/hero/move/greater/{move}")
    public List<Hero> heroesMoveGreaterThan(@PathVariable short move){
        return heroService.findAllHeroesWhereMoveGreaterThanValue(move);
    }

    @GetMapping("/hero/move/less/{move}")
    public List<Hero> heroesMoveLessThan(@PathVariable short move){
        return heroService.findAllHeroesWhereMoveLessThanValue(move);
    }

    @GetMapping("/hero/move/equals/{move}")
    public List<Hero> heroesMoveEquals(@PathVariable short move){
        return heroService.findAllHeroesWhereMoveEqualsValue(move);
    }

    @GetMapping("/hero/sidekicks/greater/{numberOfSidekicks}")
    public List<Hero> heroesSidekicksGreaterThan(@PathVariable short numberOfSidekicks){
        return heroService.findAllHeroesWhereNumberOfSidekicksGreaterThanValue(numberOfSidekicks);
    }

    @GetMapping("/hero/sidekicks/less/{numberOfSidekicks}")
    public List<Hero> heroesSidekicksLessThan(@PathVariable short numberOfSidekicks){
        return heroService.findAllHeroesWhereNumberOfSidekicksLessThanValue(numberOfSidekicks);
    }

    @GetMapping("/hero/sidekicks/equals/{numberOfSidekicks}")
    public List<Hero> heroesSidekicksEquals(@PathVariable short numberOfSidekicks){
        return heroService.findAllHeroesWhereNumberOfSidekicksEqualsValue(numberOfSidekicks);
    }

    @GetMapping("/hero/setname/equals/{setName}")
    public List<Hero> heroesSetNameEquals(@PathVariable String setName){
        return heroService.findAllHeroesBySetName(setName);
    }

    @PostMapping("/hero/add")
    public String addNewHero(@RequestBody Hero hero){
        hero.setId(heroService.findCorrectHeroId() + 1);
        heroService.saveHero(hero);
        return "Hero has been saved";
    }

    @PutMapping("/hero/update/{name}")
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

    @DeleteMapping("/hero/delete/{name}")
    public String deleteHero(@PathVariable String name){
        heroService.deleteHeroByName(name);
        return "Hero has been deleted";
    }
}
