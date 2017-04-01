package com.bloonerd.androidukdw;

import com.bloonerd.androidukdw.model.SKUItem;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    public List<SKUItem> skuItems;
    public double totalPrice;

    public CartService() {
        skuItems = new ArrayList<>();
    }

    public void AddItemToCart(SKUItem item) {
        skuItems.add(item);
        updatePrice();
    }

    public void updatePrice() {
        totalPrice = 0;
        for (SKUItem sku : skuItems) {
            totalPrice += sku.price * sku.quantity;
        }
    }
}
