package com.ces2.clase9.model;

import org.springframework.data.annotation.Id;

public class Autor {
	@Id 
	private Integer id;
	private String name;
	private String lastname;
	private String email;
	private String username;
	
	public Autor() {}
	
	public Autor(String name, String lastaname, String email, String username) {
		super();
		this.name = name;
		this.lastname = lastaname;
		this.email = email;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastaname) {
		this.lastname = lastaname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
