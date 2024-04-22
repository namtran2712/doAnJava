package DTO;

import java.util.ArrayList;

public class customerManagement implements modelInterface<customer> {
    private ArrayList<customer> listCustomer;

    public customerManagement(ArrayList<customer> listCustomer) {
        this.listCustomer = listCustomer;
    }

    @Override
    public void insert(customer t) {
        listCustomer.add(t);
    }

    @Override
    public void delete(int row) {
        listCustomer.remove(row);
    }

    @Override
    public void update(customer t, int id) {

    }

    @Override
    public customer findByID(int id) {
        for (customer c : listCustomer) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<customer> listCustomer) {
        this.listCustomer = listCustomer;
    }

    public ArrayList<customer> findByName(String src) {
        ArrayList<customer> tmp = new ArrayList<customer>();

        for (customer s : listCustomer) {
            if (s.getName().toLowerCase().indexOf(src) != -1) {
                tmp.add(s);
            }
        }
        return tmp;
    }
}
