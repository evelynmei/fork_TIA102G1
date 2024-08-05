package com.tia102g1.news.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<NewsVO, Integer>{
	
	@Query(value = "from NewsVO where newsDate = ?1 and startDt = ?2 and endDt = ?3 and newsContent = ?4 order by newsId")
	List<NewsVO> findByothers(Date newsDate, Date startDt, Date endDt, String newsContent);
}
