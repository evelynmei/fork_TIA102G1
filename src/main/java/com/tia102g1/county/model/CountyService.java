package com.tia102g1.county.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g1.county.model.CountyRepository;
import com.tia102g1.county.model.CountyVO;

@Service("countyService")
public class CountyService {
	
	@Autowired
	CountyRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
			
	//單筆查詢
	public CountyVO getOneCounty(Integer cntCode) {
		Optional<CountyVO> optional = repository.findById(cntCode);
		return optional.orElse(null); //如果有查到資料就回傳VO物件,否則就回傳nu	ll
	}
	
	//全部查詢
	public List<CountyVO> getAll(){
		return repository.findAll();
	}
	
	//複合查詢
//	public List<CountyVO> getAll(Map<String, String[]> map){
//		return CompositeQuery_County.getAllC(map, sessionFactory.openSession());
//	}

	//查詢所有縣市區
	public List<CountyVO> getAllCounties() {
		return repository.findAll();
	}
	
}
