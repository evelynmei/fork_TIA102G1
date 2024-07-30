//package com.tia102g1.staff.staffLogin;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//@WebServlet("/tia102g1/staff/staffLogin/Loginhandler")
//public class Loginhandler extends HttpServlet {
//
////	protected boolean allowUser(HttpServletRequest req, Object staffId, String password) {
////		if ((Integer.parseInt((req.getParameter("staffId")).equals(staffId) && (req.getParameter("password")).equals(password)))
////			return true;
////		else
////			return false;
////	}
//	
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("Big5");
//		res.setContentType("text/html; charset = Big5");
//		PrintWriter out = res.getWriter();
//
//		Integer staffId = Integer.valueOf(req.getParameter("staffId"));
//		String password = req.getParameter("password");
//
////		if (!allowUser(req, staffId, password)) {
////			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
////			out.println("<BODY>你的帳號 , 密碼無效!<BR>");
////			out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/tia102g1/staff/staffLogin/staffLoginPage.jsp>重新登入</A>");
////			out.println("</BODY></HTML>");
////		} else {
////			HttpSession session = req.getSession();
////			session.setAttribute("staffId", staffId);
//
//			try {
//				String location = (String) session.getAttribute("location");
//				if (location != null) {
//					res.sendRedirect(location);
//					return;
//				}
//			} catch (Exception ignored) {
//			}
//			res.sendRedirect(req.getContextPath() + "/tia102g1/index.jsp");
//		}
//
//	}
//
//}
