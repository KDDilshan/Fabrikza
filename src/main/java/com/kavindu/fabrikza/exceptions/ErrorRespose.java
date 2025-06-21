package com.kavindu.fabrikza.exceptions;

import lombok.Getter;

@Getter
public class ErrorRespose {

    private String message;

    public ErrorRespose(String message) {
        this.message = message;
    }
}
