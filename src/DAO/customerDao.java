package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.customerDTO;
import database.databaseUtil;

public class customerDao implements daoInterface<customerDTO> {
    public static void main(String[] args) {
        customerDao a = new customerDao();
        a.insert(null);
    }

    @Override
    public boolean delete(customerDTO t) {
        try {
            Connection connect = databaseUtil.getConnection();
            Statement statement = connect.createStatement();

            String sql = "DELETE FROM CUSTOMER WHERE ID_CUSTOMER = "
                    + t.getId();

            int rows = statement.executeUpdate(sql);
            databaseUtil.closeConnection(connect);
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(customerDTO t) {
        try {
            Connection connection = databaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO CUSTOMER (FULLNAME, PHONE_NUMBER, BIRTHDAY) VALUES('"
                    + t.getName() + "','"
                    + t.getPhoneNumber() + "'','"
                    + t.getBirthday()
                    + "')";

            int rows = statement.executeUpdate(sql);
            databaseUtil.closeConnection(connection);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<customerDTO> selectAll() {
        try {
            Connection connection = databaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM CUSTOMER";

            Boolean result = statement.execute(sql);

            if (result) {
                ArrayList<customerDTO> customerList = new ArrayList<customerDTO>();
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    customerDTO tmp = new customerDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
                    customerList.add(tmp);
                }
                return customerList;
            }

            databaseUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<customerDTO> selectByCondition(String condition) {

        try {
            Connection connection = databaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM CUSTOMER WHERE ";

            Boolean result = statement.execute(sql);
            databaseUtil.closeConnection(connection);

            if (result) {
                ArrayList<customerDTO> customerList = new ArrayList<customerDTO>();
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    customerDTO tmp = new customerDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
                    customerList.add(tmp);
                }
                return customerList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(customerDTO t) {

        try {
            Connection connection = databaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "UPDATE CUSTOMER SET ID_CUSTOMER ="
                    + t.getId() + " , FULLNAME = '"
                    + t.getName() + " ',PHONE_NUMBER = '"
                    + t.getPhoneNumber() + " ',BIRTHDAY = '"
                    + t.getBirthday()
                    + " ' WHERE ID_CUSTOMER = "
                    + t.getId();

            int rows = statement.executeUpdate(sql);
            databaseUtil.closeConnection(connection);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
