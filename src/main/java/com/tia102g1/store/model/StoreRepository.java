package com.tia102g1.store.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StoreRepository extends JpaRepository<StoreVO, Integer> {
	@Transactional
	@Modifying
	@Query(value = "delete from store where storeId =?1", nativeQuery = true)
	void deleteByStoreId(int storeId);
}
