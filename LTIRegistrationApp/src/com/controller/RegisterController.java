package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RegisterDao;
import com.model.Register;

/**
 * Servlet implementation class RegisterController
 */

//same name as in form action
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int regno = Integer.parseInt(request.getParameter("regno"));
		String name = request.getParameter("name");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		float bal = Float.parseFloat(request.getParameter("bal"));

		Register r = new Register(regno, name, uname, pass, bal);

		RegisterDao dao = new RegisterDao();
		int i = dao.createData(r);

		if (i > 0)
			response.sendRedirect("login.html"); // if data is inserted redirect to login.html
		else
			response.sendRedirect("error.html");

		// everything can be done in this class only but not the best practice
		// BELOW CODE SHOULD BE AVOIDED
		
//		PrintWriter pw=response.getWriter();
//		System.out.println("debug");
//		pw.println(name);
//		pw.println(uname);
//		pw.println(pass);
//		pw.println(pass);
//		pw.println(bal);

//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sysdb");
//			System.out.println("Connected to Database");
//			PreparedStatement ps = con.prepareStatement("insert into ltidb values(?,?,?,?,?)");
//
//			ps.setInt(1, regno);	
//			ps.setString(2, name);
//			ps.setString(3, uname);
//			ps.setString(4, pass);
//			ps.setFloat(5, bal);
//
//			int i = ps.executeUpdate(); // for DML
//
//			if (i > 0)
//				response.sendRedirect("login.html"); // if data is inserted redirect to login.html
//			else
//				response.sendRedirect("error.html");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doPost is calling doGet so you can write logic in doGet
		doGet(request, response);
	}

}
