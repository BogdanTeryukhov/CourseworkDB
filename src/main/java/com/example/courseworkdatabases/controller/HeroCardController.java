package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.service.connecter.HeroCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/herocard")
public class HeroCardController {
    @Autowired
    private HeroCardService heroCardService;

    @PostMapping("/connect")
    public String connectHeroAndCard(@RequestBody HeroCard heroCard){
        System.out.println(heroCard);
        heroCard.setId(heroCardService.getMaxHeroCardId() + 1);
        try {
            heroCardService.saveHeroCard(heroCard);
        } catch (Exception e) {
            System.out.println("No hero or card added");
        }
        return "HeroCard relation has been added";
    }

    @DeleteMapping("/delete/{heroName}/{cardName}")
    public String deleteConnection(@PathVariable String heroName, @PathVariable String cardName) {
        heroCardService.deleteHeroCard(heroName, cardName);
        return "HeroCard relation has been deleted";
    }
}
