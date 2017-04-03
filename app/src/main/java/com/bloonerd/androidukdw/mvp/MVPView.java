package com.bloonerd.androidukdw.mvp;

public interface MVPView {
    void showError(String error);

    void onSaveSuccess(String success);

    void getDateOfBirth(String dob);

    void showGallery();
}
