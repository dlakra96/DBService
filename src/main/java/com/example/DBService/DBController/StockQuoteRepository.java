package com.example.DBService.DBController;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
public interface StockQuoteRepository extends JpaRepository<Quote,Integer>{

 
@Modifying
@Transactional
@Query("update Quote q set q.quote = ?1 where q.userName = ?2")
int modifyByUserName(String quote,String userName);
List<Quote> findAllByQuote(String quote);	
List<Quote> findByUserName(String username);
List<Quote> findByQuote(String quote);

}
