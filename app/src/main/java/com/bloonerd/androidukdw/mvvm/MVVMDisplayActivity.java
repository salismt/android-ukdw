package com.bloonerd.androidukdw.mvvm;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bloonerd.androidukdw.R;
import com.bloonerd.androidukdw.UserPreference;
import com.bloonerd.androidukdw.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MVVMDisplayActivity extends AppCompatActivity {

    private static final String TAG = "CommonDisplayActivity";
    private ImageView profile;
    private TextView name;
    private ImageView imgEmail;
    private TextView textEmail;
    private ImageView imgGender;
    private TextView textGender;
    private TextView textDob;
    private Button getUser;
    private UserPreference userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmdisplay);
        userPreference = UserPreference.getInstance(this);
        initView();
        setUser();
    }

    private void initView() {
        profile = (ImageView) findViewById(R.id.img_profile);
        name = (TextView) findViewById(R.id.text_name);
        imgEmail = (ImageView) findViewById(R.id.img_email);
        textEmail = (TextView)  findViewById(R.id.text_email);
        imgGender = (ImageView) findViewById(R.id.img_gender);
        textGender = (TextView) findViewById(R.id.text_gender);
        textDob = (TextView) findViewById(R.id.text_dob);
        getUser = (Button) findViewById(R.id.btn_get_user);
    }

    private void setUser() {
        final Context context = this;
        getUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = userPreference.getUser();
                MVVMDisplayViewModel viewModel = new MVVMDisplayViewModel(context, user);

                profile.setImageDrawable(viewModel.getProfile());
                name.setText(viewModel.getName());

                imgEmail.setImageDrawable(viewModel.getEmailIcon());
                textEmail.setText(viewModel.getEmail());

                imgGender.setImageDrawable(viewModel.getGenderIcon());
                textGender.setText(user.gender);

                textDob.setText(user.dob);

            }
        });

    }
}
