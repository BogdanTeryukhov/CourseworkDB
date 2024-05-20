package com.example.courseworkdatabases.service;

import com.example.courseworkdatabases.entity.Card;
import org.springframework.stereotype.Component;

@Component
public interface CardService {
    void saveCard(Card card);
    void setUniqueWhileAdding(Card card);
    void deleteCard(String name, short boost);
    boolean ifCardExist(String name, short boost);
    Long getMaxCardId();
}
