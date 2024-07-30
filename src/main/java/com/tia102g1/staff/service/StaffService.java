package com.tia102g1.staff.service;

import java.util.List;
import java.util.Map;

import com.tia102g1.staff.entity.Staff;

public interface StaffService {
	Staff addStaff(Staff staff);
	Staff updateStaff(Staff staff);
	Staff getStaffByStaffId(Integer staffId);
	List<Staff> getAllStaff(int currentPage);
	List<Staff> getAllStaff();
	int getPageTotal();
	List<Staff> getStaffByCompositeQuery(Map<String, String[]> map);

}
