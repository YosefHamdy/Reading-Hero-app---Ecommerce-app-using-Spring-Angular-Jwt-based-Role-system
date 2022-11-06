package com.example.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cruddemo.entity.TransactionBookView;

public interface ViewCustomized extends JpaRepository<TransactionBookView,Integer>{

	
	List<TransactionBookView>findByCustomerId(int id);

}

