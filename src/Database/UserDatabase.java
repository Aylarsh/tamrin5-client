package Database;

import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class UserDatabase {
    private static String fileName = "user.json";
    private static ArrayList<User> users;
    public static void  load() {
        users = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            users = (gson.fromJson(reader, userListType));
        } catch (IOException e) {
            User firstUser = new User(null,null);
            users.add(firstUser);
            save();
        }
    }
    public static void save(){
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<User> getUsers() {
        return users;
    }
}
