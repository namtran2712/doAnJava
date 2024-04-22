package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.bill;
import DTO.customer;
import DTO.particularBill;
import DTO.staff;
import database.databaseUtil;

public class billDao implements daoInterface<bill> {
    @Override
    public boolean insert(bill t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "INSERT INTO BILLS (ID_STAFF, ID_CUSTOMER, TOTAL_BILL) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, t.getStaff().getId());
            state.setInt(2, t.getCustomer().getId());
            state.setFloat(3, t.getTotal());

            int rows = state.executeUpdate();

            particularBill detail = t.getDetail();
            for (int i = 0; i < detail.getIdProduct().size(); i++) {
                sql = "INSERT INTO PARTICULAR_BILLS (ID_BILLS, ID_PRODUCT, QUANTITY) " +
                        "VALUES (?, ?, ?)";
                state = conn.prepareStatement(sql);
                state.setInt(1, t.getIdBill());
                state.setInt(2, detail.getIdProduct().get(i));
                state.setInt(3, detail.getQuantity().get(i));
                rows +=state.executeUpdate();
                
            }

            return rows > 0;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        databaseUtil.closeConnection(conn);
        return false;

    }

    @Override
    public boolean delete(bill t) {
        Connection conn = databaseUtil.getConnection();

        try {
            int rows;
            String sql = "DELETE FROM BILLS WHERE ID_BILL = ?";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, t.getIdBill());
            state.executeUpdate();

            sql = "DELETE FROM PARTICULAR_BILLS WHERE ID_BILL = ?";
            state = conn.prepareStatement(sql);
            state.setInt(1, t.getIdBill());
            rows =state.executeUpdate();
            return rows>1;   
        } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

        databaseUtil.closeConnection(conn);
        return false;
    }

    @Override
    public boolean update(bill t) {
        return true;
    }

    @Override
    public ArrayList<bill> selectAll() {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT * " +
                    " FROM BILLS JOIN PARTICULAR_BILLS ON BILLS.ID_BILL = PARTICULAR_BILLS.ID_BILL" +
                    " JOIN STAFF ON BILLS.ID_STAFF = STAFF.ID_STAFF" +
                    " JOIN CUSTOMER ON BILLS.ID_CUSTOMER = CUSTOMER.ID_CUSTOMER";
            PreparedStatement state = conn.prepareStatement(sql);

            ResultSet rs = state.executeQuery();

            ArrayList<bill> arrayList = new ArrayList<bill>();
            int currentID = -1;
            while (rs.next()) {
                if (rs.getInt("BILLS.ID_BILL") != currentID) {
                    currentID = rs.getInt("BILLS.ID_BILL");
                    particularBill tmp1 = new particularBill(
                            rs.getInt("PARTICULAR_BILLS.ID_PRODUCT"),
                            rs.getInt("PARTICULAR_BILLS.QUANTITY"));

                    staff st = new staff(rs.getInt("BILLS.ID_STAFF"),
                            rs.getString("STAFF.FULLNAME"),
                            rs.getString("STAFF.PHONE_NUMBER"),
                            rs.getDate("STAFF.BIRTHDAY"),
                            rs.getFloat("STAFF.SALARY"),
                            rs.getString("STAFF.DATE_START"));

                    customer ct = new customer(rs.getInt("CUSTOMER.ID_CUSTOMER"),
                            rs.getString("CUSTOMER.FULLNAME"),
                            rs.getString("CUSTOMER.PHONE_NUMBER"),
                            rs.getDate("CUSTOMER.BIRTHDAY"));

                    bill tmp2 = new bill(rs.getInt("BILLS.ID_BILL"),
                            st,
                            ct,
                            rs.getFloat("BILLS.TOTAL_BILL"),
                            rs.getDate("BILLS.DATE_BILL"),
                            tmp1);

                    currentID = rs.getInt("BILLS.ID_BILL");

                    arrayList.add(tmp2);

                } else {

                    arrayList.get(arrayList.size() - 1).getDetail().getIdProduct()
                            .add(rs.getInt("PARTICULAR_BILLS.ID_PRODUCT"));
                    arrayList.get(arrayList.size() - 1).getDetail().getQuantity()
                            .add(rs.getInt("PARTICULAR_BILLS.QUANTITY"));

                }
            }
            return arrayList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return null;
    }

    @Override
    public ArrayList<bill> selectByCondition(String condition) {

        return null;
    }

}
