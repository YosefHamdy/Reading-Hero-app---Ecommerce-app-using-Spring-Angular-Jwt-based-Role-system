package com.example.cruddemo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.example.cruddemo.security.User;

import lombok.NoArgsConstructor;

@Entity
//@Table(name = "Authorities")

@NoArgsConstructor
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id ;
	@Column(name="Role_Name")
	@Enumerated(EnumType.STRING)
	private RoleEnum roleName;

	@ManyToMany(mappedBy = "roleList" , fetch = FetchType.LAZY)
	private Set<User> usersList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEnum getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleEnum roleName) {
		this.roleName = roleName;
	}
	
}
