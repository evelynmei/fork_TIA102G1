package com.staff.dao;

import java.util.List;
import java.util.Map;

import com.staff.entity.Staff;

public interface StaffDAO {
	
	int insert(Staff staff);
	int update(Staff staff);
	Staff getById(Integer staffId);
	List<Staff> getAll();
	List<Staff> getAll(int currentPage);
	List<Staff> getByCompositeQuery(Map<String, String> map);
	long getTotal();
	
}
