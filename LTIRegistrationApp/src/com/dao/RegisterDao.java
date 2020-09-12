package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Register;

public class RegisterDao {

	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String username = "system";
	private String password = "sysdb";

	public Connection MyConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public int createData(Register r) {
		int i = 0;
		try {
			Connection con = MyConnection();
			System.out.println("Connected to Database | " + con);
			PreparedStatement ps = con.prepareStatement("insert into ltidb values(?,?,?,?,?)");

			ps.setInt(1, r.getRegno());
			ps.setString(2, r.getName());
			ps.setString(3, r.getUname());
			ps.setString(4, r.getPass());
			ps.setFloat(5, r.getBal());

			i = ps.executeUpdate(); // for DML
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Register retrieveRecord(int regno) {
		Register r = null;
		Connection con = MyConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from ltidb where reg_no = ?");
			ps.setInt(1, regno);
			ResultSet result = ps.executeQuery();
			// point cursor to the record
			if (result.next())
				r = new Register(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getFloat(5));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}

	public int deleteRecord(int regno) {
		int row = 0;
		Connection con = MyConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("delete from ltidb where reg_no = ?");
			ps.setInt(1, regno);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return row;
	}

//	public boolean checkRecord(int regno) {
//		Connection con = MyConnection();
//		PreparedStatement ps;
//		int i = 0;
//		try {
//			ps = con.prepareStatement("select reg_no from ltidb where regno = ?");
//			ps.setInt(1, regno);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next())
//				i = rs.getInt(1);
//			if (i != 0)
//				return true;
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return false;
//	}

	public int updateRecord(Register r) {
		int i = 0;
		Connection con = MyConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"update ltidb set name = ?,username = ?, password = ?, balance = ? where reg_no = ?");
			ps.setString(1, r.getName());
			ps.setString(2, r.getUname());
			ps.setString(3, r.getPass());
			ps.setFloat(4, r.getBal());
			ps.setInt(5, r.getRegno());

			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}
}
