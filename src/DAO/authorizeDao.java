package DAO;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.authorize;
import database.databaseUtil;

public class authorizeDao implements daoInterface<authorize> {

    @Override
    public boolean insert(authorize t) {
        return true;
        
    }

    @Override
    public boolean delete(authorize t) {
        return true;
    }   

    @Override
    public boolean update(authorize t) {
        return true;
    }   

    @Override
    public ArrayList<authorize> selectAll() {
        Connection conn = databaseUtil.getConnection();

        ArrayList<authorize> list = new ArrayList<authorize>();
        String sql = "SELECT * " +
                "FROM AUTHORIZES";
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                authorize auth = new authorize(
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
    public ArrayList<authorize> selectByCondition(String condition) {
        return null;
    }

    public authorize selectByName(String name) {
        Connection conn = databaseUtil.getConnection();

        String sql = "SELECT * " +
                "FROM AUTHORIZES " +
                "WHERE AUTHORIZE_NAME = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                authorize auth = new authorize(
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
