package com.smallcase.smallcaseportfolioassignment.model;

import javax.persistence.Column;

public class TradeInput {
    private String tradeType;
    private String ticker;
    private int quantity;
    private double pricePerShare;

    public TradeInput(String tradeType, String ticker, int quantity, double pricePerShare) {
        this.tradeType = tradeType;
        this.ticker = ticker;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
    }

    public TradeInput() {
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
}
