package com.store.hive.model.people;

/**
 * Created by tinashe
 */
public class RegisteredUser {

    private boolean isLoggedIn;
    private int ownerId;
    private String fullName;
    private String email;

    public RegisteredUser(boolean isLoggedIn, int ownerId, String fullName, String email) {
        this.isLoggedIn = isLoggedIn;
        this.ownerId = ownerId;
        this.fullName = fullName;
        this.email = email;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
