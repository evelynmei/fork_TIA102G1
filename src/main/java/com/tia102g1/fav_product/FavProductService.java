package com.tia102g1.fav_product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g1.productinfo.entity.ProductInfo;
import com.tia102g1.productinfo.model.ProductInfoServiceS;

@Service("favProductService")
public class FavProductService {

    @Autowired
    FavProductRepository repository;
    
    @Autowired
    ProductInfoServiceS prodInfoSvc;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 新增最愛商品
     *
     * @param favProduct
     * @return
     */
    public FavProduct addFavPrd(FavProduct favProduct) {
        repository.save(favProduct);
        return favProduct;
    }

    public void updateFavPrd(FavProduct favProduct) {
        repository.save(favProduct);
    }

    /**
     * 刪除最愛商品
     * @param favProductId
     */
    @Transactional
    public void removeFavorite(Integer memberId, Integer productId) {
        // 根據 memberId 和 productId 找到對應的最愛項目，然後移除

        repository.deleteByMember_MemberIdAndProductInfo_ProductId(memberId, productId);
    }

    /**
     * 查詢單項最愛商品
     * @param favProductId
     * @return
     */
    public FavProduct getFavPrd(Integer favProductId) {
        Optional<FavProduct> optional = repository.findById(favProductId);
        return optional.orElse(null);
    }
    
    public boolean isFavorite(Integer memberId, Integer productId) {
        return repository.existsByMember_MemberIdAndProductInfo_ProductId(memberId, productId);
    }


    /**
     * 查詢會員全部最愛商品
     * @return
     */
    public List<FavProduct> getMyFavPrds(Integer memberId) {

        return repository.findByMember_MemberId(memberId);//先假設會員ID為2
    }
    
    public List<ProductInfo> getFavProdInfo(Integer memberId) {
        System.out.println("Member ID: " + memberId);
        
        // 查詢該會員的最愛商品列表
        List<FavProduct> favProducts = repository.findByMember_MemberId(memberId);
        System.out.println("Fav Products Query Result: " + favProducts);
        
        if (favProducts == null) {
            System.out.println("Fav Products is null");
            return Collections.emptyList(); // 返回空列表而不是 null
        }
        
        // 創建一個列表來存儲對應的商品資訊
        List<ProductInfo> productInfos = new ArrayList<>();
        
        // 遍歷最愛商品，並提取每個商品的詳細資訊
        for (FavProduct favProduct : favProducts) {
            if (favProduct != null) {
                ProductInfo productInfo = favProduct.getProductInfo();
                System.out.println("Product Info: " + productInfo);
                productInfos.add(productInfo);
            } else {
                System.out.println("FavProduct is null");
            }
        }
        
        // 返回該會員最愛商品的詳細資訊列表
        return productInfos;
    }


}

