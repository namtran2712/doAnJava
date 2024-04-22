package DTO;

import java.util.ArrayList;

public class staffManagement implements modelInterface<staff> {
	private ArrayList<staff> listStaff;

	public staffManagement(ArrayList<staff> listStaff) {
		this.listStaff = listStaff;
	}

	public void add(staff s) {

	}

	public ArrayList<staff> getListStaff() {
		return listStaff;
	}

	public void setListStaff(ArrayList<staff> listStaff) {
		this.listStaff = listStaff;
	}

	@Override
	public void insert(staff t) {
		for (staff st : listStaff) {
			if (t.getPhoneNumber().equals(st)) {
				return;
			}
		}
		listStaff.add(t);
	}

	@Override
	public void delete(int row) {
		listStaff.remove(row);
	}

	@Override
	public void update(staff t, int id) {
		for (staff s : listStaff) {
			if (s.getId() == id) {
				s = t;
			}
		}
	}

	public ArrayList<staff> findByName(String name) {
		ArrayList<staff> tmp = new ArrayList<staff>();

		for (staff s : listStaff) {
			if (s.getName().toLowerCase().indexOf(name) != -1) {
				tmp.add(s);
			}
		}
		return tmp;
	}

	@Override
	public staff findByID(int id) {
		for (staff s : listStaff) {
			if (s.getId() == (id)) {
				return s;
			}
		}
		return null;
	}
}
