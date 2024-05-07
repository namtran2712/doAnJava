package BUS;

import java.util.ArrayList;

import DAO.customerDao;
import DTO.customerDTO;

public class customerBUS {
    private ArrayList<customerDTO> listCustomer;
    private customerDao dao = new customerDao();

    public customerBUS() {
        this.listCustomer = dao.selectAll();
    }

    public ArrayList<customerDTO> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<customerDTO> listCustomer) {
        this.listCustomer = listCustomer;
    }

    public customerDTO getInfo(int id) {
        for (customerDTO cus : listCustomer) {
            if (cus.getId() == id) {
                return cus;
            }
        }
        return null;
    }

    public ArrayList<customerDTO> getInfoByName(String src) {
        String arr[] = src.toLowerCase().trim().split(" ");
        ArrayList<customerDTO> list = new ArrayList<customerDTO>();
        for (customerDTO cus : listCustomer) {
            boolean check = true;
            for (String str : arr) {
                if (cus.getName().toLowerCase().indexOf(str) == -1) {
                    check = false;
                }
            }
            if (check) {
                list.add(cus);
            }
        }
        return list;
    }

    public boolean deleteCustomer(int id) {
        for (customerDTO cus : listCustomer) {
            if (cus.getId() == id) {
                listCustomer.remove(cus);
                dao.delete(cus);
                return true;
            }
        }
        return false;
    }

    public boolean updateCustomer(customerDTO cus) {
        boolean checkName = new validateBUS().checkName(cus.getName());
        boolean checkPhone = new validateBUS().checkPhone(cus.getPhoneNumber());
        boolean checkAge = new validateBUS().checkAge(cus.getBirthday().toString());

        if (checkName == false ||
                checkPhone == false ||
                checkAge == false) {
            return false;
        }
        for (customerDTO customer : listCustomer) {
            if (cus.getId() == customer.getId()) {
                listCustomer.add(listCustomer.indexOf(customer), cus);
                dao.update(cus);
                return true;
            }
        }
        return false;
    }

    public boolean checkMatchPhone(customerDTO customer) {
        for (customerDTO cus : listCustomer) {
            if (cus.getId() != customer.getId()) {
                if (cus.getPhoneNumber().equals(customer.getPhoneNumber())) {
                    return true;
                }
            }
        }
        return false;
    }
}
