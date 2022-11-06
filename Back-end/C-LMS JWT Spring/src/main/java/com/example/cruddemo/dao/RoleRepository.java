package com.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Role;
import com.example.cruddemo.entity.RoleEnum;

@Repository
public interface RoleRepository
				extends JpaRepository<Role, Integer>{


//	Role findByRoleName(String roleUser);

	Role findByRoleName(RoleEnum roleUser);

}
