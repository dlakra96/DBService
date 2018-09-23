package com.example.DBService.DBController;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.UserDetails;

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
@RequestMapping(method=RequestMethod.GET,value="/login")
public ModelAndView returnLoginPage(@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false)String logout)
{ModelAndView model=new ModelAndView();
if (error != null) {
	model.addObject("error", "Invalid username and password!");
  }

  if (logout != null) {
	model.addObject("msg", "You've been logged out successfully.");
  }
  model.setViewName("login");
  return model;
}
@RequestMapping(method=RequestMethod.GET,value="/403")
public ModelAndView accessDenied()
{
ModelAndView model = new ModelAndView();
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if(!(auth instanceof AnonymousAuthenticationToken))
{
UserDetails userDetail = (UserDetails) auth.getPrincipal();	
model.addObject("username", userDetail.getUsername());
}
model.setViewName("403");
return model;
}
@RequestMapping(method=RequestMethod.GET,value="/admin**")
public ModelAndView adminPage()
{
	ModelAndView model = new ModelAndView();
	model.addObject("title","Spring Security Login Form - Database Authentication");
	model.addObject("message","This page is for ROLE_ADMIN only !!!");
    model.setViewName("admin");
return model;
}
@RequestMapping(value = "/", method = RequestMethod.GET)
public ModelAndView defaultPage() {

  ModelAndView model = new ModelAndView();
  model.addObject("title", "Spring Security Login Form - Database Authentication");
  model.addObject("message", "This is default page!");
  model.setViewName("hello");
  return model;

}

}
