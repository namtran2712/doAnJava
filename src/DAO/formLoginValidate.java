package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.databaseUtil;

public class formLoginValidate {
	public static boolean checkUser (String username, String password) {		
		try {
			Connection conn = databaseUtil.getConnection();
			
			Statement st = conn.createStatement();
			
			String sql = "SELECT * "
					+ "FROM accounts "
					+ "WHERE USERNAME = '" + username
					+"' AND PASS_WORD = '" + password + "'";
			
			ResultSet rs = st.executeQuery(sql);

			System.out.println(sql);
			
			while (rs.next()) {
				return true;
			}
			
			databaseUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
