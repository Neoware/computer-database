package com.excilys.formation.persistence;

import java.sql.Timestamp;

public class Computer {
	
	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private int companyId;
	
	public Computer(){}
	
	public Computer(int id, String name, Timestamp introduced, Timestamp discontinued, int company_id){
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = company_id;
	}
	
	public Computer(String name, Timestamp introduced, Timestamp discontinued, int company_id){
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = company_id;
	}
	
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
	public Timestamp getIntroduced() {
		if (introduced == null)
			introduced = new Timestamp(0);
		return introduced;
	}
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int company_id) {
		this.companyId = company_id;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company_id=" + companyId + "]";
	}
	
	
	
}
