package DTO;

public class thongkeDTO {
    private String time;
    private float totalDoanhThu;
    private float totalChiPhi;

    public thongkeDTO(String time, float totalDoanhThu, float totalChiPhi) {
        this.time = time;
        this.totalDoanhThu = totalDoanhThu;
        this.totalChiPhi = totalChiPhi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getTotalDoanhThu() {
        return totalDoanhThu;
    }

    public void setTotalDoanhThu(float totalDoanhThu) {
        this.totalDoanhThu = totalDoanhThu;
    }

    public float getTotalChiPhi() {
        return totalChiPhi;
    }

    public void setTotalChiPhi(float totalChiPhi) {
        this.totalChiPhi = totalChiPhi;
    }

}
