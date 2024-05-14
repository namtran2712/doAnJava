package BUS;

import java.util.ArrayList;

import DAO.thongKeDao;
import DTO.thongkeDTO;

public class thongKeBus {
    private ArrayList<thongkeDTO> list;
    private thongKeDao dao = new thongKeDao();

    public thongKeBus() {
        list = dao.selectAll();
    }

    public ArrayList<thongkeDTO> getList() {
        return list;
    }

    public void setList(ArrayList<thongkeDTO> list) {
        this.list = list;
    }

}
