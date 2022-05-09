package com.smallcase.smallcaseportfolioassignment.Exceptions;

public class NoTradePresentWithGivenTradeIdException extends RuntimeException{
    String message;

    public NoTradePresentWithGivenTradeIdException() {
    }

    public NoTradePresentWithGivenTradeIdException(String message) {
        super(message);
        this.message = message;
    }
}
