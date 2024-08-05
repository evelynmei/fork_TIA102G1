package com.tia102g1.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g1.news.model.NewsVO;
import com.tia102g1.staff.model.StaffVO;

public class CompositeQuery_News {
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<NewsVO> root, String columnName, String value) {
		Predicate predicate = null;
		//查詢公告內容
		if("newsContent".equals(columnName))
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		//查詢公告發布日期
		else if("startPublish".equals(columnName))
			predicate = builder.greaterThanOrEqualTo(root.get("newsDate"), java.sql.Date.valueOf(value));
		else if("endPublish".equals(columnName))
			predicate = builder.lessThanOrEqualTo(root.get("newsDate"), java.sql.Date.valueOf(value));
		else if("startPublish" != null && "endPublish" != null)
			predicate = builder.between(root.get("newsDate"), java.sql.Date.valueOf(value), java.sql.Date.valueOf(value));
		//查詢公告起始時間
		else if("beginStartDt".equals(columnName))
			predicate = builder.greaterThanOrEqualTo(root.get("startDt"), java.sql.Date.valueOf(value));
		else if("endStartDt".equals(columnName))
			predicate = builder.lessThanOrEqualTo(root.get("startDt"), java.sql.Date.valueOf(value));
		else if("beginStartDt" != null && "endStartDt" != null)
			predicate = builder.between(root.get("startDt"), java.sql.Date.valueOf(value), java.sql.Date.valueOf(value));
		//查詢公告結束時間
		else if("beginEndDt".equals(columnName))
			predicate = builder.greaterThanOrEqualTo(root.get("endDt"), java.sql.Date.valueOf(value));
		else if("endEndDt".equals(columnName))
			predicate = builder.lessThanOrEqualTo(root.get("endDt"), java.sql.Date.valueOf(value));
		else if("beginEndDt" != null && "endEndDt" != null)
			predicate = builder.between(root.get("endDt"), java.sql.Date.valueOf(value), java.sql.Date.valueOf(value));
		
		else if("newsNow".equals(columnName)) {
			Predicate afterStartDt = builder.greaterThanOrEqualTo(root.get("startDt"), java.sql.Date.valueOf(value));
			Predicate beforeEndDt = builder.lessThanOrEqualTo(root.get("endDt"), java.sql.Date.valueOf(value));
			predicate = builder.and(afterStartDt, beforeEndDt);
		}
		return predicate;
	}
	
	@SuppressWarnings("unchecked")
	public static List<NewsVO> getALLC(Map<String, String[]> map, Session session){
		
		Transaction tx = session.beginTransaction();
		List<NewsVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<NewsVO> criteriaQuery = builder.createQuery(NewsVO.class);
			// 【●創建 Root】
			Root<NewsVO> root = criteriaQuery.from(NewsVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			Set<String> keys = map.keySet();
			
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("newsId")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); // javax.persistence.Query; //Hibernate 5 開始 取代原
																// org.hibernate.Query 介面
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;
	}
}
