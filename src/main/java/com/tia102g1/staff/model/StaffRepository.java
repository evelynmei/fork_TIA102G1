package com.tia102g1.staff.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StaffRepository extends JpaRepository<StaffVO, Integer> {
	
	@Query(value = "from StaffVO where employDt = ?1 and status = ?2 order by staffId")
	List<StaffVO> findByOthers(Date employDt, int status);
}
