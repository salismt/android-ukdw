package com.bloonerd.androidukdw.model;

import java.util.ArrayList;
import java.util.List;

public class CartHelper {

    public static List<SKUItem> getAvailableSKUItems() {
        List<SKUItem> skuItems = new ArrayList<>();
        skuItems.add(new SKUItem(1, "Doritos", "https://images-na.ssl-images-amazon.com/images/I/91wirhWS8nL._SX522_.jpg", 5000, "Snack", true, 8));
        skuItems.add(new SKUItem(2, "Aqua", "https://images-na.ssl-images-amazon.com/images/I/51NRIIZpwJL._SY679SX441_SY679_CR,0,0,441,679_PIbundle-24,TopRight,0,0_SX441_SY679_CR,0,0,441,679_SH20_.jpg", 2500, "Beverages", true, 13));
        skuItems.add(new SKUItem(3, "Rice Cooker", "https://www.amazon.com/gp/product/B007WQ9YNO/https://images-na.ssl-images-amazon.com/images/I/41juCzD8qWL._SL150_.jpg", 50000, "Appliances", false, 3));
        skuItems.add(new SKUItem(4, "Ketchup", "https://images-na.ssl-images-amazon.com/images/I/81W79j5rL1L._SY679_.jpg", 7000, "Condiment", true, 5));
        skuItems.add(new SKUItem(5, "Oreo", "https://images-na.ssl-images-amazon.com/images/I/51-m6y98A8L.jpg", 3000, "Snack", true, 6));
        skuItems.add(new SKUItem(6, "Cashew", "https://images-na.ssl-images-amazon.com/images/I/910AyQumGkL._SL1500_.jpg", 15000, "Snack", true, 5));
        skuItems.add(new SKUItem(7, "Soap", "https://images-na.ssl-images-amazon.com/images/I/61KMq35I0uL._SL1000_.jpg", 6500, "Utilities", false, 3));
        skuItems.add(new SKUItem(8, "Cereal", "https://images-na.ssl-images-amazon.com/images/I/91tqhqtCXUL._SL1500_.jpg", 30000, "Food", true, 2));
        return skuItems;
    }


}
