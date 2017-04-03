package com.bloonerd.androidukdw.common;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bloonerd.androidukdw.R;
import com.bloonerd.androidukdw.UserPreference;
import com.bloonerd.androidukdw.model.User;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class CommonActivity extends AppCompatActivity {

    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final int RESULT_LOAD_IMAGE = 35;
    private static final int REQ_IMG_PERMISSION = 14;
    private ImageButton imageProfile;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private TextView dateOfBirth;
    private Spinner gender;
    private EditText password;
    private EditText confirmPassword;
    private Button saveBtn;
    private Bitmap imgFromGallery;
    private Calendar calendar;
    private UserPreference userPreference;
    private String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        userPreference = UserPreference.getInstance(this);
        initView();
        setImage();
        setDateOfBirth();
        setGender();
        setSaveButton();
    }

    private void initView() {
        imageProfile = (ImageButton) findViewById(R.id.img_profile);
        firstName = (EditText) findViewById(R.id.input_first_name);
        lastName = (EditText) findViewById(R.id.input_last_name);
        email = (EditText) findViewById(R.id.input_email);
        dateOfBirth = (TextView) findViewById(R.id.text_date);
        gender = (Spinner) findViewById(R.id.spinner_gender);
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
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_IMG_PERMISSION);
            } else {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                imgFromGallery = BitmapFactory.decodeFile(picturePath);
                if (imgFromGallery != null)
                    imageProfile.setImageBitmap(imgFromGallery);
            }
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

    private void setGender() {
        gender.setAdapter(ArrayAdapter.createFromResource(
                this, R.array.genders, android.R.layout.simple_spinner_item));

    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    private void setSaveButton() {
        final Context context = this;

        final Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String emailStr = email.getText().toString();
                String dob = dateOfBirth.getText().toString();
                String genderStr = gender.getSelectedItem().toString();
                String passStr = password.getText().toString();
                String confPassStr = confirmPassword.getText().toString();

                if (fName.length() == 0) {
                    Toast.makeText(context, "First name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (lName.length() == 0) {
                    Toast.makeText(context, "Last name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (emailStr.length() == 0) {
                    Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!emailPattern.matcher(emailStr).find()) {
                    Toast.makeText(context, "Please input valid email", Toast.LENGTH_SHORT).show();
                } else if (dob.length() == 0) {
                    Toast.makeText(context, "Date cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (genderStr.length() == 0) {
                    Toast.makeText(context, "Date cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (passStr.length() == 0) {
                    Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (confPassStr.length() == 0) {
                    Toast.makeText(context, "Confirm Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (passStr.length() < 8) {
                    Toast.makeText(context, "Password cannot be less than 8", Toast.LENGTH_SHORT).show();
                } else if (confPassStr.length() < 8) {
                    Toast.makeText(context, "Confirm Password cannot be less than 8", Toast.LENGTH_SHORT).show();
                } else if (!passStr.equals(confPassStr)) {
                    Toast.makeText(context, "Password doesn\'t match", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(fName, lName, emailStr, dob, genderStr, picturePath, passStr);
                    userPreference.saveUser(user);
                    Toast.makeText(context, "SAVED" + dob, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, CommonDisplayActivity.class));
                }

            }
        });
    }

}
