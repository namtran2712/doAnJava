package DTO;

import java.sql.Date;

public class bill {
	private int idBill;
	private staff Staff;
	private customer Customer;
	private float total;
	private Date dateBill;
	private particularBill detail;

	public bill(int idBill, staff staff, customer customer, float total, Date dateBill, particularBill detail) {
		this.idBill = idBill;
		Staff = staff;
		Customer = customer;
		this.total = total;
		this.dateBill = dateBill;
		this.detail = detail;
	}

	public bill() {
	}

	public int getIdBill() {
		return idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}

	public staff getStaff() {
		return Staff;
	}

	public void setStaff(staff staff) {
		Staff = staff;
	}

	public customer getCustomer() {
		return Customer;
	}

	public void setCustomer(customer customer) {
		Customer = customer;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getDateBill() {
		return dateBill;
	}

	public void setDateBill(Date dateBill) {
		this.dateBill = dateBill;
	}

	public particularBill getDetail() {
		return detail;
	}

	public void setDetail(particularBill detail) {
		this.detail = detail;
	}

}
