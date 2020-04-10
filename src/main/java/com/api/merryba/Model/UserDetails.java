package com.api.merryba.Model;

public class UserDetails {
	private String firstname;
	
	private String lastname;
	private String city;
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + "]";
	}

	public UserDetails(String firstname, String lastname, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
