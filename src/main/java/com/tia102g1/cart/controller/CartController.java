package com.tia102g1.cart.controller;

import com.tia102g1.cart.CartPrd;
import com.tia102g1.cart.CartPrdRepository;
import com.tia102g1.cart.model.Cart;
import com.tia102g1.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartPrdRepository cartPrdRepo;

    /**
     * 取得購物車全部內容
     * @param memberId
     * @param model
     * @return api
     */
    @GetMapping("api/cart/{memberId}")
    public ResponseEntity<?> getAllItems(@PathVariable Integer memberId, Model model) {
        List<Cart> cartList = cartService.getAllItems(memberId);
        model.addAttribute("cartList", cartList);

        List<CartPrd> cartPrdList = cartPrdRepo.findAll();
        for (CartPrd cartPrd : cartPrdList) {
            cartPrd.setPrdPic(null);
        }

        model.addAttribute("cartPrdList", cartPrdList);

        System.out.println("=========================================");

        Map<String, List<?>> map = new HashMap<>();
        map.put("cartList", cartList);
        map.put("cartPrdList", cartPrdList);

        System.out.println("=========================================== map" + map);

        if (cartList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 新增商品至購物車
     * @param cart 新增的商品項目
     * @return api
     * 購物車內如果存在相同會員和商品的購物車項目，則更新數量，否則新增購物車項目
     */
    @PostMapping("api/cart/{memberId}")
    public ResponseEntity<?> addItem(@RequestBody Cart cart) {
        Integer memberId = cart.getMemberId();
        Integer productId = cart.getProductId();

        // 檢查是否已存在相同會員和商品的購物車項目
        List<Cart> cartList = cartService.getAllItems(memberId);

        for (Cart item : cartList) {
            // 如果存在，更新數量; *需用equals()比較物件內容
            if (Objects.equals(item.getProductId(), productId)) {

                item.setProAmount(item.getProAmount() + cart.getProAmount());
                Cart updatedCart = cartService.updateCart(item.getCartId(), item);
                System.out.println(item.toString() + cart.toString());
                return ResponseEntity.status(HttpStatus.OK).body(updatedCart);
            }
        }
        // 如果不存在，新增項目
        Cart newCart = cartService.addItem(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
    }

    /**
     * 更新商品數量
     * @param cartId
     * @param proAmount
     * @return api
     * 前端用put請求時, 傳入cartId以及要更新的數量
     * 後端更新資料庫後回傳api
     */
    @PutMapping("api/cart/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer cartId, @RequestBody Integer proAmount, Model model) {
        System.out.println("====================購物車更新===================");

        //從前端取得的memberId
        Integer memberId = (Integer) model.getAttribute("memberId");

        //前端按下去買單後, 傳入cartId以及更新後的數量
        Cart checkOutCart = new Cart();
        checkOutCart.setProAmount(proAmount);
        checkOutCart.setCartId(cartId);

        //檢查購物車內品項
        Cart item = cartService.getCartByPK(cartId, memberId);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //更新數量
        Cart updateItem = cartService.updateCart(cartId, checkOutCart);
        System.out.println("====================更新成功====================" + proAmount);
        return ResponseEntity.status(HttpStatus.OK).body(updateItem);

    }

    /**
     * 刪除商品
     * @param cartId
     * @return api
     */
    @DeleteMapping("api/cart/{cartId}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer cartId) {
        cartService.deleteItem(cartId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 買單商品
     * @param obj: 從前端拿到的資料，結構為 obj= {"buyItemList"= [
     *           {productId=1001, quantity=2},{productId=1002, quantity=3} ]}。
     * @return: 將資料存進 model 中的 buyItemList 屬性，然後重導到結帳頁面
     */
//    @ResponseBody
//    @PostMapping("api/checkout")  // 暫定 url
//    public String checkout(@RequestBody Object obj, Model model) {
//        model.addAttribute("buyItemList", obj);
//        System.out.println(obj);
//        String message;
//
//        if (obj == null) {
//            message = "請選擇要購買的商品";
//        } else {
//            message = "購買成功";
//        }
//
//        return message;
//    }

}
