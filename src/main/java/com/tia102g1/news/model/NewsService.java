package com.tia102g1.news.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g1.news.CompositeQuery_News;

@Service("NewsService")
public class NewsService {
	
	@Autowired
	NewsRepository repo;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addNews(NewsVO newsVO) {
		repo.save(newsVO);
	}
	
	public void updateNews(NewsVO newsVO) {
		repo.save(newsVO);
	}
	
	public NewsVO getOneNews(Integer newsId) {
		Optional<NewsVO> optional = repo.findById(newsId);
		return optional.orElse(null);
	}
	
	public List<NewsVO> getAll(){
		return repo.findAll();
	}
	
	public List<NewsVO> getAll(Map<String, String[]> map){
		return CompositeQuery_News.getALLC(map, sessionFactory.openSession());
	}
}
