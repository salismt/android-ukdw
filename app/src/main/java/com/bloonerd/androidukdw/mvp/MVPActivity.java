package com.bloonerd.androidukdw.mvp;

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

public class MVPActivity extends AppCompatActivity implements MVPView {

    private static final int RESULT_LOAD_IMAGE = 35;
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
    private MVPPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        userPreference = UserPreference.getInstance(this);
        initView();
        setImage();
        setDateOfBirth();
        setGender();
        setSaveButton();
        presenter = new MVPPresenter(userPreference, this);
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
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgFromGallery = BitmapFactory.decodeFile(picturePath);
            if (imgFromGallery != null)
                imageProfile.setImageBitmap(imgFromGallery);
        }
    }

    private void setGender() {
        gender.setAdapter(ArrayAdapter.createFromResource(
                this, R.array.genders, android.R.layout.simple_spinner_item));
    }

    private void setImage() {
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onImageClick();
            }
        });
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
                presenter.setDateOfBirth(day, month, year);
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

    private void setSaveButton() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validateForm(firstName.getText().toString(),
                        lastName.getText().toString(),
                        email.getText().toString(),
                        dateOfBirth.getText().toString(),
                        gender.getSelectedItem().toString(),
                        password.getText().toString(),
                        confirmPassword.getText().toString(),
                        picturePath);

            }
        });
    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveSuccess(String success) {
        Toast.makeText(this, success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDateOfBirth(String dob) {
        dateOfBirth.setText(dob);
    }

    @Override
    public void showGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
}
