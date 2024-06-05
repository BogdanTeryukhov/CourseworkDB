package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.connecter.HeroCard;
import com.example.courseworkdatabases.exception.NoHeroOrCardAddedException;
import com.example.courseworkdatabases.service.connecter.HeroCardService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/herocard")
public class HeroCardController {
    @Autowired
    private HeroCardService heroCardService;

    @PostMapping("/connect")
    public ResponseEntity<String> connectHeroAndCard(@RequestBody HeroCard heroCard){
        heroCard.setId(heroCardService.getMaxHeroCardId() + 1);
        try {
            heroCardService.saveHeroCard(heroCard);
        }
        catch (NoHeroOrCardAddedException e) {
            return new ResponseEntity<>("No hero or card added", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(heroCard), HttpStatus.OK);
    }

    @PostMapping("/connectAll")
    public ResponseEntity<String> connectAllHeroAndCard(@RequestBody List<HeroCard> heroCards){
        for (HeroCard heroCard: heroCards) {
            heroCard.setId(heroCardService.getMaxHeroCardId() + 1);
            try {
                heroCardService.saveHeroCard(heroCard);
            }
            catch (NoHeroOrCardAddedException e) {
                return new ResponseEntity<>("No hero or card added", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(new ArrayList<HeroCard>()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{heroName}/{cardName}")
    public ResponseEntity<String> deleteConnection(@PathVariable String heroName, @PathVariable String cardName) {
        if (!heroCardService.heroCardExists(heroName, cardName)){
            return new ResponseEntity<>(CrudMessagesUtil.getDeleteErrorString(new HeroCard()), HttpStatus.BAD_REQUEST);
        }
        heroCardService.deleteHeroCard(heroName, cardName);
        return new ResponseEntity<>(CrudMessagesUtil.getDeleteString(new HeroCard()), HttpStatus.OK);
    }
}
