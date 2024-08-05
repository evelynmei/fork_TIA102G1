package com.tia102g1.productInfo.dao;

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

import com.tia102g1.productInfo.dao.ProductInfoDAO;
import com.tia102g1.productInfo.dao.ProductInfoDAOImpl;
import com.tia102g1.productInfo.entity.ProductInfo;

import util.HibernateUtil;


public class ProductInfoDAOImpl implements ProductInfoDAO {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ProductInfoDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ProductInfo productInfo) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(productInfo);
	}

	@Override
	public int update(ProductInfo productInfo) {
		try {
			getSession().update(productInfo);
			return 1; // 修改成功
		} catch (Exception e) {
			return -1; // 修改失敗
		}

	}

	@Override
	public int updateProStatus(Integer productId, Integer proStatus) {
		try {
			((ProductInfoDAOImpl) getSession()).updateProStatus(productId, proStatus);
			return 1; // 修改成功
		} catch (Exception e) {
			return -1; // 修改失敗
		}
	}

	@Override
	public ProductInfo getByProductId(Integer productId) {
		return getSession().get(ProductInfo.class, productId);
	}

	@Override
	public List<ProductInfo> getAll() {
		return getSession().createQuery("from ProductInfo", ProductInfo.class).list();
	}

	@Override
	public List<ProductInfo> getAll(int currentPage) {
		int pageMax = 5;
		int first = (currentPage - 1) * pageMax;
		return getSession().createQuery("from ProductInfo", ProductInfo.class).setFirstResult(first)
				.setMaxResults(pageMax).list();
	}

	@Override
	public List<ProductInfo> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}

		// 先從session取得criteria builder工具
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// builder建立Query
		CriteriaQuery<ProductInfo> criteria = builder.createQuery(ProductInfo.class);
		// 用from指定要用哪個table
		Root<ProductInfo> root = criteria.from(ProductInfo.class);
		// 把所有where後面的條件集合起來放進<Predicate>，然後再用list接著、建立一個新的array
		List<Predicate> predicates = new ArrayList<>();

		// 加入查詢條件進Map
		for (Map.Entry<String, String> row : map.entrySet()) {
			// 商品編號
			if ("productId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("productId"), Integer.valueOf(row.getValue())));
			}
			// 商品類型
			if ("productTypeId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("productTypeId"), Integer.valueOf(row.getValue())));
			}
			// 商品狀態
			if ("proStatus".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("proStatus"), Byte.valueOf(row.getValue())));
			}
			// 價格範圍
			// 最低價格
			if ("priceMin".equals(row.getKey())) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("proPrice"), Double.valueOf(row.getValue())));
			}
			// 最高價格
			if ("priceMax".equals(row.getKey())) {
				predicates.add(builder.lessThanOrEqualTo(root.get("proPrice"), Double.valueOf(row.getValue())));
			}
		}

		// 把list轉成陣列
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		//查詢結果依照productId升冪排列
		criteria.orderBy(builder.asc(root.get("productId")));
		TypedQuery<ProductInfo> query = getSession().createQuery(criteria);
		return query.getResultList();

	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ProductInfo", Long.class).uniqueResult();
	}

}
