package BUS;

import java.util.ArrayList;

import DTO.billDTO;
import DAO.billDao;

public class billBUS {
    ArrayList<billDTO> listbill;
    billDao bdao = new billDao();

    public billBUS() {
        listbill = bdao.selectAll();
    }

    public ArrayList<billDTO> getListBill() {
        return listbill;
    }

    public billDTO getBill(int id) {
        for (billDTO billDTO : listbill) {
            if (billDTO.getIdBill() == id) {
                return billDTO;
            }
        }
        return null;
    }

    public boolean deleteBill(int id) {
        for (billDTO billDTO : listbill) {
            if (billDTO.getIdBill() == id) {
                listbill.remove(billDTO);
                bdao.delete(billDTO);
                return true;
            }
        }
        return false;
    }

    public ArrayList<billDTO> getBillByNameStaff(String name) {
        ArrayList<billDTO> list = new ArrayList<billDTO>();
        for (billDTO billDTO : listbill) {
            if (billDTO.getStaff().getName().toLowerCase().indexOf(name.toLowerCase()) != -1) {
                list.add(billDTO);
            }
        }
        return list;
    }

    public ArrayList<billDTO> getBillByNameCustomer(String name) {
        ArrayList<billDTO> list = new ArrayList<billDTO>();
        for (billDTO billDTO : listbill) {
            if (billDTO.getCustomer().getName().toLowerCase().indexOf(name.toLowerCase()) != -1) {
                list.add(billDTO);
            }
        }
        return list;
    }

    public ArrayList<billDTO> getBillByID(int id) {
        ArrayList<billDTO> list = new ArrayList<billDTO>();
        for (billDTO billDTO : listbill) {
            if (billDTO.getIdBill() == id) {
                list.add(billDTO);
            }
        }
        return list;
    }
}
