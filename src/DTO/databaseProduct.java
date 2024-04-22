package DTO;

import java.util.ArrayList;

import DAO.productDao;

public class databaseProduct implements modelInterface<productDTO> {

    private ArrayList<productDTO> listProduct;

    private productDao dao;

    public databaseProduct() {
        listProduct = new ArrayList<productDTO>();
        dao = new productDao();
    }

    @Override
    public void delete(int row) {
        listProduct.remove(row);
        for (int i = row + 1; i < listProduct.size(); i++) {
            listProduct.add(i - 1, listProduct.get(i));
        }

    }

    public productDao getDao() {
        return dao;
    }

    @Override
    public productDTO findByID(int id) {
        for (productDTO product : listProduct) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void insert(productDTO t) {
        listProduct.add(t);

    }

    @Override
    public void update(productDTO t, int id) {
        listProduct.add(id, t);
    }

}
