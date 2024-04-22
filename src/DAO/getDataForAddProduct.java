package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.databaseUtil;

public class getDataForAddProduct {
    public static String[] getCategory() {
        Connection conn = databaseUtil.getConnection();
        try {
            ArrayList<String> sTmp = new ArrayList<String>();

            java.sql.Statement state = conn.createStatement();
            String sql = "SELECT CATEGORY_NAME " +
                    "FROM CATEGORY";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                sTmp.add(rs.getString(1));
            }
            int i = 0;

            String[] s = new String[sTmp.size()];
            for (String string : sTmp) {
                s[i] = string;
                i++;
            }

            return s;

        } catch (SQLException e) {
            // TODO: handle exception
        }

        databaseUtil.closeConnection(conn);
        return null;
    }

    public static String[] getMaterial() {
        Connection conn = databaseUtil.getConnection();

        try {
            ArrayList<String> arrayList = new ArrayList<String>();
            Statement state = conn.createStatement();
            String sql = "SELECT MATERIAL_NAME " +
                    "FROM MATERIAL";

            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                arrayList.add(rs.getString(1));
            }

            String[] s = arrayList.toArray(new String[arrayList.size()]);

            return s;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return null;
    }
}
