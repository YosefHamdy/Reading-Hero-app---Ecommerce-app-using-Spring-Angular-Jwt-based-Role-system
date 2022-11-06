package com.example.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cruddemo.dao.BookRepository;
import com.example.cruddemo.dao.FindAll;
import com.example.cruddemo.dao.ViewCustomized;
import com.example.cruddemo.entity.Book;
import com.example.cruddemo.entity.TransactionBookView;

@Service
public class BookServiceImpl implements BookService {

	
	private BookRepository bookRepository;
	private FindAll findAll ;
	private ViewCustomized viewCustBook ;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, FindAll findAll,ViewCustomized viewCustBook) {
		this.bookRepository = bookRepository;
		this.findAll = findAll;
		this.viewCustBook = viewCustBook;
	}

	public BookServiceImpl () {
	}
	
	@Override
	public List<Book> findAll() {

		return findAll.findAll(null);
	}
	@Override
	public Book findById(int theId) {
		Optional<Book> result = bookRepository.findById(theId);
		
		Book theBook = null;
				
		if(result.isPresent()) {
			theBook = result.get();
		}else {
			// we didn't find the employee
			throw new RuntimeException("Did not found the Book id - " +theId);
		}
		return theBook;
	}
	@Override
	public void save(Book theBook) {
		bookRepository.save(theBook);
	}
	@Override
	public void deleteById(int theId) {
		bookRepository.deleteById(theId);
	}

	@Override
	public List<TransactionBookView> findAllIssued() {
		return findAll.findAllIssued(null);
	}
	@Override
	public List<TransactionBookView> findByUserId(int id){
		return viewCustBook.findByCustomerId(id);
	}

	@Override
	public List<Book> search(String keyword) {
		if (keyword!=null) {
				return bookRepository.search(keyword);
			}
		return bookRepository.findAll();
		
	}

	
	
}
