package com.example.cruddemo.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.example.cruddemo.entity.Role;

import lombok.Data;

@Table(name = "users")
@Entity
@Data
@NamedQuery(name = "checkUser", query = "select e from User e where e.username = :username AND e.password = :password ")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String username;
	private String password;
	private int enabled;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roleList;

	public void setEnabled(int enabled) {

		this.enabled = enabled;
	}

	public int isEnabled() {
 		return enabled;
	}

}
