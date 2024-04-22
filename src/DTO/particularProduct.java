package DTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class particularProduct {
    int quantityRemain;
    int size;
    float price;

    public particularProduct(int size, float price,int quantityRemain) {
        this.quantityRemain = quantityRemain;
        this.size = size;
        this.price = price;
    }

    public int getQuantityRemain() {
        return quantityRemain;
    }

    public void setQuantityRemain(int quantityRemain) {
        this.quantityRemain = quantityRemain;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "particularProduct [quantityRemain=" + quantityRemain + ", size=" + size + ", price=" + price + "]";
    }
} 