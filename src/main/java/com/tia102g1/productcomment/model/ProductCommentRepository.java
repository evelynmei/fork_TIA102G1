package com.tia102g1.productcomment.model;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductCommentRepository extends JpaRepository<ProductCommentVO, Integer> {
	List<ProductCommentVO> findByMember_MemberId(Integer memberId);

	List<ProductCommentVO> findByProductInfo_ProductId(Integer productId);

	ProductCommentVO findProductCommentByProCommentId(Integer proCommentId);

	
	@Transactional
	@Modifying
	@Query("UPDATE ProductCommentVO SET storeReply = :storeReply , staffId = :staffId , replyTime = :replyTime, lastUpdatedBy = :lastUpdatedBy, lastUpdated = :lastUpdated WHERE proCommentId = :proCommentId")
	void updateStoreReplyByProCommentId(Integer proCommentId, String storeReply, Integer staffId,
			Timestamp replyTime, String lastUpdatedBy, Timestamp lastUpdated);
}
