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
}
