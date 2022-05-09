package com.smallcase.smallcaseportfolioassignment.service;

import com.smallcase.smallcaseportfolioassignment.model.Security;
import com.smallcase.smallcaseportfolioassignment.model.Trade;

import java.util.List;

public interface IPortfolioService {
    List<Trade> getAllTrades();
    Trade getTradeById(Long tradeId);
    String addTrade(Trade trade);
    String updateTrade(Trade trade);
    String deleteTrade(Long tradeId);

    List<Security> getAllSecurities();
    Security getSecurityById(String ticker);
    void update(Security security);

    String getReturns();
}
