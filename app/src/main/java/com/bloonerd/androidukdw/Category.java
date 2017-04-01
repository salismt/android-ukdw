package com.bloonerd.androidukdw;

import android.support.annotation.IntDef;

public class Category {

    public String name;
    public int categoryType;

    public Category(String name, int categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }
}
