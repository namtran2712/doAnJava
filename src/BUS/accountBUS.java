package BUS;

import java.util.ArrayList;

import DAO.accountDao;
import DAO.staffDao;
import DTO.accountDTO;
import DTO.staffDTO;

public class accountBUS {
    private ArrayList<accountDTO> listAccount;
    private accountDao dao = new accountDao();

    public accountBUS() {
        this.listAccount = dao.selectAll();
    }

    public void setListAccount(ArrayList<accountDTO> listAccount) {
        this.listAccount = listAccount;
    }

    public ArrayList<accountDTO> getListAccount() {
        return listAccount;
    }

    public accountDTO getInfo(int id) {
        for (accountDTO acc : listAccount) {
            if (acc.getIdAccount() == id) {
                return acc;
            }
        }
        return null;
    }

    public ArrayList<accountDTO> getInfoByName(String src) {
        ArrayList<accountDTO> tmp = new ArrayList<accountDTO>();
        for (accountDTO acc : listAccount) {
            if (acc.getUsername().toLowerCase().indexOf(src) != -1) {
                tmp.add(acc);
            }
        }
        return tmp;
    }

    public boolean deleteAccount(int id) {
        for (accountDTO acc : listAccount) {
            if (acc.getIdAccount() == id) {
                listAccount.remove(acc);
                dao.delete(acc);
                return true;
            }
        }
        return false;
    }

    public boolean updateAccount(accountDTO acc) {
        for (accountDTO a : listAccount) {
            if (a.getIdAccount() == acc.getIdAccount()) {
                listAccount.add(listAccount.indexOf(a), acc);
                dao.update(acc);
                return true;
            }
        }
        return false;
    }

    public boolean checkLogin(accountDTO acc) {
        for (accountDTO account : listAccount) {
            if (account.getUsername().equals(acc.getUsername())) {
                if (account.getPassword().equals(acc.getPassword())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean checkRegister(accountDTO acc) {
        for (accountDTO account : listAccount) {
            if (account.getUsername().equals(acc.getUsername())) {
                return false;
            }
        }
        if (acc.getPassword().trim().length() >= 8) {
            boolean check = new staffDao().insert(acc.getNhanVien());
            if (check == false) {
                System.out.println("thêm thất bại");
                return false;
            }
            staffDTO staff = new staffDao().getLastInsert();
            System.out.println(staff.getId());
            System.out.println(staff.getName());
            acc.setNhanVien(staff);
            System.out.println(acc.getNhanVien().getName());
            System.out.println(acc.getNhanVien().getPhoneNumber());
            dao.insert(acc);
            listAccount = dao.selectAll();
            return true;
        }
        return false;
    }
}
