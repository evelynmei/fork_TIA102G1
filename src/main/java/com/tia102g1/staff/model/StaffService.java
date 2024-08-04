package com.tia102g1.staff.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g1.staff.CompositeQuery_Staff;

@Service("staffService")
public class StaffService {
	
	@Autowired
	StaffRepository repo;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addStaff(StaffVO staffVO) {
		repo.save(staffVO);
	}
	
	public void updateStaff(StaffVO staffVO) {
		repo.save(staffVO);
	}
	
	public StaffVO getOneStaff(Integer staffId) {
		Optional<StaffVO> optional = repo.findById(staffId);
		return optional.orElse(null);
	}
	
	public List<StaffVO> getAll(){
		return repo.findAll();
	}
	
	public List<StaffVO> getAll(Map<String, String[]> map){
		return CompositeQuery_Staff.getAllC(map, sessionFactory.openSession());
	}

}
