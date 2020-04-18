package com.merryba.api.dtos;

import java.util.List;

import com.merryba.api.Model.Order;

public class UserDtoV2 {

	private long id;

	private String firstname;

	private String lastname;

	private String email;

	private String role;

	private String ssn;

	private List<Order> orders;

	private String address;

	public UserDtoV2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDtoV2(long id, String firstname, String lastname, String email, String role, String ssn,
			List<Order> orders, String address, String username) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
		this.username = username;
	}

	private String username;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDtoV2 [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address + ", username="
				+ username + "]";
	}

}
