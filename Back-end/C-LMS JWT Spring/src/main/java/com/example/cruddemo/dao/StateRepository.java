package com.example.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.cruddemo.entity.State;

@CrossOrigin("http://localhost:4200")
public interface StateRepository extends JpaRepository<State, Integer> {

    List<State> findByCountryId(int id);

}