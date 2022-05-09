package com.smallcase.smallcaseportfolioassignment.service;

import com.smallcase.smallcaseportfolioassignment.Exceptions.IncorrectTradeTypeException;
import com.smallcase.smallcaseportfolioassignment.Exceptions.NoSecurityAvailableInPortfolioException;
import com.smallcase.smallcaseportfolioassignment.Exceptions.NoTradePresentWithGivenTradeIdException;
import com.smallcase.smallcaseportfolioassignment.Exceptions.SellingMoreThanAvailableException;
import com.smallcase.smallcaseportfolioassignment.model.Security;
import com.smallcase.smallcaseportfolioassignment.model.Trade;
import com.smallcase.smallcaseportfolioassignment.repository.SecurityRepository;
import com.smallcase.smallcaseportfolioassignment.repository.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService implements IPortfolioService{

    private final Logger logger = LoggerFactory.getLogger(PortfolioService.class);
    private final long CURRENT_PRICE = 100L;

    @Autowired
    TradeRepository tradeRepo;

    @Autowired
    SecurityRepository securityRepo;

    @Override
    public List<Trade> getAllTrades() {
        ArrayList<Trade> trades= new ArrayList<>();
        tradeRepo.findAll().forEach(trades::add);
        return trades;
    }

    @Override
    public Trade getTradeById(Long tradeId) {
        if(tradeRepo.findById(tradeId).isPresent()) return tradeRepo.findById(tradeId).get();
        else return null;
    }

    @Override
    public String addTrade(Trade trade) {


        if("SELL".equals(trade.getTradeType())){
            Security security = securityRepo.findById(trade.getTicker()).orElseThrow(() -> new NoSecurityAvailableInPortfolioException("No Security for this ticker available in portfolio"));

            if(security.getNumberOfShares()<trade.getQuantity()){
                return "Invalid Trade : Selling more shares than available";
            }

            security.setNumberOfShares(security.getNumberOfShares() - trade.getQuantity());
            securityRepo.save(security);
            tradeRepo.save(trade);
            return "SELL successful : tradeId= "+trade.getTradeId();
        }
        else if("BUY".equals(trade.getTradeType())){
            Optional<Security> securityOpt = securityRepo.findById(trade.getTicker());

            if(securityOpt.isPresent()){
                Security security = securityOpt.get();

                double newAveragePrice = (security.getNumberOfShares()*security.getAverageBuyPrice() +
                        trade.getQuantity()*trade.getPricePerShare())/(security.getNumberOfShares()+trade.getQuantity());

                security.setNumberOfShares(security.getNumberOfShares()+trade.getQuantity());
                security.setAverageBuyPrice(newAveragePrice);

                securityRepo.save(security);
                tradeRepo.save(trade);
                return "BUY successful : tradeId= "+trade.getTradeId();
            }
            else{
                Security security = new Security(trade.getTicker(), trade.getPricePerShare(), trade.getQuantity());
                securityRepo.save(security);
                tradeRepo.save(trade);
                return "BUY successful: tradeId= "+trade.getTradeId();
            }
        }

        throw new IncorrectTradeTypeException("Incorrect Trade Type = " + trade.getTradeType());
    }

    @Override
    public String updateTrade(Trade trade) {
        Trade oldTrade = tradeRepo.findById(trade.getTradeId()).orElseThrow(() -> new NoTradePresentWithGivenTradeIdException("No Trade present for given tradeId"));

        Security security = securityRepo.findById(trade.getTicker()).orElseThrow(() -> new NoSecurityAvailableInPortfolioException("No Security Present for given tradeId"));

        //delete the old trade
        if("BUY".equals(oldTrade.getTradeType())){
            //sell the number of shares
            if(security.getNumberOfShares() < oldTrade.getQuantity()) {
                throw new SellingMoreThanAvailableException("Deleting a trade not possible now : not enough shares available");
                //return "Deleting a trade not possible now : not enough shares available";
            }

            double newAveragePrice = (security.getAverageBuyPrice()*security.getNumberOfShares() - oldTrade.getPricePerShare()*oldTrade.getQuantity())
                    /(security.getNumberOfShares()-oldTrade.getQuantity());

            security.setAverageBuyPrice(newAveragePrice);
            security.setNumberOfShares(security.getNumberOfShares() - oldTrade.getQuantity());

            if(security.getNumberOfShares() == 0){
                securityRepo.delete(security);
            }
            else{
                securityRepo.save(security);
            }
        }
        else{
            //increase the number of shares
            Trade t1 = oldTrade.clone();
            t1.setTradeType("BUY");
            this.addTrade(t1);
        }


        // add the updated trade

        return this.addTrade(trade);
    }

    @Override
    public String deleteTrade(Long tradeId) {
        Trade trade = tradeRepo.findById(tradeId).orElseThrow(() -> new NoTradePresentWithGivenTradeIdException("No trade with given TradeId present" + tradeId));

        Security security = securityRepo.findById(trade.getTicker()).orElseThrow(() -> new NoSecurityAvailableInPortfolioException("No Security Present for given tradeId"));

        if("BUY".equals(trade.getTradeType())){
            //sell the number of shares
            if(security.getNumberOfShares() < trade.getQuantity()) {
                throw new SellingMoreThanAvailableException("Deleting a trade not possible now : not enough shares available");
                //return "Deleting a trade not possible now : not enough shares available";
            }

            double newAveragePrice = (security.getAverageBuyPrice()*security.getNumberOfShares() - trade.getPricePerShare()*trade.getQuantity())
                    /(security.getNumberOfShares()-trade.getQuantity());

            logger.info("AveragePrice: Before " +  newAveragePrice + " ; After : "  + security.getAverageBuyPrice());
            security.setAverageBuyPrice(newAveragePrice);
            logger.info("NumberOfShares: Before " +  security.getNumberOfShares() + " ; After : "  + (security.getNumberOfShares() - trade.getQuantity()));
            security.setNumberOfShares(security.getNumberOfShares() - trade.getQuantity());
            securityRepo.save(security);
        }
        else{
            //increase the number of shares
            Trade t1 = trade.clone();
            t1.setTradeType("BUY");
            this.addTrade(t1);
        }

        if(security.getNumberOfShares() == 0){
            securityRepo.delete(security);
        }

        tradeRepo.delete(trade);

        return "Deleted trade successfully : tradeId= "+trade.getTradeId();
    }

    @Override
    public List<Security> getAllSecurities() {
        ArrayList<Security> securities = new ArrayList<>();
        securityRepo.findAll().forEach(securities::add);
        return securities;
    }

    @Override
    public Security getSecurityById(String ticker) {
        return securityRepo.findById(ticker).orElseThrow(() -> new NoSecurityAvailableInPortfolioException("No Security for this ticker Id present"));
    }

    @Override
    public void update(Security security) {
        securityRepo.save(security);
    }

    @Override
    public String getReturns() {
        final long[] returnsSum = {0L};

        securityRepo.findAll().forEach(security -> {
            returnsSum[0] += (CURRENT_PRICE - security.getAverageBuyPrice())*security.getNumberOfShares();
        });

        return  String.valueOf(returnsSum[0]);
    }
}
/*
SUM((CURRENT_PRICE[ticker] - AVERAGE_BUY_PRICE[ticker]) *
CURRENT_QUANTITY[ticker])
 */