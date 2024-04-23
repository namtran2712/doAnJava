package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.staffDTO;
import database.databaseUtil;

public class staffDao implements daoInterface<staffDTO> {

	@Override
	public boolean insert(staffDTO t) {
		try {
			final Connection conn = databaseUtil.getConnection();
			final Statement st = conn.createStatement();
			final String sql = "INSERT INTO staff (FULLNAME, PHONE_NUMBER, BIRTHDAY) "
					+ "VALUES ('" + t.getName() + "', '"
					+ t.getPhoneNumber() + "', '"
					+ t.getBirthday() + "'"
					+ ")";
			int rows = st.executeUpdate(sql);
			databaseUtil.closeConnection(conn);
			return rows > 0;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public staffDTO getLastInsert() {
		Connection conn = databaseUtil.getConnection();

		String sql = "SELECT * " +
				"FROM STAFF " +
				"ORDER BY ID_STAFF DESC " +
				"LIMIT 1";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet result = pst.executeQuery();
			staffDTO staff;
			while (result.next()) {
				staff = new staffDTO(
						result.getInt("ID_STAFF"),
						result.getString("FULLNAME"),
						result.getString("PHONE_NUMBER"),
						result.getDate("BIRTHDAY"),
						result.getFloat("SALARY"),
						null);
				return staff;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		databaseUtil.closeConnection(conn);
		return null;
	}

	@Override
	public boolean delete(final staffDTO t) {
		final Connection conn = databaseUtil.getConnection();

		try {
			final Statement state = conn.createStatement();

			final String sql = "DELETE FROM STAFF " +
					"WHERE ID_STAFF = " + t.getId();

			final int rows = state.executeUpdate(sql);
			return rows > 0;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		databaseUtil.closeConnection(conn);
		return false;
	}

	@Override
	public boolean update(final staffDTO t) {
		final Connection conn = databaseUtil.getConnection();

		try {
			final Statement state = conn.createStatement();
			String sql;
			if (t.getBirthday() == null) {
				sql = "UPDATE STAFF " +
						"SET ID_STAFF = " + t.getId() +
						" ,FULLNAME = '" + t.getName() + "'" +
						" ,PHONE_NUMBER = '" + t.getPhoneNumber() + "'" +
						" ,SALARY = " + t.getSalary() +
						" WHERE ID_STAFF = " + t.getId();
			} else {
				sql = "UPDATE STAFF " +
						"SET ID_STAFF = " + t.getId() +
						" ,FULLNAME = '" + t.getName() + "'" +
						" ,PHONE_NUMBER = '" + t.getPhoneNumber() + "'" +
						" ,BIRTHDAY = '" + t.getBirthday() + "'" +
						" ,SALARY = " + t.getSalary() +
						" WHERE ID_STAFF = " + t.getId();
			}
			final int rows = state.executeUpdate(sql);
			return rows > 0;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		databaseUtil.closeConnection(conn);
		return false;
	}

	@Override
	public ArrayList<staffDTO> selectAll() {
		final Connection conn = databaseUtil.getConnection();

		final ArrayList<staffDTO> list = new ArrayList<staffDTO>();

		try {
			final Statement state = conn.createStatement();
			final String sql = "SELECT * " +
					"FROM STAFF";
			final ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				final staffDTO st = new staffDTO(rs.getInt("ID_STAFF"),
						rs.getString("FULLNAME"),
						rs.getString("PHONE_NUMBER"),
						rs.getDate("BIRTHDAY"),
						rs.getFloat("SALARY"),
						rs.getString("DATE_START"));
				list.add(st);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		databaseUtil.closeConnection(conn);
		return list;
	}

	@Override
	public ArrayList<staffDTO> selectByCondition(String condition) {
		throw new UnsupportedOperationException("Unimplemented method 'selectByCondition'");
	}

}
