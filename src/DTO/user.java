package DTO;

import java.sql.Date;

public class user {
	protected int id;
	protected String name;
	protected String phoneNumber;
	protected Date birthday;

	public user(int id, String name, String phoneNumber, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
	}

	public user() {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
