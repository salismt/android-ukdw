package com.bloonerd.androidukdw.ddd.domain.contract;

import android.os.Bundle;

import com.bloonerd.androidukdw.ddd.domain.model.User;

public interface UserRepository {

    Bundle setUser(String username, String password);

    User getUser(Bundle bundle);
}