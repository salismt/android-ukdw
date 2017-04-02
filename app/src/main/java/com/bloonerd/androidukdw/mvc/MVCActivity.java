package com.bloonerd.androidukdw.mvc;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bloonerd.androidukdw.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MVCActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 35;
    private ImageButton imageProfile;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private TextView dateOfBirth;
    private TextView gender;
    private EditText password;
    private EditText confirmPassword;
    private Button saveBtn;
    private Bitmap imgFromGallery;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        initView();
        setImage();
        setDateOfBirth();
    }

    private void initView() {
        imageProfile = (ImageButton) findViewById(R.id.img_profile);
        firstName = (EditText) findViewById(R.id.input_first_name);
        lastName = (EditText) findViewById(R.id.input_last_name);
        email = (EditText) findViewById(R.id.input_email);
        dateOfBirth = (TextView) findViewById(R.id.text_date);
        gender = (TextView) findViewById(R.id.text_gender);
        password = (EditText) findViewById(R.id.input_password);
        confirmPassword = (EditText) findViewById(R.id.input_confirm_password);
        saveBtn = (Button) findViewById(R.id.btn_save);
    }

    private void setImage() {
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get the image and set the image
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgFromGallery = BitmapFactory.decodeFile(picturePath);
            if (imgFromGallery != null)
                imageProfile.setImageBitmap(imgFromGallery);
        }
    }

    private void setDateOfBirth() {
        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);

        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateOfBirth.setText(String.format(Locale.ENGLISH, "%d %s %d", day, getMonth(month), year));
            }
        }, year, month, day);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

}
