package com.bloonerd.androidukdw.ddd.domain.contract;

import android.os.Bundle;

import com.bloonerd.androidukdw.ddd.domain.model.User;

public interface UserRepository {

    Bundle setUser(String username, String password, String email);

    User getUser(Bundle bundle);
}