package BUS;

import java.text.DecimalFormat;
import java.util.ArrayList;

import DAO.productDao;
import DTO.particularProduct;
import DTO.productDTO;

public class productBUS {
    ArrayList<productDTO> listProduct;
    productDao pdao;
    materialBUS material;
    categoryBUS category;

    public productBUS() {
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

    public productDTO getProduct(int index) {
        return listProduct.get(index);
    }

    public productDTO getProductByID(int id) {

        Boolean flag = false;

        for (productDTO pd : listProduct) {
            if (pd.getIdProduct() == id) {

                flag = true;
            }
            if (flag)
                return pd;
        }

        return null;
    }

    public String getPrice(int id, int size) {
        ArrayList<particularProduct> pd = listProduct.get(id).getParticularProducts();
        float price = 0;
        boolean flag = true;
        for (particularProduct p : pd) {
            if (p.getSize() == size) {
                price = p.getPrice();
                flag = false;
            }

        }
        if (flag)
            return "Không có size";
        else {
            // Định dạng giá tiền
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            String formattedPrice = decimalFormat.format(price);
            return formattedPrice;
        }
    }

    public String getDefaultPrice(productDTO product) {
        float price = product.getParticularProducts().get(0).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedPrice = decimalFormat.format(price);
        return formattedPrice;
    }

    public int quantitySize(int id, int size) {
        ArrayList<particularProduct> pd = listProduct.get(id).getParticularProducts();
        int sizeReturn = -1;

        for (particularProduct p : pd) {
            if (p.getSize() == size) {
                sizeReturn = p.getQuantityRemain();

            }

        }

        return sizeReturn;

    }
}