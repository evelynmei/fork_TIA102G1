package com.tia102g1.event.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g1.event.CompositeQuery_Event;

@Service("eventService")
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//新增
	public void addEvent(EventVO eventVO) {
		repository.save(eventVO);
	}
	
	//修改
	public void updateEvent(EventVO eventVO) {
		repository.save(eventVO);
	}
	
	//刪除
	public void deleteEvent(Integer eventId) {
		if(repository.existsById(eventId)) {
			repository.deleteByEventId(eventId);
		}	
	}
	
	//單筆查詢
	public EventVO getOneEvent(Integer eventId) {
		Optional<EventVO> optional = repository.findById(eventId);
		return optional.orElse(null); //如果有查到資料就回傳VO物件,否則就回傳null
	}
	
	//全部查詢
	public List<EventVO> getAll(){
		return repository.findAll();
	}
	
	//複合查詢
	public List<EventVO> getAll(Map<String, String[]> map){
		return CompositeQuery_Event.getAllC(map, sessionFactory.openSession());
	}
	
	
}
