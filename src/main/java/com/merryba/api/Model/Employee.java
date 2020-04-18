package com.merryba.api.Model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "employee")
public class Employee {
	
	

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long empid, String name, String department, Timestamp loginTime, Timestamp logoutTime,
			double salary, Date lastPromotionDate) {
		super();
		this.empid = empid;
		this.name = name;
		this.department = department;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		Salary = salary;
		this.lastPromotionDate = lastPromotionDate;
	}

	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long empid;
	
	@JsonView(Views.External.class)
	private String name;
	
	@JsonView(Views.External.class)
	private String department;
	
	@JsonView(Views.External.class)
	private Timestamp loginTime;
	
	@JsonView(Views.External.class)
	private Timestamp logoutTime;
	
	@JsonView(Views.Internal.class)
	private double Salary;
	
	@JsonView(Views.Internal.class)
	private Date lastPromotionDate;

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}

	public Date getLastPromotionDate() {
		return lastPromotionDate;
	}

	public void setLastPromotionDate(Date lastPromotionDate) {
		this.lastPromotionDate = lastPromotionDate;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", department=" + department + ", loginTime=" + loginTime
				+ ", logoutTime=" + logoutTime + ", Salary=" + Salary + ", lastPromotionDate=" + lastPromotionDate
				+ "]";
	}

}
