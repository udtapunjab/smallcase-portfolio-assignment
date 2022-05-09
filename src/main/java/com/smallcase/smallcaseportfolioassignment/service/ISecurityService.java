package com.smallcase.smallcaseportfolioassignment.service;

import com.smallcase.smallcaseportfolioassignment.model.Security;

import java.util.List;

public interface ISecurityService {
    List<Security> findAll();
    Security findByTicker(String ticker);
    void saveOrUpdate(Security security);
}
