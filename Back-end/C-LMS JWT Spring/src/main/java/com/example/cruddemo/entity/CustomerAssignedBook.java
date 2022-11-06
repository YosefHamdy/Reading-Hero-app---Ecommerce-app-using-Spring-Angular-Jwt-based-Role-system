package com.example.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@NamedQuery(name = "listIssue", query = "Select e from CustomerAssignedBook e Where  :id = -1 OR e.id = :id  ")
@Entity
@Table(name = "TRANSACTIONBOOk")
@Data
@NoArgsConstructor
public class CustomerAssignedBook {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(name = "customerid")
	private int customerId;
	private String customerName; 
	@Column(name = "bookid")
	private int bookId; 
	@Column(name = "TITLE")
	private String bookTitle;
	@Column(name = "transactionDate")
	private String transactionDate;
	@Column(name = "RETURNDATE")
	private String returnDate;
}
