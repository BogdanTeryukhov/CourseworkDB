package com.example.courseworkdatabases.exception;

import java.io.IOException;

public class CantChangeIdException extends IOException {
    public CantChangeIdException(String message) {
        super(message);
    }
}
