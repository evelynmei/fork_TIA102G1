package com.tia102g1.qutype.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("quTypeService")
public class QuTypeService {

		@Autowired
		QuTypeRepository repo;
		
		public void addQuType(QuTypeVO quTypeVO) {
			repo.save(quTypeVO);
		}
		
		public void updateQuType(QuTypeVO quTypeVO) {
			repo.save(quTypeVO);
		}
		
		public QuTypeVO getOneQuType(Integer quTypeId) {
			Optional<QuTypeVO> optional = repo.findById(quTypeId);
			return optional.orElse(null);
		}
		
		public List<QuTypeVO> getAll(){
			return repo.findAll();
		}
}
