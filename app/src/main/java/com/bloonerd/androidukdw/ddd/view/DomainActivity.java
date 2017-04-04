package com.bloonerd.androidukdw.ddd.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bloonerd.androidukdw.R;
import com.bloonerd.androidukdw.ddd.domain.contract.UserService;
import com.bloonerd.androidukdw.ddd.services.UserServices;

public class DomainActivity extends AppCompatActivity {

    UserService userService;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);

        final EditText usernameEditText = (EditText) findViewById(R.id.et_username);
        final EditText passwordEditText = (EditText) findViewById(R.id.et_password);

        final Button button = (Button) findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                // Inisialisasi user services
                userService = new UserServices();

                // Masukkan variable username dan password ke dalam android bundle object
                final Bundle bundle = userService.create(username, password);

                // Panggil intent untuk ke activity berikutnya dengan membawa bundle object
                final Intent intent = new Intent(DomainActivity.this, DomainResultActivity.class);
                intent.putExtra("USER_BUNDLE", bundle);
                startActivity(intent);
            }
        });
    }
}