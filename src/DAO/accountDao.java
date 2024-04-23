package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.accountDTO;
import DTO.authorizeDTO;
import DTO.staffDTO;
import database.databaseUtil;

public class accountDao implements daoInterface<accountDTO> {

    @Override
    public boolean insert(accountDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "INSERT INTO ACCOUNTS (ID_AUTHORIZE, ID_STAFF, USERNAME, PASS_WORD) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, t.getVaiTro().getIdAuthorize());
            pst.setInt(2, t.getNhanVien().getId());
            pst.setString(3, t.getUsername());
            pst.setString(4, t.getPassword());

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return false;
    }

    @Override
    public boolean delete(accountDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "DELETE FROM ACCOUNTS WHERE ID_ACCOUNT = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, t.getIdAccount());
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return false;
    }

    @Override
    public boolean update(accountDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "UPDATE ACCOUNTS SET " +
            // "ID_ACCOUNT = ?," +
            // "ID_AUTHORIZE = ?," +
            // "ID_STAFF = ?," +
            // "USERNAME = ?," +
                    "PASS_WORD = ? " +
                    "WHERE ID_ACCOUNT = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            // pst.setInt(1, t.getVaiTro().getIdAuthorize());
            // pst.setInt(2, t.getNhanVien().getId());
            pst.setString(1, t.getPassword());
            pst.setInt(2, t.getIdAccount());
            // pst.setString(4, t.getUsername());
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return false;
    }

    @Override
    public ArrayList<accountDTO> selectAll() {
        Connection conn = databaseUtil.getConnection();

        ArrayList<accountDTO> list = new ArrayList<accountDTO>();
        try {
            String sql = "SELECT * " +
                    "FROM ACCOUNTS JOIN AUTHORIZES " +
                    "ON ACCOUNTS.ID_AUTHORIZE = AUTHORIZES.ID_AUTHORIZE " +
                    "JOIN STAFF " +
                    "ON ACCOUNTS.ID_STAFF = STAFF.ID_STAFF";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                staffDTO s = new staffDTO(
                        result.getInt("ID_STAFF"),
                        result.getString("FULLNAME"),
                        result.getString("PHONE_NUMBER"),
                        result.getDate("BIRTHDAY"),
                        result.getFloat("SALARY"),
                        result.getString("DATE_START"));

                authorizeDTO auth = new authorizeDTO(
                        result.getInt("ID_AUTHORIZE"),
                        result.getString("AUTHORIZE_NAME"));
                accountDTO acc = new accountDTO(
                        result.getInt("ID_ACCOUNT"),
                        auth,
                        s,
                        result.getString("USERNAME"),
                        result.getString("PASS_WORD"));
                list.add(acc);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return null;
    }

    @Override
    public ArrayList<accountDTO> selectByCondition(String condition) {
        return null;
    }

    public accountDTO selectByID(int id) {
        Connection conn = databaseUtil.getConnection();

        String sql = "SELECT * " +
                "FROM ACCOUNTS JOIN AUTHORIZES " +
                "ON ACCOUNTS.ID_AUTHORIZE = AUTHORIZES.ID_AUTHORIZE " +
                "JOIN STAFF " +
                "ON ACCOUNTS.ID_STAFF = STAFF.ID_STAFF " +
                "WHERE ID_ACCOUNT = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                staffDTO s = new staffDTO(
                        result.getInt("ID_STAFF"),
                        result.getString("FULLNAME"),
                        result.getString("PHONE_NUMBER"),
                        result.getDate("BIRTHDAY"),
                        result.getFloat("SALARY"),
                        result.getString("DATE_START"));

                authorizeDTO auth = new authorizeDTO(
                        result.getInt("ID_AUTHORIZE"),
                        result.getString("AUTHORIZE_NAME"));
                accountDTO acc = new accountDTO(
                        result.getInt("ID_ACCOUNT"),
                        auth,
                        s,
                        result.getString("USERNAME"),
                        result.getString("PASS_WORD"));
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return null;
    }

}
