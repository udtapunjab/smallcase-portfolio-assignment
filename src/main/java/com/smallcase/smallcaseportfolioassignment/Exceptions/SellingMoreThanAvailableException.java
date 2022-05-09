package com.smallcase.smallcaseportfolioassignment.Exceptions;

public class SellingMoreThanAvailableException extends RuntimeException{
    String message;

    public SellingMoreThanAvailableException() {
    }

    public SellingMoreThanAvailableException(String message) {
        super(message);
        this.message = message;
    }
}
