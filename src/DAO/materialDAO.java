package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.materialDTO;
import database.databaseUtil;

public class materialDAO implements daoInterface<materialDTO> {

    @Override
    public boolean delete(materialDTO t) {
        return true;
    }

    @Override
    public boolean insert(materialDTO t) {
        return true;
    }

    @Override
    public ArrayList<materialDTO> selectAll() {
        Connection conc = databaseUtil.getConnection();
        ArrayList<materialDTO> result = new ArrayList<>();
        try {
            Statement stmt = conc.createStatement();
            String sql = "select * from material";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                materialDTO material = new materialDTO(rs.getInt(1), rs.getString(2 ));
                result.add(material);
            }
            databaseUtil.closeConnection(conc);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<materialDTO> selectByCondition(String condition) {
        return null;
    }

    @Override
    public boolean update(materialDTO t) {
        // TODO Auto-generated method stub
        return true;

    }

}
