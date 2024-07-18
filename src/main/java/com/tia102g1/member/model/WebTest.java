package com.tia102g1.member.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/webtest")
public class WebTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>SEND TEST</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<p>Hello</p>");
		out.println("<HTML>");
		out.println("</BODY></HTML>");
	}
}
