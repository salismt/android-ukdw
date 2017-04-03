package com.bloonerd.androidukdw.mvp;

import com.bloonerd.androidukdw.UserPreference;
import com.bloonerd.androidukdw.Util;
import com.bloonerd.androidukdw.model.User;

public class MVPPresenter {

    private UserPreference userPreference;
    private MVPView view;

    public MVPPresenter(UserPreference userPreference, MVPView view) {
        this.userPreference = userPreference;
        this.view = view;
    }

    public void validateForm(String firstName, String lastName, String email,
                             String dob, String gender, String pass, String confPass, String picturePath) {
        if (firstName.length() == 0) {
            view.showError("First name cannot be empty");
        } else if (lastName.length() == 0) {
            view.showError("Last name cannot be empty");
        } else if (email.length() == 0) {
            view.showError("Email cannot be empty");
        } else if (!Util.isValidEmail(email)) {
            view.showError("Please input valid email");
        } else if (dob.length() == 0) {
            view.showError("Date cannot be empty");
        } else if (gender.length() == 0) {
            view.showError("Gender cannot be empty");
        } else if (pass.length() == 0) {
            view.showError("Password cannot be empty");
        } else if (confPass.length() == 0) {
            view.showError("Confirm Password cannot be empty");
        } else if (pass.length() < 8) {
            view.showError("Password cannot be less than 8");
        } else if (confPass.length() < 8) {
            view.showError("Confirm Password cannot be less than 8");
        } else if (!pass.equals(confPass)) {
            view.showError("Password doesn\'t match");
        } else {
            User user = new User(firstName, lastName, email, dob, gender, picturePath, pass);
            userPreference.saveUser(user);
            view.onSaveSuccess("Saved");
        }

    }
}
