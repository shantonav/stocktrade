package com.hackerrank.stocktrades.model;

import com.hackerrank.stocktrades.validation.StockType;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class StockTrade {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "seq" )
    private Integer id;

    @StockType
    private String type;
    private Integer userId;
    private String symbol;
    private Integer shares;
    private Integer price;
    private Long timestamp;

    public StockTrade() {
    }

    public StockTrade(Integer id, String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = timestamp;
    }

    public StockTrade(String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
        this.type = type;
        this.userId = userId;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    @Min( 1 )
    @Max( 100 )
    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockTrade that = (StockTrade) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(shares, that.shares) &&
                Objects.equals(price, that.price) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, userId, symbol, shares, price, timestamp);
    }
}
