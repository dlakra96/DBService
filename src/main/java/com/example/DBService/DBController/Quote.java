package com.example.DBService.DBController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quote",catalog="deepDB")

public class Quote {
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
@Column(name="id")
private Integer id;

@Column(name="user_name")
private String userName;

@Column(name="quote")
private String quote;

public Quote() {
	// TODO Auto-generated constructor stub
}

public Quote(String username, String quote) {

	this.userName = username;
	this.quote = quote;
}

public Integer getId() {
	return id;
}

public void setId(Integer qid) {
	this.id = qid;
}

public String getUserName() {
	return userName;
}

public void setUserName(String username) {
	this.userName = username;
}

public String getQuote() {
	return quote;
}

public void setQuote(String quote) {
	this.quote = quote;
}

}
