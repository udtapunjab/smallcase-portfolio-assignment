package com.smallcase.smallcaseportfolioassignment.model;

public class TradeUpdateInput extends TradeInput{
    private Long tradeId;

    public TradeUpdateInput(String tradeType, String ticker, int quantity, double pricePerShare, Long tradeId) {
        super(tradeType, ticker, quantity, pricePerShare);
        this.tradeId = tradeId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }
}
