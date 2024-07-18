package com.tia102g1.staff.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.tia102g1.staff.dao.StaffDAO;
import com.tia102g1.staff.dao.StaffDAOImpl;
import com.tia102g1.staff.entity.Staff;

import util.HibernateUtil;

public class StaffServiceImpl implements StaffService{
	
	private StaffDAO dao;
	
	public StaffServiceImpl() {
		dao = new StaffDAOImpl();
	}

	@Override
	public Staff addStaff(Staff staff) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
            dao.insert(staff);
            session.getTransaction().commit();
            return staff;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public Staff updateStaff(Staff staff) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			dao.update(staff);
			session.getTransaction().commit();
			return staff;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Staff getStaffByStaffId(Integer staffId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Staff staff = dao.getById(staffId);
			session.getTransaction().commit();
			return staff;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public List<Staff> getAllStaff(int currentPage) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Staff> list = dao.getAll(currentPage);
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}
	}
	
	

	@Override
	public List<Staff> getAllStaff() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Staff> list = dao.getAll();
			session.getTransaction().commit();
			return list;
		}catch(Exception e) {
			session.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public List<Staff> getStaffByCompositeQuery(Map<String, String[]> map) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for(Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			
			if("action".equals(key)) {
				continue;
			}
			
			String value = row.getValue()[0];
			if(value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);
		
	try {
		session.beginTransaction();
		List<Staff> list = dao.getByCompositeQuery(query);
		session.getTransaction().commit();
		return list;
	}catch (Exception e) {
		session.getTransaction().rollback();
		e.printStackTrace();
		return null;
	}
	}

	@Override
	public int getPageTotal() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int pageQty;
		int pageMax = 5;
		try {
			session.beginTransaction();
			long total = dao.getTotal();
			pageQty = (int)(total % pageMax == 0 ? (total / pageMax) : (total / pageMax + 1));
			session.getTransaction().commit();
		}catch (Exception e) {
			pageQty = 1;
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return pageQty;
	}
}
