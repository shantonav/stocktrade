package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

    @Query("Select s from StockTrade s where s.type=:type order by id asc")
     List<StockTrade> findAllStockTradesByType(
            @Param("type") String type  );

    @Query("Select s from StockTrade s where s.userId=:userId order by id asc")
    List<StockTrade> findAllStockTradesByUserId(
            @Param("userId") Integer userId  );

    @Query("Select s from StockTrade s where s.type = :type and s.userId= :userId order by id asc")
    List<StockTrade> findAllStockTradesByTypeAndUserId(
            @Param("type") String type ,
            @Param("userId") Integer userId  );
}
