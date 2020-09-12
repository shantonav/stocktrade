package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.model.exception.BadRequestException;
import com.hackerrank.stocktrades.model.exception.DataNotFoundException;
import com.hackerrank.stocktrades.service.StockTradeDBService;
import com.hackerrank.stocktrades.validation.StockType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@RestController
public class StockTradeRestController {
    @Autowired
    private StockTradeDBService service;

    @PostMapping("trades")
    @ResponseStatus(HttpStatus.CREATED)
    public StockTrade createStockTrade(
            @Valid
            @RequestBody final StockTrade stockTrade) throws BadRequestException {
        if (stockTrade.getId() != null) {
            throw new BadRequestException("New trade must not have a id in it");
        }

        return service.createNewStockTrade(stockTrade);
    }

    @GetMapping("trades")
    @ResponseStatus(HttpStatus.OK)
    public List<StockTrade> getStockTrades(
            @Valid
            @StockType
            @RequestParam(required = false, name = "type") String stockType,
            @RequestParam(required = false, name = "userId") Integer userId) {
        return service.findAllStockTradesByTypeAndOrUserID(stockType, userId);
    }

    @GetMapping("trades/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockTrade getStockTradeById(
            @PathVariable(name = "id") Integer id) throws DataNotFoundException {

        Optional<StockTrade> stockTradePlaceHolder
                = service.findTheStockTrade(id);

        if (stockTradePlaceHolder.isPresent()) {
            return stockTradePlaceHolder.get();
        } else {
            throw new DataNotFoundException(" Stock trade with id " + id + " not found");
        }

    }
}

