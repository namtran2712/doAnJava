package DAO;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.authorizeDTO;
import database.databaseUtil;

public class authorizeDao implements daoInterface<authorizeDTO> {

    @Override
    public boolean insert(authorizeDTO t) {
        return false;
    }

    @Override
    public boolean delete(authorizeDTO t) {
        return false;
    }

    @Override
    public boolean update(authorizeDTO t) {
        return false;
    }

    @Override
    public ArrayList<authorizeDTO> selectAll() {
        Connection conn = databaseUtil.getConnection();

        ArrayList<authorizeDTO> list = new ArrayList<authorizeDTO>();
        String sql = "SELECT * " +
                "FROM AUTHORIZES";
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                authorizeDTO auth = new authorizeDTO(
                        result.getInt("ID_AUTHORIZE"),
                        result.getString("AUTHORIZE_NAME"));
                list.add(auth);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return null;
    }

    @Override
    public ArrayList<authorizeDTO> selectByCondition(String condition) {
        return null;
    }

    public authorizeDTO selectByName(String name) {
        Connection conn = databaseUtil.getConnection();

        String sql = "SELECT * " +
                "FROM AUTHORIZES " +
                "WHERE AUTHORIZE_NAME = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                authorizeDTO auth = new authorizeDTO(
                        result.getInt("ID_AUTHORIZE"),
                        result.getString("AUTHORIZE_NAME"));
                return auth;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return null;
    }

}
