package com.bloonerd.androidukdw.model;

import com.bloonerd.androidukdw.UserPreference;

public class User {

    public String firstName;
    public String lastName;
    public String email;
    public String dob;
    public String gender;
    public String image;
    public String password;

    public User(String firstName, String lastName, String email, String dob, String gender, String image, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.image = image;
        this.password = password;
    }


}
