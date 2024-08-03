package com.tia102g1.event.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends JpaRepository<EventVO, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "delete from event where eventId =?1", nativeQuery = true)
	void deleteByEventId(int eventId);
	
	
	@Query(value = "from EventVO where eventId=?1 and eventName like?2 and startDt=?3 order by eventId")
	List<EventVO> findByOthers(int eventId, String eventName, java.sql.Date startDt);
	
}
