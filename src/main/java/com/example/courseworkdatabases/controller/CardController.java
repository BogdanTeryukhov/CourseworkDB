package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Card;
import com.example.courseworkdatabases.entity.composite.CardID;
import com.example.courseworkdatabases.service.CardService;
import com.example.courseworkdatabases.util.CrudMessagesUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

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
