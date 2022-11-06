package com.example.cruddemo.dao;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.cruddemo.entity.Book;
import com.example.cruddemo.entity.TransactionBookView;

@CrossOrigin
public interface FindAll  {

	public List<Book> findAll(Integer isbn);
	public List<TransactionBookView> findAllIssued(Integer customerId);
}
