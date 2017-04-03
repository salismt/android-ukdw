package com.bloonerd.androidukdw.mvvm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;

import com.bloonerd.androidukdw.R;
import com.bloonerd.androidukdw.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MVVMDisplayViewModel {

    private static final String TAG = "MVVMDisplayViewModel";
    private final Context context;
    final private User user;

    public MVVMDisplayViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public RoundedBitmapDrawable getProfile() {
        RoundedBitmapDrawable imgRound = RoundedBitmapDrawableFactory.create(context.getResources(), user.image);
        imgRound.setCircular(true);
        return imgRound;
    }

    public String getName() {
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
                return "Dek " + user.firstName + " " + user.lastName;
            } else {
                return user.firstName + " " + user.lastName;
            }
        } else {
            return "";
        }
    }

    public Drawable getEmailIcon() {
        if (user.email.contains("gmail"))
            return ContextCompat.getDrawable(context, R.drawable.ic_gmail);
        else if (user.email.contains("yahoo"))
            return ContextCompat.getDrawable(context, R.drawable.ic_yahoo);
        else
            return ContextCompat.getDrawable(context, R.drawable.ic_hotmail);
    }

    public String getEmail() {
        return user.email;
    }

    public Drawable getGenderIcon() {
        if (user.gender.equalsIgnoreCase("female"))
            return ContextCompat.getDrawable(context, R.drawable.ic_women);
        else
            return ContextCompat.getDrawable(context, R.drawable.ic_men);
    }
}
