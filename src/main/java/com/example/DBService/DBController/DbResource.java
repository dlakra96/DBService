package com.example.DBService.DBController;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class DbResource {
private Logger logger = LoggerFactory.getLogger(DbResource.class);
@Autowired
private StockQuoteRepository stockRepo;
@RequestMapping(method=RequestMethod.GET,value="/quotes/{username}")
public String returnDisplay(@PathVariable("username") String username,Model model)
{
List<Quote> quotes=stockRepo.findByUserName(username);
List<String> quote_names=new ArrayList<>();
for(Quote q:quotes)
{
	quote_names.add(q.getQuote());
}
model.addAttribute("quotesList",quote_names);
Quote q1=new Quote();
q1.setUserName(username);
model.addAttribute("new_quote",q1);
logger.info("returning list of quotes corresponding to the username passed as url parameter");
return "input";
}


}
