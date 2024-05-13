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

    public productDTO getProductByIndex(int idex) {
        return listProduct.get(idex);
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

    public float getPrice(productDTO product, int size) {
        ArrayList<particularProduct> pd = product.getParticularProducts();
        float price = 0;
        boolean flag = true;
        for (particularProduct p : pd) {
            if (p.getSize() == size) {
                price = p.getPrice();
                flag = false;
            }

        }
        if (flag)
            return -1;
        else {
            return price;
        }
    }

    public String getDefaultPrice(productDTO product) {
        float price = product.getParticularProducts().get(0).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedPrice = decimalFormat.format(price);
        return formattedPrice;
    }

    public int quantitySize(productDTO product, int size) {
        ArrayList<particularProduct> pd = product.getParticularProducts();
        int sizeReturn = -1;

        for (particularProduct p : pd) {
            if (p.getSize() == size) {
                sizeReturn = p.getQuantityRemain();

            }

        }

        return sizeReturn;

    }

    public ArrayList<productDTO> selectByName(String name) {
        String[] arr = name.toLowerCase().trim().split(" ");
        ArrayList<productDTO> l = new ArrayList<productDTO>();
        for (productDTO productDTO : listProduct) {
            boolean check = true;
            for (String tmp : arr) {
                if (productDTO.getName().toLowerCase().indexOf(tmp) == -1) {
                    check = false;
                    break;
                }
            }
            if (check) {
                l.add(productDTO);
            }
        }
        return l;
    }

    public ArrayList<productDTO> selectByHetHang() {
        ArrayList<productDTO> l = new ArrayList<productDTO>();
        for (productDTO productDTO : listProduct) {
            int sum = 0;
            for (particularProduct pp : productDTO.getParticularProducts()) {
                sum += pp.getQuantityRemain();
            }
            if (sum < 10) {
                l.add(productDTO);
            }
        }
        return l;
    }

    public ArrayList<productDTO> selectById(int id) {
        ArrayList<productDTO> l = new ArrayList<productDTO>();
        for (productDTO productDTO : listProduct) {
            if (productDTO.getIdProduct() == id) {
                l.add(productDTO);
            }
        }
        return l;
    }
}