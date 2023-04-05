package com.example.pinguin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

//read
public class ReadFile {
    public ArrayList<User> readFile() throws FileNotFoundException {
        Gson gson = new Gson();

        ArrayList<User> users;
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();

        try (Reader reader = new FileReader("src/main/resources/data.json")) {
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            String data = gson.toJson(json);
            users = gson.fromJson(data, userListType);
            return users;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int checkUsers(String username, String password, Text loginText) throws FileNotFoundException {
        ArrayList<User> data = readFile();
        if (data.size() == 0) return 1;

        for (User user : data) {
            if (user.username.equals(username) && user.password.equals(password)){return 2;}
            else if (user.username.equals(username) && !Objects.equals(user.password, password)) {
                loginText.setText("Onjuist wachtwoord!");
                loginText.setFill(Color.color(1, 0, 0));
                return 3;
            }
        }
        return 1;
    }
}
