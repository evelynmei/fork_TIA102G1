package com.tia102g1.fav_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FavProductController {

    @Autowired
    FavProductService favProductService;

    /**
     * todo 前端請求時須給預設值欄位(favProductId, joinDt)
     * 新增最愛商品
     * @param favPrd
     * @return
     */
    @PostMapping("favProduct/{memberId}")
    public ResponseEntity<FavProduct> addFavPrd(@RequestBody FavProduct favPrd) {
        FavProduct newFavPrd = favProductService.addFavPrd(favPrd);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFavPrd);
    }

    /**
     * 刪除最愛商品
     * @param favProductId
     * @return
     */
    @DeleteMapping("favProduct/{favProductId}")
    public ResponseEntity<?> deleteFavPrd(@PathVariable Integer favProductId) {
        favProductService.deleteFavPrd(favProductId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * todo 從memberId取得最愛商品清單
     * @return
     */
    @GetMapping("favProduct/{memberId}")
    public ResponseEntity<?> getMyFavPrds (@PathVariable Integer memberId) {
        List<FavProduct> myFavPrds = favProductService.getMyFavPrds();
        return ResponseEntity.status(HttpStatus.OK).body(myFavPrds);
    }

}
