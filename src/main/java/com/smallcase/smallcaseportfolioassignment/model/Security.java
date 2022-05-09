package com.smallcase.smallcaseportfolioassignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Security {
    @Column @Id
    private String ticker;

    @Column
    private double averageBuyPrice;

    @Column
    private int numberOfShares;

    public Security() {
    }

    public Security(String ticker, double averageBuyPrice, int numberOfShares) {
        this.ticker = ticker;
        this.averageBuyPrice = averageBuyPrice;
        this.numberOfShares = numberOfShares;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }
}
