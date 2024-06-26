package BUS;

import java.util.ArrayList;
import DAO.staffDao;
import DTO.customerDTO;
import DTO.staffDTO;

public class staffBUS {
	private ArrayList<staffDTO> listStaff;
	private staffDao dao = new staffDao();

	public staffBUS() {
		this.listStaff = dao.selectAll();
	}

	public ArrayList<staffDTO> getListStaff() {
		return listStaff;
	}

	public void setListStaff(ArrayList<staffDTO> listStaff) {
		this.listStaff = listStaff;
	}

	public staffDTO getInfo(int id) {
		for (staffDTO s : listStaff) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}

	public ArrayList<staffDTO> getInfoByName(String src) {
		String[] arr = src.toLowerCase().trim().split(" ");
		ArrayList<staffDTO> tmp = new ArrayList<staffDTO>();
		for (staffDTO s : listStaff) {
			boolean check = true;
			for (String str : arr) {
				if (s.getName().toLowerCase().indexOf(str) == -1) {
					check = false;
					break;
				}
			}
			if (check)
				tmp.add(s);
		}
		return tmp;
	}

	public boolean deleteStaff(int id) {
		for (staffDTO s : listStaff) {
			if (s.getId() == id) {
				listStaff.remove(s);
				dao.delete(s);
				return true;
			}
		}
		return false;
	}

	public boolean updateStaff(staffDTO staff) {
		boolean checkName = new validateBUS().checkName(staff.getName());
		boolean checkPhone = new validateBUS().checkPhone(staff.getPhoneNumber());
		boolean checkAge = new validateBUS().checkAge(staff.getBirthday().toString());

		if (checkName == false ||
				checkPhone == false ||
				checkAge == false) {
			return false;
		}

		for (staffDTO s : listStaff) {
			if (s.getId() == staff.getId()) {
				listStaff.add(listStaff.indexOf(s), staff);
				dao.update(staff);
				return true;
			}
		}
		return false;
	}

	public boolean checkMatchPhone(staffDTO staff) {
		for (staffDTO s : listStaff) {
			if (s.getId() != staff.getId()) {
				if (s.getPhoneNumber().equals(staff.getPhoneNumber())) {
					return true;
				}
			}
		}
		customerBUS cus = new customerBUS();
		for (customerDTO c : cus.getListCustomer()) {
			if (c.getPhoneNumber().equals(staff.getPhoneNumber())) {
				return true;
			}
		}
		return false;
	}
}
