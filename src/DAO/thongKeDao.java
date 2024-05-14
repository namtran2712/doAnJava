package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.thongkeDTO;
import database.databaseUtil;

public class thongKeDao {
    public ArrayList<thongkeDTO> selectAll() {
        Connection conn = databaseUtil.getConnection();

        try {
            ArrayList<thongkeDTO> list = new ArrayList<thongkeDTO>();
            String sql = "SELECT CONCAT(MONTH(date_col), '/', YEAR(date_col)) AS 'Thoi gian', SUM(doanh_thu) AS 'Doanh thu', SUM(chi_phi) AS 'Chi phi' "
                    +
                    "FROM " +
                    "( " +
                    "SELECT DATE_BILL AS date_col, TOTAL_BILL AS doanh_thu, 0 AS chi_phi " +
                    "FROM bills " +
                    "UNION ALL " +
                    "SELECT DATE_RECEIPT AS date_col, 0 AS doanh_thu, TOTAL_PRICE AS chi_phi " +
                    "FROM receipts " +
                    ") AS combined_data " +
                    "GROUP BY CONCAT(MONTH(date_col), '/', YEAR(date_col)) " +
                    "ORDER BY `Thoi gian` DESC ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                thongkeDTO tmp = new thongkeDTO(
                        result.getString("Thoi gian"),
                        result.getFloat("Doanh thu"),
                        result.getFloat("Chi phi"));
                list.add(tmp);
            }
            databaseUtil.closeConnection(conn);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return null;
    }
}
