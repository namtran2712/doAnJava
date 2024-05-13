package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.particularProduct;
import DTO.productDTO;
import database.databaseUtil;

@SuppressWarnings("unused")
public class productDao implements daoInterface<productDTO> {
    int sodongthaydoi = 0;

    @Override
    public boolean delete(productDTO t) {
        return true;
    }

    @Override
    public boolean insert(productDTO t) {
        // ket noi co so du lieu
        Connection conc = databaseUtil.getConnection();
        // tao doi tuong statement
        try {
            Statement st = conc.createStatement();
            String sql = "insert into products (ID_PRODUCT,ID_CATEGORY,ID_MATERIAL,PRODUCT_NAME,QUANTITY_SOLD,LINK_IMAGE)"
                    + " values ("
                    + t.getIdProduct() + ","
                    + t.getIdCategory() + ","
                    + t.getIdMaterial() + ",'"
                    + t.getName() + "',"
                    + t.getQuantitySold() + ",'"
                    + t.getLinkImg() + ",'"
                    + ")";

            // thuc thi cau lenh sql
            sodongthaydoi = st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<productDTO> selectAll() {
        ArrayList<productDTO> result = new ArrayList<productDTO>();
        Connection conc = databaseUtil.getConnection();
        try {

            Statement stmt = conc.createStatement();
            String sql = "SELECT * " + //
                    "FROM products pd, particular_products " +
                    "WHERE pd.ID_PRODUCT = particular_products.ID_PRODUCT" +
                    " ";
            ResultSet rs = stmt.executeQuery(sql);
            int currentId = -1;
            productDTO tmp;
            while (rs.next()) {
                if (currentId != rs.getInt("ID_PRODUCT")) {
                    tmp = new productDTO();

                    tmp = new productDTO();
                    tmp.setIdProduct(rs.getInt("ID_PRODUCT"));
                    tmp.setIdCategory(rs.getInt("ID_CATEGORY"));
                    tmp.setIdMaterial(rs.getInt("ID_MATERIAL"));
                    tmp.setName(rs.getString("PRODUCT_NAME"));
                    tmp.setQuantitySold(rs.getInt("QUANTITY_SOLD"));
                    tmp.setLinkImg(rs.getString("LINK_IMAGE"));
                    currentId = rs.getInt("ID_PRODUCT");
                    tmp.setParticularProducts(rs.getInt("SIZE"), rs.getFloat("PRICE"), rs.getInt("QUANTITY_REMAIN"));
                    result.add(tmp);
                    currentId = tmp.getIdProduct();
                    // System.out.println(tmp.getLinkImg());
                } else {
                    result.get(result.size() - 1).setParticularProducts(rs.getInt("SIZE"), rs.getFloat("PRICE"),
                            rs.getInt("QUANTITY_REMAIN"));
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(productDTO t) {
        return true;
    }

    public void insertParticularProduct(int idProduct, int size, float price, int quantityRemain) {
        Connection conc = databaseUtil.getConnection();
        try {
            Statement stmt = conc.createStatement();
            String sql = "INSERT INTO particular_productS " +
                    "values("
                    + idProduct + ","
                    + size + ","
                    + price + ","
                    + quantityRemain
                    + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<productDTO> selectByCondition(String condition) {
        //
        throw new UnsupportedOperationException("Unimplemented method 'selectByCondition'");
    }

    public productDTO selectByName(String name) {
        Connection conc = databaseUtil.getConnection();
        try (Statement stmt = conc.createStatement()) {
            String sql = "Select * from products join particular_products on particular_products.ID_PRODUCT=products.ID_PRODUCT "
                    +
                    "WHERE products.product_name ='" + name + "'";
                    System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            productDTO tmp;
            tmp = new productDTO();
            int currentId = -1;
            while (rs.next()) {
                if (currentId != rs.getInt("ID_PRODUCT")) {
                    tmp.setIdProduct(rs.getInt("ID_PRODUCT"));
                    tmp.setIdCategory(rs.getInt("ID_CATEGORY"));
                    tmp.setIdMaterial(rs.getInt("ID_MATERIAL"));
                    tmp.setName(rs.getString("PRODUCT_NAME"));
                    tmp.setQuantitySold(rs.getInt("QUANTITY_SOLD"));
                    tmp.setLinkImg(rs.getString("LINK_IMAGE"));
                    currentId = rs.getInt("ID_PRODUCT");
                    tmp.setParticularProducts(rs.getInt("SIZE"), rs.getFloat("PRICE"),
                            rs.getInt("QUANTITY_REMAIN"));
                } else {
                    tmp.setParticularProducts(rs.getInt("SIZE"), rs.getFloat("PRICE"),
                            rs.getInt("QUANTITY_REMAIN"));
                }
            }
            return tmp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public productDTO selectById(int id) {
        Connection conc = databaseUtil.getConnection();
        try {

            Statement stmt = conc.createStatement();
            String sql = "SELECT * " +
                    "FROM products join particular_products on particular_products.ID_PRODUCT=products.ID_PRODUCT " +
                    "WHERE particular_products.ID_PRODUCT= " + id;
            ResultSet rs = stmt.executeQuery(sql);
            productDTO tmp;
            tmp = new productDTO();
            int currentId = -1;
            while (rs.next()) {
                if (currentId != rs.getInt("ID_PRODUCT")) {
                    tmp.setIdProduct(rs.getInt("ID_PRODUCT"));
                    tmp.setIdCategory(rs.getInt("ID_CATEGORY"));
                    tmp.setIdMaterial(rs.getInt("ID_MATERIAL"));
                    tmp.setName(rs.getString("PRODUCT_NAME"));
                    tmp.setQuantitySold(rs.getInt("QUANTITY_SOLD"));
                    tmp.setLinkImg(rs.getString("LINK_IMAGE"));
                    currentId = rs.getInt("ID_PRODUCT");
                    tmp.setParticularProducts(rs.getInt("SIZE"), rs.getFloat("PRICE"),
                            rs.getInt("QUANTITY_REMAIN"));
                } else {
                    tmp.setParticularProducts(rs.getInt("SIZE"), rs.getFloat("PRICE"),
                            rs.getInt("QUANTITY_REMAIN"));
                }
            }
            return tmp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean checkId(int id) {
        Connection conc = databaseUtil.getConnection();

        try {
            Statement stmt = conc.createStatement();
            String sql = "Select * form products where ID_PRODUCT=" + id;
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
