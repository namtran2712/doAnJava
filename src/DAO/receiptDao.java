package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.particularReceiptDTO;
import DTO.productDTO;
import DTO.receiptDTO;
import DTO.staffDTO;
import database.databaseUtil;

public class receiptDao implements daoInterface<receiptDTO> {

    @Override
    public boolean insert(receiptDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "INSERT INTO RECEIPTS (ID_STAFF, TOTAL_PRICE) " +
                    "VALUES (? , ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, t.getStaff().getId());
            pst.setFloat(2, t.getTotalPrice());

            int check = pst.executeUpdate();
            if (check > 0) {
                int id = getLastId();
                sql = "INSERT INTO PARTICULAR_RECEIPTS (ID_RECEIPT, ID_PRODUCT, SIZE, QUANTITY, PRICE) " +
                        "VALUES (? , ? , ? , ? , ?)";

                pst = conn.prepareStatement(sql);
                int rows = 0;
                for (particularReceiptDTO p : t.getParticular()) {
                    pst.setInt(1, id);
                    pst.setInt(2, p.getProduct().getIdProduct());
                    pst.setInt(3, p.getSize());
                    pst.setInt(4, p.getQuantity());
                    pst.setFloat(5, p.getPrice());
                    rows += pst.executeUpdate();
                }
                databaseUtil.closeConnection(conn);
                return rows > 0;
            }
            databaseUtil.closeConnection(conn);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return false;
    }

    public int getLastId() {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT ID_RECEIPT " +
                    "FROM RECEIPTS " +
                    "ORDER BY ID_RECEIPT DESC " +
                    "LIMIT 1";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("ID_RECEIPT");
                databaseUtil.closeConnection(conn);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return 0;
    }

    @Override
    public boolean delete(receiptDTO t) {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "DELETE FROM RECEIPTS WHERE ID_RECEIPT = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, t.getIdReceipt());
            int rows = pst.executeUpdate();

            databaseUtil.closeConnection(conn);
            return rows > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return false;
    }

    @Override
    public boolean update(receiptDTO t) {
        return false;
    }

    @Override
    public ArrayList<receiptDTO> selectAll() {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT * " +
                    "FROM RECEIPTS JOIN PARTICULAR_RECEIPTS " +
                    "ON RECEIPTS.ID_RECEIPT = PARTICULAR_RECEIPTS.ID_RECEIPT " +
                    "JOIN staff " +
                    "ON receipts.ID_STAFF = staff.ID_STAFF " +
                    "JOIN products " +
                    "ON particular_receipts.ID_PRODUCT = products.ID_PRODUCT";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            int i = 0;
            ArrayList<receiptDTO> list = new ArrayList<receiptDTO>();

            while (result.next()) {
                if (i != result.getInt("ID_RECEIPT")) {
                    staffDTO staff = new staffDTO(
                            result.getInt("ID_STAFF"),
                            result.getString("FULLNAME"),
                            result.getString("PHONE_NUMBER"),
                            result.getDate("BIRTHDAY"),
                            result.getFloat("SALARY"),
                            result.getString("DATE_START"));

                    ArrayList<particularReceiptDTO> particular = new ArrayList<particularReceiptDTO>();

                    productDTO product = new productDTO(
                            result.getInt("ID_PRODUCT"),
                            result.getInt("ID_CATEGORY"),
                            result.getInt("ID_MATERIAL"),
                            result.getString("PRODUCT_NAME"),
                            result.getInt("QUANTITY_SOLD"),
                            result.getString("LINK_IMAGE"),
                            null);

                    particularReceiptDTO tmp = new particularReceiptDTO(
                            result.getInt("ID_RECEIPT"),
                            product,
                            result.getInt("SIZE"),
                            result.getInt("QUANTITY"),
                            result.getFloat("PRICE"));

                    receiptDTO receipt = new receiptDTO(
                            result.getInt("ID_RECEIPT"),
                            staff,
                            result.getFloat("TOTAL_PRICE"),
                            result.getTimestamp("DATE_RECEIPT"),
                            particular);
                    receipt.getParticular().add(tmp);

                    list.add(receipt);
                    i = result.getInt("ID_RECEIPT");
                } else {
                    productDTO product = new productDTO(
                            result.getInt("ID_PRODUCT"),
                            result.getInt("ID_CATEGORY"),
                            result.getInt("ID_MATERIAL"),
                            result.getString("PRODUCT_NAME"),
                            result.getInt("QUANTITY_SOLD"),
                            result.getString("LINK_IMAGE"),
                            null);

                    particularReceiptDTO tmp = new particularReceiptDTO(
                            result.getInt("ID_RECEIPT"),
                            product,
                            result.getInt("SIZE"),
                            result.getInt("QUANTITY"),
                            result.getFloat("PRICE"));
                    list.get(list.size() - 1).getParticular().add(tmp);
                }
            }
            databaseUtil.closeConnection(conn);
            return list;

        } catch (SQLException e) {

        }
        databaseUtil.closeConnection(conn);
        return null;
    }

    @Override
    public ArrayList<receiptDTO> selectByCondition(String condition) {
        return null;
    }

    public float getChiPhiCurrent() {
        Connection conn = databaseUtil.getConnection();

        try {
            String sql = "SELECT SUM(TOTAL_PRICE)\r\n" +
                    "FROM receipts\r\n" +
                    "WHERE DATE(DATE_RECEIPT) = CURDATE()";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                float total = result.getFloat("SUM(TOTAL_PRICE)");
                databaseUtil.closeConnection(conn);
                return total;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseUtil.closeConnection(conn);
        return 0;
    }
}
