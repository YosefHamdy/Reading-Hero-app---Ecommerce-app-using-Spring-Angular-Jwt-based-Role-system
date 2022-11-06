package com.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.example.cruddemo.entity.Country;

@CrossOrigin("http://localhost:4200")
public interface CountryRepository extends JpaRepository<Country, Integer> {
}