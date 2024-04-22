package test;

import java.util.ArrayList;

import DAO.productDao;
import DTO.productDTO;

public class testDao {
    public static void main(String[] args) {
        productDao pDao = new productDao();
        ArrayList<productDTO> products = pDao.selectAll();
        for (productDTO i : products) {
            System.out.println(i);
        }
    }
}
