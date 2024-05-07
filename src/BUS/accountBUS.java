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
        String arr[] = src.toLowerCase().trim().split(" ");
        for (accountDTO acc : listAccount) {
            boolean check = true;
            for (String string : arr) {
                if (acc.getUsername().toLowerCase().indexOf(string) == -1) {
                    check = false;
                    break;
                }
            }
            if (check) {
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

    public int checkLogin(accountDTO acc) {
        for (accountDTO account : listAccount) {
            if (account.getUsername().equals(acc.getUsername())) {
                if (account.getPassword().equals(acc.getPassword())) {
                    if (account.getStatus() == 1) {
                        return 1;
                    } else if (account.getStatus() == 0) {
                        return 0;
                    }
                }
                return 2;
            }
        }
        return 2;
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
