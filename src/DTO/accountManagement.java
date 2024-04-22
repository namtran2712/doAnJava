package DTO;

import java.util.ArrayList;

public class accountManagement implements modelInterface<accountDTO> {
    private ArrayList<accountDTO> listAccount;

    public accountManagement(ArrayList<accountDTO> listAccount) {
        this.listAccount = listAccount;
    }

    @Override
    public void insert(accountDTO t) {
        listAccount.add(t);
    }

    @Override
    public void delete(int row) {
        listAccount.remove(row);
    }

    @Override
    public void update(accountDTO t, int id) {

    }

    @Override
    public accountDTO findByID(int id) {
        for (accountDTO a : listAccount) {
            if (a.getIdAccount() == id) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<accountDTO> findByName(String src) {
        ArrayList<accountDTO> list = new ArrayList<accountDTO>();
        for (accountDTO acc : listAccount) {
            if (acc.getUsername().toLowerCase().indexOf(src) != -1) {
                list.add(acc);
            }
        }
        return list;
    }

    public ArrayList<accountDTO> getListAccount() {
        return listAccount;
    }

    public void setListAccount(ArrayList<accountDTO> listAccount) {
        this.listAccount = listAccount;
    }
}
