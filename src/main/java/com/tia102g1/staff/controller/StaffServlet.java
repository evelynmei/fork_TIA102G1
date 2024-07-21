package com.tia102g1.staff.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g1.staff.entity.Staff;
import com.tia102g1.staff.service.StaffService;
import com.tia102g1.staff.service.StaffServiceImpl;

@WebServlet("/tia102g1/staff/staff.do")
public class StaffServlet extends HttpServlet {

	private StaffService staffService;

	public void init() throws ServletException {
		staffService = new StaffServiceImpl();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "getAll":
			forwardPath = getAllStaff(req, res);
			break;
		case "getById":
			forwardPath = getById(req, res);
			break;
		case "compositeQuery":
			forwardPath = getCompositeStaffQuery(req, res);
			break;
		case "addStaff":
			forwardPath = getAddStaff(req, res);
			break;
		case "get_one_update":
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			forwardPath = getUpdate(req, res);
			break;
		default:
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String getAllStaff(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<Staff> staffList = staffService.getAllStaff(currentPage);
		List<Staff> allStaff = staffService.getAllStaff();

		int staffPageQty = staffService.getPageTotal();
		req.getSession().setAttribute("staffPageQty", staffPageQty);
		req.getSession().setAttribute("allStaff", allStaff);
		req.setAttribute("staffList", staffList);
		req.setAttribute("currentPage", currentPage);

		return "/tia102g1/staff/staffPages/listAllStaffEX.jsp";
	}

	private String getById(HttpServletRequest req, HttpServletResponse res) {
		Integer staffId = Integer.parseInt(req.getParameter("staffId"));
		Staff staff = staffService.getStaffByStaffId(staffId);
		List<Staff> staffList = new ArrayList<>();
		staffList.add(staff);
		req.setAttribute("staffList", staffList);
		return "/tia102g1/staff/staffPages/listAllStaffEX.jsp";
	}

	private String getCompositeStaffQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Staff> staffList = staffService.getStaffByCompositeQuery(map);
			req.setAttribute("staffList", staffList);
		} else {
			return "/mainPage.jsp";
		}
		return "/tia102g1/staff/staffPages/listCompositeQueryStaffEX.jsp";
	}

	private String getAddStaff(HttpServletRequest req, HttpServletResponse res) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String password = "Desserter";
		String name = req.getParameter("name");
		String nameReg = "^[\\u4e00-\\u9fa5]+$";
		if (name == null || name.trim().length() == 0) {
			errorMsgs.add("員工姓名:請勿空白");
		} else if (!name.trim().matches(nameReg)) {
			errorMsgs.add("員工姓名:請輸入中文");
		}

		String phone = req.getParameter("phone");
		String phoneReg = "^\\d{10}$";
		if (phone == null || phone.trim().length() == 0) {
			errorMsgs.add("員工電話:請勿空白");
		} else if (!phone.trim().matches(phoneReg)) {
			errorMsgs.add("員工電話:請輸入10碼手機號碼");
		}

		String email = req.getParameter("email");

		Date employDt = null;
		try {
			employDt = java.sql.Date.valueOf(req.getParameter("employDt"));
		} catch (IllegalArgumentException e) {
		}

		Date leaveDt = null;
		try {
			leaveDt = java.sql.Date.valueOf(req.getParameter("leaveDt"));
		} catch (IllegalArgumentException e) {

		}

