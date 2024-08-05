package com.tia102g1.member.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService {
	@Autowired
	MemberRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addMember(Member memberVO) {
		repository.save(memberVO);
	}
	
	//修改
	public void updateMember(Member memberVO) {
		repository.save(memberVO);
	}
	
	//刪除
//	public void deleteMember(Integer memberId) {
//		if(repository.existsById(memberId)) {
//			repository.deleteByMemCoinId(memberId);
//		}	
//	}
	
	//單筆查詢
	public Member getOneMember(Integer memberId) {
		Optional<Member> optional = repository.findById(memberId);
		return optional.orElse(null); //如果有查到資料就回傳VO物件,否則就回傳null
	}
	
	//全部查詢
	public List<Member> getAll(){
		return repository.findAll();
	}
	
	//複合查詢
//	public List<Member> getAll(Map<String, String[]> map){
//		return CompositeQuery_Member.getAllC(map, sessionFactory.openSession());
//	}
}
