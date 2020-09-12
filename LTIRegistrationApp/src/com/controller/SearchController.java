package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RegisterDao;
import com.model.Register;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
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

		RegisterDao rd = new RegisterDao();
		Register r = rd.retrieveRecord(regno);

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		if (r != null) {
			pw.print("<h2>Record found</h2>");
			pw.print("<tr><td>" + r.getRegno() + " </td><td>" + r.getName() + " </td><td>" + r.getUname() + " </td><td>"
					+ r.getPass() + "</td><td> " + r.getBal() + "</td></tr>");
		} else {
			response.sendRedirect("error.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
