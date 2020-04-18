package com.merryba.api.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This Model is to create a User")
@Entity
@Table(name="user")
//@JsonIgnoreProperties({"firstname","lastname"}) -Static Filtering @JsonIgnore
//@JsonFilter(value="userFilter") - User for MappingJacksonvalue filtering section
public class User extends RepresentationModel<User>{
		@ApiModelProperty(notes="Auto generated unique id",required=true,position =1)
		@Id
		@GeneratedValue
		@JsonView(Views.External.class)
		private long id;
		
		@ApiModelProperty(notes="Username should be flname",example= "merryba",required=false,position =2)
		@Size(min =2,max=50)
		@Column(name="USER_NAME",length=50,nullable=false,unique=true)
		@JsonView(Views.External.class)
		private String username;
		
		@Size(min =2,max=50,message = "FirstName should have atleast 2 characters")
		@Column(name="FIRST_NAME",length=50,nullable=false)
		@JsonView(Views.External.class)
		private String firstname;
		
		@Column(name="LAST_NAME",length=50,nullable=false)
		@JsonView(Views.External.class)
		private String lastname;
		
		
		@Column(name="EMAIL",length=50,nullable=false)
		@JsonView(Views.External.class)
		private String email;
		
		@Column(name="ROLE",length=50,nullable=false)
		@JsonView(Views.Internal.class)
		private String role;
		
		// @JsonIgnore - -Static Filtering @JsonIgnore
		@JsonView(Views.Internal.class)
		@Column(name="SSN",length=50,nullable=true,unique=true)
		private String ssn;
		
		@Column(name="ADDRESS")
		private String address;
		
		@OneToMany(mappedBy="user")
		@JsonView(Views.Internal.class)
		private List<Order> orders;

		//No ARgs Constructor
		public User() {
			
			// TODO Auto-generated constructor stub
		}

		

		public User(long id, String username, String firstname, String lastname, String email, String role, String ssn,
				String address, List<Order> orders) {
			super();
			this.id = id;
			this.username = username;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.role = role;
			this.ssn = ssn;
			this.address = address;
			this.orders = orders;
		}



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
			return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
					+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", address=" + address + ", orders="
					+ orders + "]";
		}
		
		

		

				
		
		
		

}
