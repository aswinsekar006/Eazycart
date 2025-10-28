package com.example.eazycart.controller;

import java.util.List;

import com.example.eazycart.model.CartItem;
import com.example.eazycart.services.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) { this.cartService = cartService; }

 
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getCart(userId);
    }


    @PostMapping("/{userId}/add")
    public List<CartItem> addItem(@PathVariable String userId, @RequestBody CartItem item) {
        return cartService.addItem(userId, item);
    }


    @PutMapping("/{userId}/update/{productId}")
    public List<CartItem> updateQuantity(@PathVariable String userId,
                                         @PathVariable String productId,
                                         @RequestParam int qty) {
        return cartService.updateQuantity(userId, productId, qty);
    }


    @DeleteMapping("/{userId}/remove/{productId}")
    public List<CartItem> removeItem(@PathVariable String userId, @PathVariable String productId) {
        return cartService.removeItem(userId, productId);
    }


    @DeleteMapping("/{userId}/clear")
    public void clear(@PathVariable String userId) {
        cartService.clear(userId);
    }

    @GetMapping("/{userId}/total")
    public double total(@PathVariable String userId) {
        return cartService.total(userId);
    }

    @GetMapping("/{userId}/count")
    public int count(@PathVariable String userId) {
        return cartService.count(userId);
    }
}
