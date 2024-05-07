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
        String arr[] = name.toLowerCase().trim().split(" ");
        for (billDTO billDTO : listbill) {
            boolean check = true;
            for (String string : arr) {
                if (billDTO.getStaff().getName().toLowerCase().indexOf(string) == -1) {
                    check = false;
                    break;
                }
            }
            if (check) {
                list.add(billDTO);
            }
        }
        return list;
    }

    public ArrayList<billDTO> getBillByNameCustomer(String name) {
        ArrayList<billDTO> list = new ArrayList<billDTO>();
        String arr[] = name.toLowerCase().trim().split(" ");
        for (billDTO billDTO : listbill) {
            boolean check = true;
            for (String string : arr) {
                if (billDTO.getCustomer().getName().toLowerCase().indexOf(string) == -1) {
                    check = false;
                    break;
                }
            }
            if (check) {
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
