package com.bloonerd.androidukdw;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        CategoryType.MVC,
        CategoryType.MVP,
        CategoryType.MVVM
})

public @interface CategoryType {
    int MVC = 0;
    int MVP = 1;
    int MVVM = 2;
}

