package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;
    public Long findCorrectId(){
        return cardService.getMaxCardId() + 1;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCard(@RequestBody Card card) {
        if (cardService.ifCardExist(card.getName(), card.getBoost())){
            return new ResponseEntity<>(
                    "Card is already exists! You can`t make another card with same name and boost value",
                    HttpStatus.BAD_REQUEST
            );
        }
        card.setId(findCorrectId());
        cardService.saveCard(card);
        cardService.setUniqueWhileAdding(card);
        return new ResponseEntity<>("Card has been added", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}/{boost}")
    public ResponseEntity<String> deleteCard(@PathVariable String name, @PathVariable short boost) {
        if (!cardService.ifCardExist(name, boost)){
            return new ResponseEntity<>(
                    "Card is not exists!",
                    HttpStatus.BAD_REQUEST
            );
        }
        cardService.deleteCard(name, boost);
        return new ResponseEntity<>("Card has been deleted", HttpStatus.OK);
    }
}
