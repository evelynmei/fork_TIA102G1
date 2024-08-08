package com.tia102g1.orderlist.model;

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

import com.tia102g1.member.model.Member;

public class CompositeQuery_OrderList {
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<OrderListVO> root, String columnName,
			String value) {

		Predicate predicate = null;
		try {
			if ("memberId".equals(columnName)) { // 依會員編號查詢
				Member member = new Member();
				member.setMemberId(Integer.valueOf(value));
				predicate = builder.equal(root.get("member"), member);
			} else if ("orderStatus".equals(columnName)) { // 依訂單狀態查詢
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
			} else if ("paymentStatus".equals(columnName)) { // 依付款狀態查詢
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
			} else if ("orderListId".equals(columnName)) { // 依訂單編號查詢
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
			} else if ("pickupMethod".equals(columnName)) { // 依取貨方式查詢
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
			} else if ("startorderDt".equals(columnName)) {// 依訂單日期查詢
				predicate = builder.greaterThanOrEqualTo(root.get("orderDt"), java.sql.Date.valueOf(value));
			} else if ("endorderDt".equals(columnName)) {
				predicate = builder.lessThanOrEqualTo(root.get("orderDt"), java.sql.Date.valueOf(value));
			} else if ("startorderDt" != null && "endorderDt" != null) {
				predicate = builder.between(root.get("orderDt"), java.sql.Date.valueOf(value),
						java.sql.Date.valueOf(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("predicate= " + predicate); //如果沒條件就會是null
		return predicate;

	}

	@SuppressWarnings("unchecked")
	public static List<OrderListVO> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<OrderListVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<OrderListVO> criteriaQuery = builder.createQuery(OrderListVO.class);
			// 【●創建 Root】
			Root<OrderListVO> root = criteriaQuery.from(OrderListVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				System.out.println("key = " + key + "; value = " + value); //印出查詢條件
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("orderListId")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); // javax.persistence.Query; //Hibernate 5 開始 取代原
																// org.hibernate.Query 介面
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
//			throw ex; // System.out.println(ex.getMessage());
			System.out.println(ex.getMessage()); //印出錯誤訊息
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}
		
		//印出查詢結果
		for(OrderListVO o : list) {
			System.out.println(o);
		}
		
		return list;
	}
}
