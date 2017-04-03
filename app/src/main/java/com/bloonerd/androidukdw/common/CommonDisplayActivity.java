package com.bloonerd.androidukdw.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
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

public class CommonDisplayActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_commondisplay);
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
                RoundedBitmapDrawable imgRound = RoundedBitmapDrawableFactory.create(getResources(), user.image);
                imgRound.setCircular(true);
                profile.setImageDrawable(imgRound);

                Date date = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy", Locale.getDefault());
                try {
                    date = dateFormat.parse(user.dob);
                } catch (ParseException e) {
                    Log.e(TAG, e.getMessage());
                }

                if (date != null) {
                    long diff = System.currentTimeMillis() - date.getTime();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(diff);
                    double year = calendar.get(Calendar.YEAR) - 1970;

                    if (year <= 17) {
                        name.setText("Dek " + user.firstName + " " + user.lastName);
                    } else {
                        name.setText(user.firstName + " " + user.lastName);
                    }
                }

                if (user.email.contains("gmail"))
                    imgEmail.setImageResource(R.drawable.ic_gmail);
                else if (user.email.contains("yahoo"))
                    imgEmail.setImageResource(R.drawable.ic_yahoo);
                else
                    imgEmail.setImageResource(R.drawable.ic_hotmail);
                textEmail.setText(user.email);

                if (user.gender.equalsIgnoreCase("female")) {
                    imgGender.setImageResource(R.drawable.ic_women);
                    textGender.setText(user.gender);
                }
                else {
                    imgGender.setImageResource(R.drawable.ic_men);
                    textGender.setText(user.gender);
                }

                textDob.setText(user.dob);

            }
        });

    }

}
