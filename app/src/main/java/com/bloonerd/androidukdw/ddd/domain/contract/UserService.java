package com.bloonerd.androidukdw.ddd.domain.contract;

import android.os.Bundle;

import com.bloonerd.androidukdw.ddd.domain.model.User;

public interface UserService {

    Bundle create(String username, String password, String email);

    User load(Bundle bundle);
}