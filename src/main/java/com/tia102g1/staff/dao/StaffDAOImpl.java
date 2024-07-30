package com.tia102g1.staff.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g1.staff.entity.Staff;

import util.HibernateUtil;




public class StaffDAOImpl implements StaffDAO{
	
	private SessionFactory factory;
	
	public StaffDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Staff staff) {
		return (Integer) getSession().save(staff);
	}

	@Override
	public int update(Staff staff) {
		try {
			getSession().update(staff);
			return 1;
		} catch(Exception e) {
			return -1;
		}
	}

	@Override
	public Staff getById(Integer staffId) {
		
		return getSession().get(Staff.class, staffId);
	}

	@Override
	public List<Staff> getAll() {
		return getSession().createQuery("from Staff", Staff.class).list();
	}
	
	@Override
	public List<Staff> getByCompositeQuery(Map<String, String> map) {
		if(map.size() == 0)
			return getAll();
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Staff> criteria = builder.createQuery(Staff.class);
		Root<Staff> root = criteria.from(Staff.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(map.containsKey("startemploydate") && map.containsKey("endemploydate"))
			predicates.add(builder.between(root.get("employDt"), Date.valueOf(map.get("startemploydate")), Date.valueOf(map.get("endemploydate"))));
		
		for(Map.Entry<String, String> row : map.entrySet()) {
//			if("staffId".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("staffId"), row.getValue()));
//			}
			
			if("startemploydate".equals(row.getKey())) {
				if(!map.containsKey("endemploydate"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("employDt"), Date.valueOf(row.getValue())));
			}
			
			if("endemploydate".equals(row.getKey())){
				if(!map.containsKey("startemploydate"))
					predicates.add(builder.lessThanOrEqualTo(root.get("employDt"), Date.valueOf(row.getValue())));
			}
			
			if("status".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("status"), row.getValue()));
			}
		}
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("staffId")));
		TypedQuery<Staff> query = getSession().createQuery(criteria);
		
		return query.getResultList();
	}

	@Override
	public List<Staff> getAll(int currentPage) {
		int pageMax = 5;
		int first = (currentPage - 1) * pageMax;
		return getSession().createQuery("from Staff", Staff.class).setFirstResult(first).setMaxResults(pageMax).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Staff", Long.class).uniqueResult();
	}	
	
	
}
