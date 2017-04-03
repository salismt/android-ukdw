package com.bloonerd.androidukdw.ddd.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bloonerd.androidukdw.R;
import com.bloonerd.androidukdw.ddd.domain.contract.UserService;
import com.bloonerd.androidukdw.ddd.domain.model.User;
import com.bloonerd.androidukdw.ddd.services.UserServices;

public class DomainActivity extends AppCompatActivity {

    UserService userService;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        userService = new UserServices();

        final String username = "Budi Oktaviyan Suryanto";
        final String password = "12345678";
        final Bundle bundle = userService.create(username, password);

        final User user = userService.load(bundle);
        final String loadUsername = user.getUsername();
        final String loadPassword = user.getPassword();

        final String message = String.format("Username: %s, Password: %s", loadUsername, loadPassword);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        final TextView viewUsername = (TextView) findViewById(R.id.tv_username);
        final TextView viewPassword = (TextView) findViewById(R.id.tv_password);
        viewUsername.setText(loadUsername);
        viewPassword.setText(loadPassword);
    }
}