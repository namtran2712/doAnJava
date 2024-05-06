package DTO;

import java.util.ArrayList;
import java.sql.Timestamp;

public class receiptDTO {
    private int idReceipt;
    private staffDTO staff;
    private float totalPrice;
    private Timestamp dateReceipt;
    private ArrayList<particularReceiptDTO> particular = new ArrayList<particularReceiptDTO>();

    public receiptDTO() {
    }

    public receiptDTO(int idReceipt, staffDTO staff, float totalPrice, Timestamp dateReceipt,
            ArrayList<particularReceiptDTO> particular) {
        this.idReceipt = idReceipt;
        this.staff = staff;
        this.totalPrice = totalPrice;
        this.dateReceipt = dateReceipt;
        particular = new ArrayList<particularReceiptDTO>();
        this.particular = particular;
    }

    public int getIdReceipt() {
        return idReceipt;
    }

    public staffDTO getStaff() {
        return staff;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getDateReceipt() {
        return dateReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public void setStaff(staffDTO staff) {
        this.staff = staff;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDateReceipt(Timestamp dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public ArrayList<particularReceiptDTO> getParticular() {
        return particular;
    }

    public void setParticular(ArrayList<particularReceiptDTO> particular) {
        this.particular = particular;
    }

    public boolean add(int idProduct, int size, int quantity, float totalPrice) {
        for (particularReceiptDTO particularReceipt : particular) {
            if (particularReceipt.getProduct().getIdProduct() == idProduct &&
                    particularReceipt.getSize() == size) {
                particularReceipt.setQuantity(particularReceipt.getQuantity() + quantity);
                this.totalPrice = totalPrice;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int id) {
        for (particularReceiptDTO particularReceipt : particular) {
            if (particularReceipt.getProduct().getIdProduct() == id) {
                float priceMinus = particularReceipt.getPrice() * particularReceipt.getQuantity();
                totalPrice -= priceMinus;
                particular.remove(particularReceipt);
                return true;
            }
        }
        return false;
    }
}
