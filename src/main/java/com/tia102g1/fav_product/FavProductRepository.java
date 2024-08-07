package com.tia102g1.fav_product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavProductRepository extends JpaRepository<FavProduct, Integer> {

    /**
     * 從memberId查詢最愛商品清單
     * @return List<FavProduct>
     */
    @Query(value = "SELECT * FROM favProduct WHERE memberId=:memberId", nativeQuery = true)
    List<FavProduct> findByMemberId(Integer memberId);

}
