package com.smallcase.smallcaseportfolioassignment.service;

import com.smallcase.smallcaseportfolioassignment.model.Security;
import com.smallcase.smallcaseportfolioassignment.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements ISecurityService{

    @Autowired
    SecurityRepository repository;

    @Override
    public List<Security> findAll() {
        ArrayList<Security> securities = new ArrayList<>();
        repository.findAll().forEach(securities::add);
        return securities;
    }

    @Override
    public Security findByTicker(String ticker) {
        return repository.findById(ticker).get();
    }

    @Override
    public void saveOrUpdate(Security security) {
        repository.save(security);
    }
}
