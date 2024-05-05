package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.cards.AttackCard;
import com.example.courseworkdatabases.entity.cards.DefenseCard;
import com.example.courseworkdatabases.entity.cards.SchemeCard;
import com.example.courseworkdatabases.entity.cards.VersatileCard;
import com.example.courseworkdatabases.service.cards.AttackCardService;
import com.example.courseworkdatabases.service.cards.DefenseCardService;
import com.example.courseworkdatabases.service.cards.SchemeCardService;
import com.example.courseworkdatabases.service.cards.VersatileCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;

@RestController
public class CardController {
    @Autowired
    private AttackCardService attackCardService;

    @Autowired
    private DefenseCardService defenseCardService;

    @Autowired
    private VersatileCardService versatileCardService;

    @Autowired
    private SchemeCardService schemeCardService;

    public Long maxID(Long...values){
        return Arrays.stream(values).max(Comparator.naturalOrder()).orElse(1L);
    }

    public Long findCorrectId(){
        return maxID(attackCardService.getMaxAttackCardId(), defenseCardService.getMaxDefenseCardId(),
                schemeCardService.getMaxSchemeCardId(), versatileCardService.getMaxVersatileCardId()) + 1;
    }

    @PostMapping("/card/addAttack")
    public String addAttackCard(@RequestBody AttackCard attackCard) {
        attackCard.setId(findCorrectId());
        attackCardService.saveAttackCard(attackCard);
        return "Attack card has been added";
    }

    @PostMapping("/card/addDefense")
    public String addDefenseCard(@RequestBody DefenseCard defenseCard) {
        defenseCard.setId(findCorrectId());
        defenseCardService.saveDefenseCard(defenseCard);
        return "Defense card has been added";
    }

    @PostMapping("/card/addVersatile")
    public String addVersatileCard(@RequestBody VersatileCard versatileCard) {
        versatileCard.setId(findCorrectId());
        System.out.println(versatileCard);
        versatileCardService.saveVersatileCard(versatileCard);
        return "Versatile card has been added";
    }

    @PostMapping("/card/addScheme")
    public String addSchemeCard(@RequestBody SchemeCard schemeCard) {
        schemeCard.setId(findCorrectId());
        schemeCardService.saveSchemeCard(schemeCard);
        return "Scheme card has been added";
    }

    @DeleteMapping("/card/delete/{name}/{boost}")
    public String deleteAttackCard(@PathVariable String name, @PathVariable int boost) {
        if (attackCardService.findAttackCard(name, boost) != null) {
            attackCardService.deleteAttackCard(name, boost);
        }
        else if (defenseCardService.findDefenseCard(name, boost) != null) {
            defenseCardService.deleteDefenseCardById(name, boost);
        }
        else if (versatileCardService.findVersatileCard(name, boost) != null) {
            versatileCardService.deleteVersatileCardByNameAndBoost(name, boost);
        }
        else if (schemeCardService.findSchemeCard(name, boost) != null) {
            schemeCardService.deleteSchemeCardByNameAndBoost(name, boost);
        }
        return "Card has been deleted";
    }
}
