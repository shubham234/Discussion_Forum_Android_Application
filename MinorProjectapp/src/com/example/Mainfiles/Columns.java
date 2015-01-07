package com.example.Mainfiles;

public class Columns {
	long enrollment;
	String name;
	String email;
	long contact;
	String branch;
	String year;
	public Columns(long enrollment,String name,String email,long contact,String branch,String year)
	{
		super();
		this.enrollment=enrollment;
		this.name=name;
		this.email=email;
		this.contact=contact;
		this.branch=branch;
		this.year=year;
	}
	public long getEnrollment() {
		return enrollment;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public long getContact() {
		return contact;
	}
	public String getBranch() {
		return branch;
	}
	public String getYear() {
		return year;
	}
	

}
