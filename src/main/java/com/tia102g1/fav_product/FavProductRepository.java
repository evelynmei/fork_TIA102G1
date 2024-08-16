package com.tia102g1.fav_product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavProductRepository extends JpaRepository<FavProduct, Integer> {

    /**
     * 從memberId查詢最愛商品清單
     * @return List<FavProduct>
     */
//    @Query(value = "SELECT * FROM favProduct WHERE memberId=:memberId", nativeQuery = true)
    List<FavProduct> findByMember_MemberId(Integer memberId);
    
    void deleteByMember_MemberIdAndProductInfo_ProductId(Integer memberId, Integer productId);

    boolean existsByMember_MemberIdAndProductInfo_ProductId(Integer memberId, Integer productId);

}
