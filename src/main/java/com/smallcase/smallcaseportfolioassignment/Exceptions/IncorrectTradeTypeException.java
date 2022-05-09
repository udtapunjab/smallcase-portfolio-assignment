package com.smallcase.smallcaseportfolioassignment.Exceptions;

public class IncorrectTradeTypeException extends RuntimeException{
    String message;

    public IncorrectTradeTypeException() {
    }

    public IncorrectTradeTypeException(String message) {
        super(message);
        this.message = message;
    }
}
