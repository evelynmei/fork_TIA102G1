package com.tia102g1.dist.model;

import com.tia102g1.county.model.CountyVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistRepository extends JpaRepository<DistVO, Integer> {
    //查詢District中的鄉鎮區代碼
    List<DistVO> findByCountyVO_CntCode(Integer cntCode);

}
