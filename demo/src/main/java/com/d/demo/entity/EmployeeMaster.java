package com.d.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table

public class EmployeeMaster {

	public EmployeeMaster() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column
	private String name;
	
	@OneToOne(targetEntity=Designation.class,cascade=CascadeType.ALL)  
	private Designation designation;  
	
	@OneToOne(targetEntity=Department.class,cascade=CascadeType.ALL)  
	private Department department;  
	
	@Temporal(TemporalType.DATE)
	private java.util.Date joiningDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public java.util.Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(java.util.Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	

}
