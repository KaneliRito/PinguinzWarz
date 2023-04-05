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
        return String.format("username: %s\npassword: %s\nhighscore: %s\n", this.username, this.password, this.wins);
    }
}
