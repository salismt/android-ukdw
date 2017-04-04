package com.bloonerd.androidukdw.ddd.repository;

import android.os.Bundle;

import com.bloonerd.androidukdw.ddd.domain.contract.UserRepository;
import com.bloonerd.androidukdw.ddd.domain.model.User;

public class UserRepositories implements UserRepository {

    @Override
    public Bundle setUser(final String username, final String password, final String email) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        final Bundle bundle = new Bundle();
        bundle.putParcelable("USER", user);

        return bundle;
    }

    @Override
    public User getUser(final Bundle bundle) {
        return bundle.getParcelable("USER");
    }
}