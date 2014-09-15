package com.store.hive.model.people;

/**
 * Created by tinashe
 */
public class RegisteredUser {

    private boolean isLoggedIn;
    private String fullName;

    public RegisteredUser(boolean isLoggedIn, String fullName) {
        this.isLoggedIn = isLoggedIn;
        this.fullName = fullName;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getFullName() {
        return fullName;
    }
}
