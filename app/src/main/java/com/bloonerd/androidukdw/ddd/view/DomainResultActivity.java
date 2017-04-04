package com.bloonerd.androidukdw.ddd.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bloonerd.androidukdw.R;
import com.bloonerd.androidukdw.ddd.domain.contract.UserService;
import com.bloonerd.androidukdw.ddd.domain.model.User;
import com.bloonerd.androidukdw.ddd.services.UserServices;

public class DomainResultActivity extends AppCompatActivity {

    UserService userService;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_result);
        userService = new UserServices();

        // Load data yang di bawa dari activity sebelumnya melalui android bundle object
        final User user = userService.load(getIntent().getBundleExtra("USER_BUNDLE"));
        final String loadUsername = user.getUsername();
        final String loadPassword = user.getPassword();
        final String loadEmail = user.getEmail();

        final TextView viewUsername = (TextView) findViewById(R.id.tv_username);
        final TextView viewPassword = (TextView) findViewById(R.id.tv_password);
        final TextView viewEmail = (TextView) findViewById(R.id.tv_email);
        viewUsername.setText(loadUsername);
        viewPassword.setText(loadPassword);
        viewEmail.setText(loadEmail);
    }
}