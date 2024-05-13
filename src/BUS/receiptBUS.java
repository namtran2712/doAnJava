package BUS;

import java.util.ArrayList;

import DAO.receiptDao;
import DTO.receiptDTO;

public class receiptBUS {
    private ArrayList<receiptDTO> list;
    private receiptDao dao = new receiptDao();

    public receiptBUS() {
        list = dao.selectAll();
    }

    public ArrayList<receiptDTO> getList() {
        return list;
    }

    public void setList(ArrayList<receiptDTO> list) {
        this.list = list;
    }

    public receiptDTO findById(int id) {
        for (receiptDTO receipt : list) {
            if (receipt.getIdReceipt() == id) {
                return receipt;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        for (receiptDTO receipt : list) {
            if (receipt.getIdReceipt() == id) {
                list.remove(receipt);
                dao.delete(receipt);
                return true;
            }
        }
        return false;
    }

    public ArrayList<receiptDTO> selectByNameStaff(String name) {
        String[] arr = name.toLowerCase().trim().split(" ");
        ArrayList<receiptDTO> l = new ArrayList<receiptDTO>();
        for (receiptDTO receiptDTO : list) {
            boolean check = true;
            for (String str : arr) {
                if (receiptDTO.getStaff().getName().toLowerCase().indexOf(str) == -1) {
                    check = false;
                    break;
                }
            }
            if (check)
                l.add(receiptDTO);
        }

        return l;
    }

    public ArrayList<receiptDTO> selectById(int id) {
        ArrayList<receiptDTO> l = new ArrayList<receiptDTO>();
        for (receiptDTO receiptDTO : list) {
            if (receiptDTO.getIdReceipt() == id) {
                l.add(receiptDTO);
            }
        }

        return l;
    }

}
