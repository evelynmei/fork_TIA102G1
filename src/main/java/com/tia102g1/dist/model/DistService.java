package com.tia102g1.dist.model;

import java.util.List;
import java.util.Optional;

import com.tia102g1.county.model.CountyRepository;
import com.tia102g1.county.model.CountyVO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("distService")
public class DistService {

    @Autowired
    DistRepository repository;
    @Autowired
    CountyRepository countyRepository;

    @Autowired
    private SessionFactory sessionFactory;

    // 單筆查詢
    public DistVO getOneDist(Integer distCode) {
        Optional<DistVO> optional = repository.findById(distCode);
        return optional.orElse(null); // 如果有查到資料就回傳VO物件,否則就回傳null
    }

    // 全部查詢
    public List<DistVO> getAll() {
        return repository.findAll();
    }

    // 複合查詢
//	public List<DistVO> getAll(Map<String, String[]> map){
//		return CompositeQuery_Dist.getAllC(map, sessionFactory.openSession());
//	}


    public List<DistVO> getDistrictsByCountyCode(Integer cntCode) {
        //先把country縣市區查出來
        CountyVO county = countyRepository.findById(cntCode).orElseThrow(()
                -> new RuntimeException("縣市代碼無效"));
        //再將country相對應的 鄉鎮市區找出來
        return repository.findByCountyVO_CntCode(cntCode);

    }
}
