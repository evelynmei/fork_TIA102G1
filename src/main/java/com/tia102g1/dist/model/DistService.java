package com.tia102g1.dist.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("distService")
public class DistService {
	
	@Autowired
	DistRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	// 單筆查詢
	public DistVO getOneDist(Integer distCode) {
		Optional<DistVO> optional = repository.findById(distCode);
		return optional.orElse(null); // 如果有查到資料就回傳VO物件,否則就回傳null
	}

	// 全部查詢
	public List<DistVO> getAll() {
		return repository.findAll();
	}

	// 複合查詢
//	public List<DistVO> getAll(Map<String, String[]> map){
//		return CompositeQuery_Dist.getAllC(map, sessionFactory.openSession());
//	}
}
