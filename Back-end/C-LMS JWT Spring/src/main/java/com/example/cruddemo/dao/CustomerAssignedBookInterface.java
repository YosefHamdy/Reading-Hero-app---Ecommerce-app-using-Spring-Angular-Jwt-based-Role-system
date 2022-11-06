package com.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.CustomerAssignedBook;

@Repository
public interface CustomerAssignedBookInterface
			extends JpaRepository<CustomerAssignedBook, Integer>{

}
