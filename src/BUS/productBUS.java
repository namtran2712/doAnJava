package BUS;

import java.text.DecimalFormat;
import java.util.ArrayList;

import DAO.productDao;
import DTO.productDTO;

public class productBUS {
    ArrayList<productDTO> listProduct;
    productDao pdao;
    materialBUS material;
    categoryBUS category;

    public productBUS() throws Exception {
        listProduct = new ArrayList<>();
        material = new materialBUS();
        category = new categoryBUS();
        pdao = new productDao();
        loadProduct();
    }

    public void loadProduct() {
        listProduct = pdao.selectAll();
    }

    public ArrayList<productDTO> getListProduct() {
        return listProduct;
    }

    public String getCategoryProduct(int id) {
        return category.getCategory(id);
    }

    public String getMaterialProduct(int id) {
        return material.getMaterial(id);
    }

    public int getQuantityProducts() {

        return listProduct.size();
    }
    public productDTO getProduct (int id)
    {
        return listProduct.get(id);
    }

    public String getDefaultPrice (int id)
    {
        float price = listProduct.get(id).getParticularProducts().get(0).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        // Định dạng giá tiền
        String formattedPrice = decimalFormat.format(price);
        return formattedPrice;
    }


    

}