package com.tia102g1.staff.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tia102g1.staff.dao.StaffDAO;
import com.tia102g1.staff.dao.StaffDAOImpl;
import com.tia102g1.staff.entity.Staff;


public class StaffServiceImpl implements StaffService {

	private StaffDAO dao;

	public StaffServiceImpl() {
		dao = new StaffDAOImpl();
	}

	@Override
	public Staff addStaff(Staff staff) {
		dao.insert(staff);
		return staff;
	}

	@Override
	public Staff updateStaff(Staff staff) {
		dao.update(staff);
		return staff;
	}

	@Override
	public Staff getStaffByStaffId(Integer staffId) {
		Staff staff = dao.getById(staffId);
		return staff;
	}

	@Override
	public List<Staff> getAllStaff(int currentPage) {
		List<Staff> list = dao.getAll(currentPage);
		return list;
	}

	@Override
	public List<Staff> getAllStaff() {
		List<Staff> list = dao.getAll();
		return list;
	}

	@Override
	public List<Staff> getStaffByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();

			if ("action".equals(key)) {
				continue;
			}

			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);

		return dao.getByCompositeQuery(query);
	}

	@Override
	public int getPageTotal() {
		int pageQty;
		int pageMax = 5;
		long total = dao.getTotal();
		pageQty = (int) (total % pageMax == 0 ? (total / pageMax) : (total / pageMax + 1));
		return pageQty;
	}
}
