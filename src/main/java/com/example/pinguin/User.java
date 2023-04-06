package com.example.pinguin;

public class User {

    //user
    public final String username;
    public final String password;
    public int wins = 0;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        if (this.username.equals("")) {
            return String.format("Guest: %s\n", this.username, this.wins);
        }
        return String.format("%s: %s\n", this.username, this.wins);
    }
}
