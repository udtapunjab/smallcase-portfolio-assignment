package com.smallcase.smallcaseportfolioassignment.controller;

import com.smallcase.smallcaseportfolioassignment.model.Security;
import com.smallcase.smallcaseportfolioassignment.model.Trade;
import com.smallcase.smallcaseportfolioassignment.model.TradeInput;
import com.smallcase.smallcaseportfolioassignment.model.TradeUpdateInput;
import com.smallcase.smallcaseportfolioassignment.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;


    @GetMapping("/portfolio")
    public List<Security> getSecurities(){
        return portfolioService.getAllSecurities();
    }

    @GetMapping("/portfolio/{ticker}")
    public Security getSecurity(@PathVariable("ticker") String ticker){
        return portfolioService.getSecurityById(ticker);
    }

    @GetMapping("/trades")
    public List<Trade> getTrades(){
        return portfolioService.getAllTrades();
    }

    @PostMapping("/trade")
    public String doTrade(@RequestBody TradeInput tradeInput){

        Trade trade = new Trade();
        trade.setTradeType(tradeInput.getTradeType());
        trade.setQuantity(tradeInput.getQuantity());
        trade.setTicker(tradeInput.getTicker());
        trade.setPricePerShare(tradeInput.getPricePerShare());

        return portfolioService.addTrade(trade);
    }

    @PutMapping("/trade")
    public String updateTrade(@RequestBody TradeUpdateInput tradeInput){
        Trade trade = new Trade();
        trade.setTradeId(tradeInput.getTradeId());
        trade.setTradeType(tradeInput.getTradeType());
        trade.setQuantity(tradeInput.getQuantity());
        trade.setTicker(tradeInput.getTicker());
        trade.setPricePerShare(tradeInput.getPricePerShare());

        return portfolioService.updateTrade(trade);
    }

    @DeleteMapping("/trade/{tradeId}")
    public String deleteTrade(@PathVariable("tradeId") long tradeId){
        return portfolioService.deleteTrade(tradeId);
    }

    @GetMapping("/returns")
    public String getReturns(){
        return portfolioService.getReturns();
    }

}
