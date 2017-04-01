package com.bloonerd.androidukdw.model;

import java.util.UUID;

public class SKUItem {

    public int id;
    public String name;
    public String image;
    public int quantity;
    public double price;
    public String category;
    public boolean isEdible;
    public int itemLeft;

    public SKUItem(String name, String image, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public SKUItem(int id, String name, String image, double price, String category, boolean isEdible, int itemLeft) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
        this.isEdible = isEdible;
        this.itemLeft = itemLeft;
    }
}
