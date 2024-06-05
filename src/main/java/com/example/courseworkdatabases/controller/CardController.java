package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.composite.CardID;
import com.example.courseworkdatabases.service.CardService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/boost/greater/{boost}")
    public List<Card> cardBoostGreater(@PathVariable short boost){
        return cardService.findAllWhereBoostGreaterThan(boost).orElse(new ArrayList<>());
    }

    @GetMapping("/boost/less/{boost}")
    public List<Card> cardBoostLess(@PathVariable short boost){
        return cardService.findAllWhereBoostLessThan(boost).orElse(new ArrayList<>());
    }

    @GetMapping("/boost/equals/{boost}")
    public List<Card> cardBoostEquals(@PathVariable short boost){
        return cardService.findAllWhereBoostEquals(boost).orElse(new ArrayList<>());
    }

    @GetMapping("/value/greater/{value}")
    public List<Card> cardValueGreater(@PathVariable short value){
        return cardService.findAllWhereValueGreaterThan(value).orElse(new ArrayList<>());
    }

    @GetMapping("/value/less/{value}")
    public List<Card> cardValueLess(@PathVariable short value){
        return cardService.findAllWhereValueLessThan(value).orElse(new ArrayList<>());
    }

    @GetMapping("/value/equals/{value}")
    public List<Card> cardValueEquals(@PathVariable short value){
        return cardService.findAllWhereValueEquals(value).orElse(new ArrayList<>());
    }

    @GetMapping("/type/equals/{type}")
    public List<Card> cardEqualsType(@PathVariable String type){
        return cardService.findCardsByType(type).orElse(new ArrayList<>());
    }

    @GetMapping("/unique")
    public List<Card> cardUnique(){
        return cardService.findAllUniqueCards().orElse(new ArrayList<>());
    }

    @GetMapping("/hasEffects")
    public List<Card> cardHasEffects(){
        return cardService.findAllWhereEffectExists().orElse(new ArrayList<>());
    }

    @GetMapping("/cards/hero/{heroName}")
    public List<Card> cardsByHero(@PathVariable String heroName){
        return cardService.findAllByHero(heroName).orElse(new ArrayList<>());
    }

    @GetMapping("/cards/sidekick/{sidekickName}")
    public List<Card> cardsBySidekick(@PathVariable String sidekickName){
        return cardService.findAllBySidekick(sidekickName).orElse(new ArrayList<>());
    }

    @GetMapping("/cards/{heroName}/greater/{number}")
    public List<Card> cardNumberValueGreater(@PathVariable String heroName, @PathVariable short number){
        return cardService.findAllByHeroCardNumberGreaterThan(heroName, number).orElse(new ArrayList<>());
    }

    @GetMapping("/cards/{heroName}/less/{number}")
    public List<Card> cardNumberValueLess(@PathVariable String heroName, @PathVariable short number){
        return cardService.findAllByHeroCardNumberLessThan(heroName, number).orElse(new ArrayList<>());
    }

    @GetMapping("/cards/{heroName}/equals/{number}")
    public List<Card> cardNumberValueEquals(@PathVariable String heroName, @PathVariable short number){
        return cardService.findAllByHeroCardNumberEquals(heroName, number).orElse(new ArrayList<>());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCard(@RequestBody Card card) {
        if (cardService.ifCardExist(card.getName(), card.getBoost())){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getCreateErrorString(card),
                    HttpStatus.BAD_REQUEST
            );
        }
        cardService.saveCard(card);
        cardService.setUniqueWhileAdding(card);
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(card), HttpStatus.OK);
    }

    @PostMapping("/addAll")
    public ResponseEntity<String> addAllCards(@RequestBody List<Card> cards) {
        for (Card card: cards) {
            System.out.println("Card: " + card);
            if (cardService.ifCardExist(card.getName(), card.getBoost())){
                return new ResponseEntity<>(
                        CrudMessagesUtil.getCreateErrorString(card),
                        HttpStatus.BAD_REQUEST
                );
            }
            Card updated = cardService.setUniqueWhileAdding(card);
            cardService.saveCard(updated);
        }
        return new ResponseEntity<>(CrudMessagesUtil.getCreateString(new ArrayList<Card>()), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCard(@RequestBody ObjectNode objectNode) {

        if (!cardService.ifCardExist(objectNode.get("name").asText(), (short) objectNode.get("boost").asInt())){
            return new ResponseEntity<>(
                    CrudMessagesUtil.getDeleteErrorString(new Card()),
                    HttpStatus.BAD_REQUEST
            );
        }
        cardService.deleteCard(objectNode.get("name").asText(), (short) objectNode.get("boost").asInt());
        return new ResponseEntity<>(CrudMessagesUtil.getDeleteString(new Card()), HttpStatus.OK);
    }

}
