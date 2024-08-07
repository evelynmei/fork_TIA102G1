/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.tia102g1.productinfo.model;

//import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 開始 取代原 org.hibernate.Criteria 介面
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g1.productinfo.entity.ProductInfo;
import com.tia102g1.producttype.model.ProductTypeVO;

public class CompositeQuery_ProductInfo {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<ProductInfo> root, String columnName,
			String value) {

		Predicate predicate = null;

		try {
			if ("productId".equals(columnName)) // 用於Integer
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
			else if ("proStatus".equals(columnName)) // 用於Integer
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
			else if ("proPrice".equals(columnName)) // 用於Integer
				predicate = builder.equal(root.get(columnName), Integer.valueOf(value));

			else if ("priceMin".equals(columnName)) {
				predicate = builder.greaterThanOrEqualTo(root.get("proPrice"), Integer.valueOf(value));
			} else if ("priceMax".equals(columnName)) {
				predicate = builder.lessThanOrEqualTo(root.get("proPrice"), Integer.valueOf(value));
			} else if ("priceMin".equals(columnName) && "priceMax".equals(columnName)) {
				String[] values = value.split(",");
				if (values.length == 2) {
					predicate = builder.between(root.get("proPrice"), Integer.valueOf(values[0]),
							Integer.valueOf(values[1]));
				}
			} else if ("productTypeId".equals(columnName)) {
				ProductTypeVO productTypeVO = new ProductTypeVO();
				productTypeVO.setProductTypeId(Integer.valueOf(value));
				predicate = builder.equal(root.get("productTypeVO"), productTypeVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<ProductInfo> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<ProductInfo> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<ProductInfo> criteriaQuery = builder.createQuery(ProductInfo.class);
			// 【●創建 Root】
			Root<ProductInfo> root = criteriaQuery.from(ProductInfo.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			

			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				System.out.println("key內容 = " + value);
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
	                count++;
	                Predicate predicate = get_aPredicate_For_AnyDB(builder, root, key, value.trim());
	                if (predicate != null) {
	                    predicateList.add(predicate);
	                } else {
	                    System.out.println("Predicate 為 null, key: " + key + ", value: " + value);
	                }
	                System.out.println("有送出查詢資料的欄位數count = " + count);
	            }
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("productId")));
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
