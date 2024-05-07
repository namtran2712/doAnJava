package DTO;

import java.sql.Date;
import java.util.ArrayList;

public class billDTO {
	private int idBill;
	private staffDTO Staff;
	private customerDTO Customer;
	private float total;
	private Date dateBill;
	private ArrayList<particularBill> detail;

	public billDTO(int idBill, staffDTO staff, customerDTO customer, float total, Date dateBill,
			particularBill particular) {
		this.idBill = idBill;
		Staff = staff;
		Customer = customer;
		this.total = total;
		this.dateBill = dateBill;
		this.detail = new ArrayList<particularBill>();
		detail.add(particular);
	}

	public billDTO() {
		detail = new ArrayList<particularBill>();
	}

	public int getIdBill() {
		return idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}

	public staffDTO getStaff() {
		return Staff;
	}

	public void setStaff(staffDTO staff) {
		Staff = staff;
	}

	public customerDTO getCustomer() {
		return Customer;
	}

	public void setCustomer(customerDTO customer) {
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

	public ArrayList<particularBill> getDetail() {
		return detail;
	}

	public void setDetail(ArrayList<particularBill> detail) {
		this.detail = detail;
	}

}
