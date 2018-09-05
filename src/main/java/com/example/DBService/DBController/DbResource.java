package com.example.DBService.DBController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController

public class DbResource {
private Logger logger = LoggerFactory.getLogger(DbResource.class);
@Autowired
private StockQuoteRepository stockRepo;
@GetMapping("/db/{username}")
public List<Quote> stockQuoteName(@PathVariable("username")String username)
{
	return stockRepo.findByUserName(username);
}

@PostMapping("/db/add")
public String add(@RequestBody Quote quote)
{System.out.println(quote.getUserName()+""+quote.getQuote());
	Quote q=new Quote(quote.getUserName(),quote.getQuote());
	System.out.println(q);
	stockRepo.save(q);
	return new String("quote inserted successfully");
}

@GetMapping("/db/delete/{quote}")
public String delete(@PathVariable("quote") String quote)
{
	stockRepo.deleteAll(stockRepo.findByQuote(quote));
	return "record deleted successfully !!!!";
    
}
@GetMapping("/stats/{quote}")
public String info(@PathVariable("quote") String quote) throws IOException
{Stock stock=null;
	try {
		stock=YahooFinance.get(quote);
	    
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return stock.toString();

}
/*
@PostMapping("/db/modify/{userName}/{newQuote}")
public String update(@PathVariable("userName") String userName,@PathVariable("newQuote") String newQuote )
{
	System.out.println(userName +"    "+newQuote);
	logger.info(" "+stockRepo.modifyByUserName(newQuote, userName));
    return "record modified successfully...............";

}
	*/
@GetMapping("/db/find/{quote}")
public List<Quote> returnQuotes(@PathVariable("quote") String quote)
{
	logger.info("returning records matched against the quote passed as the parameter.................");
	return stockRepo.findAllByQuote(quote);

}
@GetMapping("/db/stocksInfo/{username}")
public List<Stock> returnStocksInfo(@PathVariable("username") String username)
{
	List<Stock> stocks=new ArrayList<>();
	List<Quote> quotes=stockRepo.findByUserName(username);
	for(Quote q:quotes)
	{
		try {
		stocks.add(YahooFinance.get(q.getQuote()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
return stocks;
}

}
