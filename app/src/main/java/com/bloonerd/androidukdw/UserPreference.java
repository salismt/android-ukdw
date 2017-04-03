package com.bloonerd.androidukdw;

import android.content.Context;
import android.content.SharedPreferences;

import com.bloonerd.androidukdw.model.User;
import com.google.gson.Gson;

public class UserPreference {

    public static final String USER_PREF = "UserPreference";
    private static final String USER_KEY = "user";
    private static UserPreference userPreference;
    private SharedPreferences sharedPreferences;

    private UserPreference(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static UserPreference getInstance(Context context) {
        if (userPreference == null)
            userPreference = new UserPreference(context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE));
        return userPreference;
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public String setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
        return key;
    }

    public void saveUser(User user) {
        setString(USER_KEY, new Gson().toJson(user));
    }

    public User getUser() {
        return new Gson().fromJson(getString(USER_KEY, null), User.class);
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

}
