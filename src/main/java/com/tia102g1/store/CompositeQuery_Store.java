package com.tia102g1.store;

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

import com.tia102g1.county.model.CountyVO;
import com.tia102g1.dist.model.DistVO;
import com.tia102g1.store.model.StoreVO;

public class CompositeQuery_Store {
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<StoreVO> root, String columnName,
			String value) {

		Predicate predicate = null;

		try {
			// 用於Integer
			if ("storeId".equals(columnName)) {
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value)); // root代表查詢的實體型別

				// 用於varchar
			} else if ("storeName".equals(columnName)) {
				predicate = builder.like(root.get(columnName), "%" + value + "%");

			} else if ("cntCode".equals(columnName)) {
				CountyVO countyVO = new CountyVO();
				countyVO.setCntCode(Integer.valueOf(value));
				predicate = builder.equal(root.get("countyVO"), countyVO);

			} else if ("distCode".equals(columnName)) {
				DistVO distVO = new DistVO();
				distVO.setDistCode(Integer.valueOf(value));
				predicate = builder.equal(root.get("distVO"), distVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<StoreVO> getAllC(Map<String, String[]> map, Session session) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<StoreVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<StoreVO> criteriaQuery = builder.createQuery(StoreVO.class);
			// 【●創建 Root】
			Root<StoreVO> root = criteriaQuery.from(StoreVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				System.out.println("key內容 = " + value);
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("storeId")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); // javax.persistence.Query; //Hibernate 5 開始 取代原
																// org.hibernate.Query 介面
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
//			throw ex; // System.out.println(ex.getMessage());
			System.out.println(ex.getMessage());
		} finally {
			session.close();

			// HibernateUtil.getSessionFactory().close();
		}

		return list;
	}
}
