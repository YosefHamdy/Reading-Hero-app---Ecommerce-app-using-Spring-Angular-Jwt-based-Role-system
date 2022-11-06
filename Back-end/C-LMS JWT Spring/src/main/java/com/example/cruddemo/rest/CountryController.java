package com.example.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.entity.Country;
import com.example.cruddemo.entity.State;
import com.example.cruddemo.exceptions.NoDataFoundException;
import com.example.cruddemo.service.CountryServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CountryController {

	
	@Autowired 
	private CountryServiceImpl countryService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/countries")
	public List<Country> findAll() throws NoDataFoundException {
		try {
			return countryService.findAll();
		} catch (Exception e) {
			throw new NoDataFoundException("No Data Found Exception");
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/states")
	public List<State> findAllStates() throws NoDataFoundException {
		try {
			return countryService.findAllState();
		} catch (Exception e) {
			throw new NoDataFoundException("No Data Found Exception");
		}
	}
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/states/{id}")
	public List<State> findAllStates(@PathVariable int id) throws NoDataFoundException {
		try {
			return countryService.findByCountryId(id);
		} catch (Exception e) {
			throw new NoDataFoundException("No Data Found Exception");
		}
	}
}
