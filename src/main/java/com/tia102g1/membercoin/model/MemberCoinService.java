package com.tia102g1.membercoin.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g1.membercoin.CompositeQuery_MemberCoin;
import com.tia102g1.membercoin.model.MemberCoinRepository;
import com.tia102g1.membercoin.model.MemberCoinVO;

@Service("memberCoinService")
public class MemberCoinService {
	@Autowired
	MemberCoinRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addMemberCoin(MemberCoinVO memberCoinVO) {
		repository.save(memberCoinVO);
	}
	
	//修改
	public void updateMemberCoin(MemberCoinVO memberCoinVO) {
		repository.save(memberCoinVO);
	}
	
	//刪除
	public void deleteMemberCoin(Integer memCoinId) {
		if(repository.existsById(memCoinId)) {
			repository.deleteByMemCoinId(memCoinId);
		}	
	}
	
	//單筆查詢
	public MemberCoinVO getOneMemberCoin(Integer memCoinId) {
		Optional<MemberCoinVO> optional = repository.findById(memCoinId);
		return optional.orElse(null); //如果有查到資料就回傳VO物件,否則就回傳null
	}
	
	//全部查詢
	public List<MemberCoinVO> getAll(){
		return repository.findAll();
	}
	
	//複合查詢
	public List<MemberCoinVO> getAll(Map<String, String[]> map){
		return CompositeQuery_MemberCoin.getAllC(map, sessionFactory.openSession());
	}
}
