package DTO;

public class particularReceiptDTO {
    private int idReceipt;
    private productDTO product;
    private int size;
    private int quantity;
    private float price;

    public particularReceiptDTO(int idReceipt, productDTO product, int size, int quantity, float price) {
        this.idReceipt = idReceipt;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdReceipt() {
        return idReceipt;
    }

    public productDTO getProduct() {
        return product;
    }

    public int getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public void setProduct(productDTO product) {
        this.product = product;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
