package com.staff.controller;

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
import javax.servlet.http.HttpSession;

import com.staff.entity.Staff;
import com.staff.service.StaffService;
import com.staff.service.StaffServiceImpl;

@WebServlet("/staff/staff.do")
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
		case "login":
			forwardPath = getLogin(req, res);
			break;
		case "changePW":
			forwardPath = getChangePW(req, res);
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

		return "/staff/staffPages/listAllStaffEX.jsp";
	}

	private String getById(HttpServletRequest req, HttpServletResponse res) {
		Integer staffId = Integer.parseInt(req.getParameter("staffId"));
		Staff staff = staffService.getStaffByStaffId(staffId);
		List<Staff> staffList = new ArrayList<>();
		staffList.add(staff);
		req.setAttribute("staffList", staffList);
		return "/staff/staffPages/listAllStaffEX.jsp";
	}

	private String getCompositeStaffQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Staff> staffList = staffService.getStaffByCompositeQuery(map);
			req.setAttribute("staffList", staffList);
		} else {
			return "/mainPage.jsp";
		}
		return "/staff/staffPages/listCompositeQueryStaffEX.jsp";
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
			return "/staff/staffPages/addStaffEX.jsp";
		} else {
			Staff addStaff = staffService.addStaff(staff);
			req.setAttribute("staff", addStaff);
			return "/staff/staffPages/addStaffSuccessEX.jsp";
		}

	}

	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer staffId = Integer.parseInt(req.getParameter("staffId"));
		Staff staff = staffService.getStaffByStaffId(staffId);
		List<Staff> staffList = new ArrayList<>();
		staffList.add(staff);
		req.setAttribute("staffList", staffList);
		return "/staff/staffPages/updateStaffEX.jsp";
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
			return "/staff/staffPages/updateStaffEX.jsp";
		} else {
			Staff updateStaff = staffService.updateStaff(staff);
			req.setAttribute("staff", updateStaff);
			return "/staff/staffPages/updateStaffSuccessEX.jsp";
		}
	}

	private String getLogin(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String staffIdStr = req.getParameter("staffId");
		if (staffIdStr == null || staffIdStr.trim().length() == 0) {
			errorMsgs.add("員工編號:請勿空白");
		}

		Integer staffId = null;
		if (staffIdStr != null && staffIdStr.trim().length() != 0) {
			try {
				staffId = Integer.valueOf(staffIdStr);
			} catch (NumberFormatException e) {
				errorMsgs.add("請確認員工編號");
			}
		}

		if (!errorMsgs.isEmpty()) {
			return "/staff/staffLogin/staffLoginPage.jsp";
		}

		String password = req.getParameter("password");
		if (password == null || password.trim().length() == 0) {
			errorMsgs.add("密碼:請勿空白");
		}

		List<Staff> allStaff = staffService.getAllStaff();

		Staff changePassword = null;
		Staff currectStaff = null;
		for (Staff staff : allStaff) {
			if (staffId.equals(staff.getStaffId()) && password.equals("Desserter")) {
				changePassword = staff;
				req.getSession().setAttribute("staffId", staffId);
			} else if (staffId.equals(staff.getStaffId()) && password.equals(staff.getPassword())) {
				currectStaff = staff;
				req.getSession().setAttribute("staffId", staffId);
				break;
			}
		}

		if (changePassword != null) {
			return "/staff/staffLogin/staffChangePW.jsp";
		} else if (currectStaff != null) {
			return "/staff/staffMainPage/mainPageEX.jsp";
		} else {
			errorMsgs.add("登入失敗!!!");
			return "/staff/staffLogin/staffLoginPage.jsp";
		}
	}

	private String getChangePW(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		HttpSession session = req.getSession();
		Integer staffId = (Integer) session.getAttribute("staffId");
		req.setAttribute("staffId", staffId);

		Staff staff = staffService.getStaffByStaffId(staffId);

		String pastPW = req.getParameter("pastPassword");
		if (pastPW == null || pastPW.trim().length() == 0 || !pastPW.equals(staff.getPassword())) {
			errorMsgs.add("請輸入舊密碼!!");
		}

		String newPWReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{1,10}$";
		String newPW = req.getParameter("newPassword");
		if (newPW == null || newPW.trim().length() == 0) {
			errorMsgs.add("請輸入新密碼!!");
		} else if (newPW.equals(pastPW)) {
			errorMsgs.add("密碼不得與舊密碼相同!!");
		} else if (!newPW.trim().matches(newPWReg)) {
			errorMsgs.add("密碼請輸入英文大小寫及數字，不得超過10字!!");
		}

		if (!errorMsgs.isEmpty()) {
			return "/staff/staffLogin/staffChangePW.jsp";
		} else {
			staff.setPassword(newPW);
			Staff changePW = staffService.updateStaff(staff);
			req.setAttribute("staff", changePW);
			return "/staff/staffLogin/staffLoginPage.jsp";
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
