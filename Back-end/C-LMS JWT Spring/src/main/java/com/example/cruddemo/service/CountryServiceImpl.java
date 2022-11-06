package com.example.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cruddemo.dao.CountryRepository;
import com.example.cruddemo.dao.StateRepository;
import com.example.cruddemo.entity.Country;
import com.example.cruddemo.entity.State;

@Service
public class CountryServiceImpl {

	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;

	public List<Country> findAll() {

		return countryRepo.findAll();
	}

	public List<State> findAllState() {

		return stateRepo.findAll();
	}

	public List<State> findByCountryId(int id) {
	
		return stateRepo.findByCountryId(id);
	}
}
