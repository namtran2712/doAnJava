package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale.Category;

import DTO.categoryDTO;
import database.databaseUtil;

public class categoryDAO implements daoInterface<categoryDTO> {

    @Override
    public boolean delete(categoryDTO t) {
        return true;
    }

    @Override
    public boolean insert(categoryDTO t) {
        return true;
    }

    @Override
    public ArrayList<categoryDTO> selectAll() {
        Connection conc = databaseUtil.getConnection();
        ArrayList<categoryDTO> result = new ArrayList<>();
        try {
            Statement stmt = conc.createStatement();
            String sql = "SELECT * FROM category ";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                categoryDTO category = new categoryDTO(rs.getInt(1), rs.getString(2));
                result.add(category);
            }
            databaseUtil.closeConnection(conc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public categoryDTO selectByID(int id) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT * " +
                    "FROM CATEGORY " +
                    "WHERE ID_CATEGORY = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                categoryDTO cate = new categoryDTO(
                        result.getInt("ID_CATEGORY"),
                        result.getString("CATEGORY_NAME"));
                databaseUtil.closeConnection(conn);
                return cate;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<categoryDTO> selectByCondition(String condition) {
        return null;
    }

    @Override
    public boolean update(categoryDTO t) {
        return true;
    }

}
