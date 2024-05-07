package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.billDTO;
import DTO.customerDTO;
import DTO.particularBill;
import DTO.productDTO;
import DTO.staffDTO;
import database.databaseUtil;

public class billDao implements daoInterface<billDTO> {
    @Override
    public boolean insert(billDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "INSERT INTO BILLS (ID_STAFF, ID_CUSTOMER, TOTAL_BILL) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, t.getStaff().getId());
            state.setInt(2, t.getCustomer().getId());
            state.setFloat(3, t.getTotal());

            int rows = state.executeUpdate();
            int id = getLastId();

            for (particularBill p : t.getDetail()) {
                sql = "INSERT INTO PARTICULAR_BILLS (ID_BILL, ID_PRODUCT, SIZE, PRICE, QUANTITY) " +
                        "VALUES (?, ?, ?, ?, ?)";
                state = conn.prepareStatement(sql);
                state.setInt(1, id);
                state.setInt(2, p.getProduct().getIdProduct());
                state.setInt(3, p.getSize());
                state.setFloat(4, p.getPrice());
                state.setInt(5, p.getQuantity());
                rows += state.executeLargeUpdate();
            }
            databaseUtil.closeConnection(conn);
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return false;

    }

    public int getLastId() {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT ID_BILL " +
                    "FROM BILLS " +
                    "ORDER BY ID_BILL DESC " +
                    "LIMIT 1";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("ID_BILL");
                databaseUtil.closeConnection(conn);
                return id;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return 0;
    }

    @Override
    public boolean delete(billDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "UPDATE BILLS SET " +
                    "STATUS_BILL = 0 " +
                    "WHERE ID_BILL = ?";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, t.getIdBill());
            int rows = state.executeUpdate();
            databaseUtil.closeConnection(conn);
            return rows > 1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return false;
    }

    @Override
    public boolean update(billDTO t) {
        return true;
    }

    @Override
    public ArrayList<billDTO> selectAll() {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT * " +
                    " FROM BILLS JOIN PARTICULAR_BILLS ON BILLS.ID_BILL = PARTICULAR_BILLS.ID_BILL" +
                    " JOIN STAFF ON BILLS.ID_STAFF = STAFF.ID_STAFF" +
                    " JOIN CUSTOMER ON BILLS.ID_CUSTOMER = CUSTOMER.ID_CUSTOMER" +
                    " JOIN PRODUCTS ON PRODUCTS.ID_PRODUCT = PARTICULAR_BILLS.ID_PRODUCT" +
                    " WHERE STATUS_BILL = 1";
            PreparedStatement state = conn.prepareStatement(sql);

            ResultSet rs = state.executeQuery();

            ArrayList<billDTO> arrayList = new ArrayList<billDTO>();
            int currentID = -1;
            while (rs.next()) {
                if (rs.getInt("BILLS.ID_BILL") != currentID) {
                    currentID = rs.getInt("BILLS.ID_BILL");
                    productDTO product = new productDTO(
                            rs.getInt("PRODUCTS.ID_PRODUCT"),
                            rs.getInt("ID_CATEGORY"),
                            rs.getInt("ID_MATERIAL"),
                            rs.getString("PRODUCT_NAME"),
                            rs.getInt("QUANTITY_SOLD"),
                            rs.getString("LINK_IMAGE"), null);

                    particularBill tmp1 = new particularBill(
                            product,
                            rs.getInt("SIZE"),
                            rs.getInt("PARTICULAR_BILLS.QUANTITY"),
                            rs.getFloat("PRICE"));

                    staffDTO st = new staffDTO(rs.getInt("BILLS.ID_STAFF"),
                            rs.getString("STAFF.FULLNAME"),
                            rs.getString("STAFF.PHONE_NUMBER"),
                            rs.getDate("STAFF.BIRTHDAY"),
                            rs.getFloat("STAFF.SALARY"),
                            rs.getString("STAFF.DATE_START"));

                    customerDTO ct = new customerDTO(rs.getInt("CUSTOMER.ID_CUSTOMER"),
                            rs.getString("CUSTOMER.FULLNAME"),
                            rs.getString("CUSTOMER.PHONE_NUMBER"),
                            rs.getDate("CUSTOMER.BIRTHDAY"));

                    billDTO tmp2 = new billDTO(rs.getInt("BILLS.ID_BILL"),
                            st,
                            ct,
                            rs.getFloat("BILLS.TOTAL_BILL"),
                            rs.getTimestamp("BILLS.DATE_BILL"),
                            tmp1);

                    currentID = rs.getInt("BILLS.ID_BILL");

                    arrayList.add(tmp2);

                } else {
                    productDTO product = new productDTO(
                            rs.getInt("PRODUCTS.ID_PRODUCT"),
                            rs.getInt("ID_CATEGORY"),
                            rs.getInt("ID_MATERIAL"),
                            rs.getString("PRODUCT_NAME"),
                            rs.getInt("QUANTITY_SOLD"),
                            rs.getString("LINK_IMAGE"), null);

                    particularBill tmp1 = new particularBill(
                            product,
                            rs.getInt("SIZE"),
                            rs.getInt("PARTICULAR_BILLS.QUANTITY"),
                            rs.getFloat("PRICE"));
                    arrayList.get(arrayList.size() - 1).getDetail().add(tmp1);
                }
            }
            databaseUtil.closeConnection(conn);
            return arrayList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        databaseUtil.closeConnection(conn);
        return null;
    }

    @Override
    public ArrayList<billDTO> selectByCondition(String condition) {

        return null;
    }

}
