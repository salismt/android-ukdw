package com.bloonerd.androidukdw.ddd.services;

import android.os.Bundle;

import com.bloonerd.androidukdw.ddd.domain.contract.UserRepository;
import com.bloonerd.androidukdw.ddd.domain.contract.UserService;
import com.bloonerd.androidukdw.ddd.domain.model.User;
import com.bloonerd.androidukdw.ddd.repository.UserRepositories;

public class UserServices implements UserService {

    UserRepository userRepository;

    @Override
    public Bundle create(final String username, final String password) {
        userRepository = new UserRepositories();
        return userRepository.setUser(username, password);
    }

    @Override
    public User load(final Bundle bundle) {
        userRepository = new UserRepositories();
        return userRepository.getUser(bundle);
    }
}