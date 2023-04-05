package com.example.pinguin;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriteFile {
    private final ReadFile reader = new ReadFile();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void addText(ArrayList<User> users, String username, String password) throws IOException {
        try (FileWriter writer = new FileWriter("src/main/resources/data.json")) {
            users.add(new User(username, password));
            gson.toJson(users, writer);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void userWins(User user) throws FileNotFoundException {
        ArrayList<User> users = reader.readFile();
        for (User item : users) {
            if (item.username.equals(user.username)) {
                item.wins++;
            }
        }

        try (FileWriter writer = new FileWriter("src/main/resources/data.json")) {
            gson.toJson(users, writer);
        } catch (IOException ex){throw new RuntimeException(ex);}
    }
}
