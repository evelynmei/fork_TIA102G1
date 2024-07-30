package com.tia102g1.event.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EventService {

	private EventDAO_interface dao;

	public EventService() {
		dao = new EventDAO();
	}

	// 新增
	public EventVO addEvent(String eventName, Date startDt, Date endDt, Double eventDiscount, byte[] eventPic, Integer status,
			String eventContent, String createdBy, String lastUpdatedBy) {

		EventVO eventVO = new EventVO();
		eventVO.setEventName(eventName);
		eventVO.setStartDt(startDt);
		eventVO.setEndDt(endDt);
		eventVO.setEventDiscount(eventDiscount);
		eventVO.setEventPic(eventPic);
		eventVO.setStatus(status);
		eventVO.setEventContent(eventContent);
		eventVO.setCreatedBy(createdBy);
		eventVO.setLastUpdatedBy(lastUpdatedBy);
		dao.insert(eventVO);

		return eventVO;
	}

	// 修改
	public EventVO updateEvent(Integer eventId, String eventName, Date startDt, Date endDt, Double eventDiscount,
			byte[] eventPic, Integer status, String eventContent, String lastUpdatedBy) {

		EventVO eventVO = new EventVO();
		eventVO.setEventId(eventId);
		eventVO.setEventName(eventName);
		eventVO.setStartDt(startDt);
		eventVO.setEndDt(endDt);
		eventVO.setEventDiscount(eventDiscount);
		eventVO.setEventPic(eventPic);
		eventVO.setStatus(status);
		eventVO.setEventContent(eventContent);
		eventVO.setLastUpdatedBy(lastUpdatedBy);
		dao.update(eventVO);
		EventVO eventVONew = dao.findByPrimaryKey(eventId);

		return eventVONew;
	}

	// 刪除
	public void deleteEvent(Integer eventId) {
		dao.delete(eventId);
	}

	// 單筆查詢
	public EventVO getOneEvent(Integer eventId) {
		return dao.findByPrimaryKey(eventId);
	}

	// 多筆查詢
	public List<EventVO> getAll() {
		return dao.getAll();
	}

}
