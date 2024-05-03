package DTO;

import java.sql.Date;
import java.util.ArrayList;

public class receiptDTO {
    private int idReceipt;
    private staffDTO staff;
    private float totalPrice;
    private Date dateReceipt;
    private ArrayList<particularReceiptDTO> particular = new ArrayList<particularReceiptDTO>();

    public receiptDTO(int idReceipt, staffDTO staff, float totalPrice, Date dateReceipt,
            ArrayList<particularReceiptDTO> particular) {
        this.idReceipt = idReceipt;
        this.staff = staff;
        this.totalPrice = totalPrice;
        this.dateReceipt = dateReceipt;
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

    public Date getDateReceipt() {
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

    public void setDateReceipt(Date dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public ArrayList<particularReceiptDTO> getParticular() {
        return particular;
    }

    public void setParticular(ArrayList<particularReceiptDTO> particular) {
        this.particular = particular;
    }
}
