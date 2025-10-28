package com.example.eazycart.services;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import com.example.eazycart.model.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final Map<String, Map<String, CartItem>> carts = new ConcurrentHashMap<>();

    public List<CartItem> getCart(String userId) {
        return new ArrayList<>(carts.getOrDefault(userId, new HashMap<>()).values());
    }

    public List<CartItem> addItem(String userId, CartItem item) {
        carts.putIfAbsent(userId, new ConcurrentHashMap<>());
        Map<String, CartItem> cart = carts.get(userId);

        CartItem existing = cart.get(item.getProductId());
        if (existing == null) {
            if (item.getQuantity() <= 0) item.setQuantity(1);
            cart.put(item.getProductId(), item);
        } else {
            existing.setQuantity(existing.getQuantity() + Math.max(1, item.getQuantity()));
        }
        return getCart(userId);
    }

    public List<CartItem> updateQuantity(String userId, String productId, int qty) {
        Map<String, CartItem> cart = carts.getOrDefault(userId, new HashMap<>());
        CartItem item = cart.get(productId);
        if (item != null) {
            if (qty <= 0) cart.remove(productId);
            else item.setQuantity(qty);
        }
        return getCart(userId);
    }

    public List<CartItem> removeItem(String userId, String productId) {
        Map<String, CartItem> cart = carts.getOrDefault(userId, new HashMap<>());
        cart.remove(productId);
        return getCart(userId);
    }

    public void clear(String userId) {
        carts.remove(userId);
    }

    public double total(String userId) {
        return getCart(userId).stream()
                .mapToDouble(ci -> ci.getPrice() * ci.getQuantity())
                .sum();
    }

    public int count(String userId) {
        return getCart(userId).stream().mapToInt(CartItem::getQuantity).sum();
    }
}
