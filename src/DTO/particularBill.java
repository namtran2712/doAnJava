package DTO;

public class particularBill {
    private productDTO Product;
    private int size;
    private int quantity;
    private float price;

    public particularBill() {
    }

    public particularBill(productDTO Product, int size, int quantity, float price) {
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.Product = Product;
    }

    public productDTO getProduct() {
        return Product;
    }

    public void setProduct(productDTO product) {
        Product = product;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
