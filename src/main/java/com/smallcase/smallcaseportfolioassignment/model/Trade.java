package com.smallcase.smallcaseportfolioassignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Trade implements Cloneable {

    @Column @Id
    private Long tradeId;

    @Column
    private LocalDateTime localDateTime;

    @Column
    private String tradeType;

    @Column
    private String ticker;

    @Column
    private int quantity;

    @Column
    private double pricePerShare;

    public Trade() {
        this.setLocalDateTime(LocalDateTime.now());
        this.tradeId= UUID.randomUUID().getLeastSignificantBits();
    }

    public Trade(Long tradeId, LocalDateTime localDateTime, String tradeType, String ticker, int quantity, double pricePerShare) {
        this.tradeId = tradeId;
        this.localDateTime = localDateTime;
        this.tradeType = tradeType;
        this.ticker = ticker;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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

    @Override
    public Trade clone() {
        try {
            Trade clone = (Trade) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
