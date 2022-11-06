package com.example.cruddemo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cruddemo.dao.CustomerAssignedBookInterface;
import com.example.cruddemo.dao.UserRepository;
import com.example.cruddemo.entity.CustomerAssignedBook;
import com.example.cruddemo.security.User;

@Service
public class CustomerAssignedBookServiceImpl implements CustomerAssignedBookService {

	private UserRepository userRepository;
	private CustomerAssignedBookInterface customerAssignedBookInterface;

	@Autowired
	public CustomerAssignedBookServiceImpl(CustomerAssignedBookInterface customerAssignedBookInterface,UserRepository userRepository) {
		this.customerAssignedBookInterface = customerAssignedBookInterface;
		this.userRepository=userRepository;
	}
	@Override
	public void save(CustomerAssignedBook theBook) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		String str = formatter.format(date);
		theBook.setTransactionDate(str);
		customerAssignedBookInterface.save(theBook);
	}
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}



	
	
}
