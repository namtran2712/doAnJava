package DTO;

import java.util.ArrayList;

public class particularBill {
    private ArrayList<Integer> idProduct;
    private ArrayList<Integer> quantity;

    public particularBill() {
        idProduct = new ArrayList<Integer>();
        quantity = new ArrayList<Integer>();
    }

    public particularBill(int id, int quantity) {
        this.idProduct = new ArrayList<Integer>();
        this.quantity = new ArrayList<Integer>();
        this.idProduct.add(id);
        this.quantity.add(quantity);
    }

    public void addInfo(int id, int quantity) {
        this.idProduct.add(id);
        this.quantity.add(quantity);
    }

    public ArrayList<Integer> getIdProduct() {
        return idProduct;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setIdProduct(ArrayList<Integer> idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }
}
