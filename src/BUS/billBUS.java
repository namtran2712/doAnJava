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
}
