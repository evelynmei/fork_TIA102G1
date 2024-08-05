package com.tia102g1.membercoin.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface MemberCoinRepository extends JpaRepository<MemberCoinVO, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "delete from memberCoin where memCoinId =?1", nativeQuery = true)
	void deleteByMemCoinId(int memCoinId);
}
