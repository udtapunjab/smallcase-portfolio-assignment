package com.smallcase.smallcaseportfolioassignment.Exceptions;

/**
 *
 */
public class NoSecurityAvailableInPortfolioException extends RuntimeException{
    String message;

    public NoSecurityAvailableInPortfolioException() {
    }

    public NoSecurityAvailableInPortfolioException(String message) {
        super(message);
        this.message = message;
    }
}

