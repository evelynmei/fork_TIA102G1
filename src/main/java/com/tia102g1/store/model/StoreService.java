package com.tia102g1.store.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g1.store.CompositeQuery_Store;

@Service("storeService")
public class StoreService {
	@Autowired
	StoreRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addStore(StoreVO storeVO) {
		repository.save(storeVO);
	}
	
	//修改
	public void updateStore(StoreVO storeVO) {
		repository.save(storeVO);
	}
	
	//刪除
	public void deleteStore(Integer storeId) {
		if(repository.existsById(storeId)) {
			repository.deleteByStoreId(storeId);
		}	
	}
	
	//單筆查詢
	public StoreVO getOneStore(Integer storeId) {
		Optional<StoreVO> optional = repository.findById(storeId);
		return optional.orElse(null); //如果有查到資料就回傳VO物件,否則就回傳null
	}
	
	//全部查詢
	public List<StoreVO> getAll(){
		return repository.findAll();
	}
	
	//複合查詢
	public List<StoreVO> getAll(Map<String, String[]> map){
		return CompositeQuery_Store.getAllC(map, sessionFactory.openSession());
	}
	
}
