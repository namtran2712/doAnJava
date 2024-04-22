package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.staff;
import database.databaseUtil;

public class staffDao implements daoInterface<staff> {

	@Override
	public boolean insert(final staff t) {
		try {
			int rows = 0;
			final Connection conn = databaseUtil.getConnection();
			final Statement st = conn.createStatement();
			final String sql = "INSERT INTO staff (FULLNAME, PHONE_NUMBER, BIRTHDAY, SALARY) "
					+ "VALUES ('" + t.getName() + "', '"
					+ t.getPhoneNumber() + "', '"
					+ t.getBirthday() + "', "
					+ t.getSalary()
					+ ")";
			System.out.println(sql);
			rows = st.executeUpdate(sql);
			databaseUtil.closeConnection(conn);
			return rows>0;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean delete(final staff t) {
		final Connection conn = databaseUtil.getConnection();
		int rows =0;
		try {
			final Statement state = conn.createStatement();

			final String sql = "DELETE FROM STAFF " +
					"WHERE ID_STAFF = " + t.getId();

			System.out.println(sql);

			 rows = state.executeUpdate(sql);

			System.out.println("Số dòng được xóa: " + rows);
			return rows>0;
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		databaseUtil.closeConnection(conn);
		return false;
	}

	@Override
	public boolean update(final staff t) {
		final Connection conn = databaseUtil.getConnection();

		try {
			final Statement state = conn.createStatement();

			final String sql = "UPDATE STAFF " +
					"SET ID_STAFF = " + t.getId() +
					" ,FULLNAME = '" + t.getName() + "'" +
					" ,PHONE_NUMBER = '" + t.getPhoneNumber() + "'" +
					" ,BIRTHDAY = '" + t.getBirthday() + "'" +
					" ,SALARY = " + t.getSalary() +
					" WHERE ID_STAFF = " + t.getId();

			System.out.println(sql);

			final int rows = state.executeUpdate(sql);
			return rows>0;
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		databaseUtil.closeConnection(conn);
		return false;
	}

	@Override
	public ArrayList<staff> selectAll() {
		final Connection conn = databaseUtil.getConnection();

		final ArrayList<staff> list = new ArrayList<staff>();

		try {
			final Statement state = conn.createStatement();
			final String sql = "SELECT * " +
					"FROM STAFF";
			final ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				final staff st = new staff(rs.getInt("ID_STAFF"),
						rs.getString("FULLNAME"),
						rs.getString("PHONE_NUMBER"),
						rs.getDate("BIRTHDAY"),
						rs.getFloat("SALARY"),
						rs.getString("DATE_START"));
				list.add(st);
			}
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		databaseUtil.closeConnection(conn);
		return list;
	}

	@Override
	public ArrayList<staff> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'selectByCondition'");
	}

}
