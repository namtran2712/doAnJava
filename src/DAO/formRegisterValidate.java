package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.databaseUtil;

public class formRegisterValidate {
	public static boolean checkRegister(String fullName, String username, String phoneNumber, String pass, String birthDay) {
		
		Connection conn = databaseUtil.getConnection();
		
		try {
			Statement st = conn.createStatement();
			
			String sql = "SELECT *"
					+ " FROM accounts INNER JOIN staff ON accounts.ID_STAFF = staff.ID_STAFF"
					+ " WHERE USERNAME = '" + username +
					"' OR PHONE_NUMBER = '" + phoneNumber + "'" ;
			
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				return false;
			}
			
			sql = "INSERT INTO staff (FULLNAME, PHONE_NUMBER, BIRTHDAY) "
					+ "VALUES ('" + fullName
					+ "', '" + phoneNumber
					+ "', '" + birthDay +
					"')";

			st.executeUpdate(sql);			
			System.out.println(sql);
						
			sql = "SELECT * "
					+ "FROM staff "
					+ "WHERE PHONE_NUMBER = '" + phoneNumber + "'" ;
			
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				sql = "INSERT INTO accounts (ID_AUTHORIZE, ID_STAFF, USERNAME, PASS_WORD) "
						+ "VALUES (" + 1
						+ ", " + rs.getInt(1)
						+ ", '" + username
						+ "', '" + pass +
						"')";
				
				st.executeUpdate(sql);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return true;
	}
}
