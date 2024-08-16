package com.tia102g1.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartPrdController {
    @Autowired
    private CartPrdRepository cartPrdRepository;

    @GetMapping("api/cartPrd")
    public List<CartPrd> getAllPrd() {
        return cartPrdRepository.findAll();
    }

}
