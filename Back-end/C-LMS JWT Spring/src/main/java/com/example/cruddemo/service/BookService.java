package com.example.cruddemo.service;

import java.util.List;

import com.example.cruddemo.entity.Book;
import com.example.cruddemo.entity.TransactionBookView;

public interface BookService {

	public List<Book> findAll();
	
	public List<TransactionBookView> findAllIssued();

	public Book findById(int theId);
	
	public void save(Book theBook);
	
	public void deleteById(int theId);

	public List<TransactionBookView> findByUserId(int id);
	
    public List<Book> search(String keyword);
    
}