//		String statusStr = req.getParameter("status");
//		Integer status = statusStr != null ? Integer.valueOf(statusStr) : null;
		Integer status = 1;

		String createdBy = null;
		try {
			createdBy = req.getParameter("createdBy").trim();
		} catch (NumberFormatException e) {
			errorMsgs.add("請輸入員工編號");
		}

		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Timestamp dateCreated = now;

		String lastUpdatedBy = createdBy;

		Timestamp lastUpdated = now;

		Staff staff = new Staff();
		staff.setPassword(password);
		staff.setName(name);
		staff.setPhone(phone);
		staff.setEmail(email);
		staff.setEmployDt((java.sql.Date) employDt);
		staff.setLeaveDt((java.sql.Date) leaveDt);
		staff.setStatus(status);
		staff.setCreatedBy(createdBy);
		staff.setDateCreated(dateCreated);
		staff.setLastUpdatedBy(lastUpdatedBy);
		staff.setLastUpdated(lastUpdated);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("staff", staff);
			return "/tia102g1/staff/staffPages/addStaffEX.jsp";
		} else {
			Staff addStaff = staffService.addStaff(staff);
			req.setAttribute("staff", addStaff);
			return "/tia102g1/staff/staffPages/addStaffSuccessEX.jsp";
		}

	}

	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer staffId = Integer.parseInt(req.getParameter("staffId"));
		Staff staff = staffService.getStaffByStaffId(staffId);
		List<Staff> staffList = new ArrayList<>();
		staffList.add(staff);
		req.setAttribute("staffList", staffList);
		return "/tia102g1/staff/staffPages/updateStaffEX.jsp";
	}

	private String getUpdate(HttpServletRequest req, HttpServletResponse res) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		Integer staffId = Integer.valueOf(req.getParameter("staffId"));
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String nameReg = "^[\\u4e00-\\u9fa5]+$";
		if (name == null || name.trim().length() == 0) {
			errorMsgs.add("員工姓名:請勿空白");
		} else if (!name.trim().matches(nameReg)) {
			errorMsgs.add("員工姓名:請輸入中文");
		}

		String phone = req.getParameter("phone");
		String phoneReg = "^\\d{10}$";
		if (phone == null || phone.trim().length() == 0) {
			errorMsgs.add("員工電話:請勿空白");
		} else if (!phone.trim().matches(phoneReg)) {
			errorMsgs.add("員工電話:請輸入10碼手機號碼");
		}

		String email = req.getParameter("email");

		Date employDt = null;
		try {
			employDt = java.sql.Date.valueOf(req.getParameter("employDt"));
		} catch (IllegalArgumentException e) {
		}

		Date leaveDt = null;
		try {
			leaveDt = java.sql.Date.valueOf(req.getParameter("leaveDt"));
		} catch (IllegalArgumentException e) {
		}
		if (leaveDt != null && employDt != null && leaveDt.compareTo(employDt) < 0) {
			errorMsgs.add("離職日不得早於到職日");
		}

//		String statusStr = req.getParameter("status");
//		Integer status = statusStr != null ? Integer.valueOf(statusStr) : null;
		Integer status = Integer.valueOf(req.getParameter("status"));

		String createdBy = null;
		try {
			createdBy = req.getParameter("createdBy");
		} catch (NumberFormatException e) {
			errorMsgs.add("請輸入員工編號");
		}

		Timestamp now = Timestamp.valueOf(LocalDateTime.now());

		Timestamp dateCreated = null;
		try {
			dateCreated = java.sql.Timestamp.valueOf(req.getParameter("dateCreated"));
		} catch (IllegalArgumentException e) {

		}

		String lastUpdatedBy = null;
		try {
			lastUpdatedBy = req.getParameter("lastUpdatedBy").trim();
		} catch (NumberFormatException e) {
			errorMsgs.add("請輸入員工編號");
		}

		Timestamp lastUpdated = now;

		Staff staff = new Staff();
		staff.setPassword(password);
		staff.setName(name);
		staff.setPhone(phone);
		staff.setEmail(email);
		staff.setEmployDt((java.sql.Date) employDt);
		staff.setLeaveDt((java.sql.Date) leaveDt);
		staff.setStatus(status);
		staff.setCreatedBy(createdBy);
		staff.setDateCreated(dateCreated);
		staff.setLastUpdatedBy(lastUpdatedBy);
		staff.setLastUpdated(lastUpdated);
		staff.setStaffId(staffId);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("staff", staff);
	        List<Staff> staffList = new ArrayList<>();
	        staffList.add(staff);
	        req.setAttribute("staffList", staffList);
			return "/tia102g1/staff/staffPages/updateStaffEX.jsp";
		} else {

			Staff updateStaff = staffService.updateStaff(staff);
//			List<Staff> updateStaffList = new ArrayList<>();
//			updateStaffList.add(updateStaff);
			req.setAttribute("staff", updateStaff);
			return "/tia102g1/staff/staffPages/updateStaffSuccessEX.jsp";
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
