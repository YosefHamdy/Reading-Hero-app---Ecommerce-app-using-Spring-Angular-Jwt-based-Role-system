package com.example.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTBOOK")
@NamedQuery(name = "joinView", query = "Select c from TransactionBookView c Where transactionDate != null")
@Data
@NoArgsConstructor
public class TransactionBookView {

	@Id
	@Column(name = "CUSTOMERID")
	private int customerId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "UNIVERSITY")
	private String university;
	@Column(name = "BOOKID")
	private int bookId;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "TRANSACTIONDATE")
	private String transactionDate;
	@Column(name = "RETURNDATE")
	private String returnDate;

}
