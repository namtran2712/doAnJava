package DTO;

import java.sql.Date;


public class staff extends user {
	private float salary;
	private String dateStart;

	public staff(int id, String name, String phoneNumber, Date birthday, float salary, String dateStart) {
		super(id, name, phoneNumber, birthday);
		this.salary = salary;
		this.dateStart = dateStart;
	}

	public staff() {
		super();
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	@Override
	public String toString() {
		return salary + name;
	}
}
