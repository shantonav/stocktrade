package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StockTradeDBService {

    @Autowired
    private StockTradeRepository repo;

    public StockTrade createNewStockTrade(StockTrade newStockTrade ){
        return repo.save( newStockTrade );
    }

    private List<StockTrade> findAllStockTrades(){
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<StockTrade> findAllStockTradesByTypeAndOrUserID(String type, Integer userId){
        if ( type != null && userId == null){
            return repo.findAllStockTradesByType( type );
        }else if ( type == null  && userId != null ){
            return repo.findAllStockTradesByUserId( userId );
        }else if ( type != null && userId != null){
            return repo.findAllStockTradesByTypeAndUserId( type, userId );
        }else{
            return this.findAllStockTrades();
        }
    }

    public Optional<StockTrade> findTheStockTrade(Integer id){
        return repo.findById( id );
    }




}
