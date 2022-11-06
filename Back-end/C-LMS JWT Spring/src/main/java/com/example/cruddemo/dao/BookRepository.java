package com.example.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Book;

@Repository
public interface BookRepository 
					extends JpaRepository<Book, Integer> {
	
//		Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
	@Query("SELECT p FROM Book p WHERE CONCAT(p.title, ' ', p.subTitle, ' ', p.subject) LIKE %?1%"
			+ "OR CONCAT(lower(p.title), ' ', lower(p.subTitle), ' ', lower(p.subject)) LIKE %?1% "
			+ "OR CONCAT(upper(p.title), ' ', upper(p.subTitle), ' ', upper(p.subject)) LIKE %?1%")
	public List<Book> search(String keyword);

}

