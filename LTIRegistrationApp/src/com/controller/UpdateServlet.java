package com.controller;

import java.awt.PrintGraphics;
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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int regno=Integer.parseInt(request.getParameter("regno"));
		String name=request.getParameter("name");
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		Float bal=Float.parseFloat(request.getParameter("bal"));
		
		Register r =new Register(regno, name, uname, pass, bal);
		RegisterDao rd=new RegisterDao();
		int i=rd.updateRecord(r);
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		if(i>0) {
			pw.println("<h2>"+i+" record updated</h2>");
			r=rd.retrieveRecord(regno);
			pw.print("<p>After Updation:</p>");
			pw.print("<tr><td>" + r.getRegno() + " </td><td>" + r.getName() + " </td><td>" + r.getUname() + " </td><td>"
					+ r.getPass() + "</td><td> " + r.getBal() + "</td></tr>");
		}
		else
			response.sendRedirect("error.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
