package Model;

import java.util.ArrayList;

public class User {
    private String name;
    private String passWord;
    private static ArrayList<User> forSignupUsers = new ArrayList<>();
    private static ArrayList<User> forLoginUsers = new ArrayList<>();

    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public static ArrayList<User> getForSignupUsers() {
        return forSignupUsers;
    }

    public static void setForSignupUsers(ArrayList<User> forSignupUsers) {
        User.forSignupUsers = forSignupUsers;
    }

    public static ArrayList<User> getForLoginUsers() {
        return forLoginUsers;
    }
}
