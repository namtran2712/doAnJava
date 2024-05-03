package DTO;

import java.sql.Date;

@SuppressWarnings("unused")
public class accountDTO {
    private int idAccount;
    private authorizeDTO vaiTro;
    private staffDTO nhanVien;
    private String username;
    private String password;
    private int status;

    public accountDTO(int idAccount, authorizeDTO vaiTro, staffDTO nhanVien, String username, String password,
            int status) {
        this.idAccount = idAccount;
        this.vaiTro = vaiTro;
        this.nhanVien = nhanVien;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public authorizeDTO getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(authorizeDTO vaiTro) {
        this.vaiTro = vaiTro;
    }

    public staffDTO getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(staffDTO nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
