import Database.UserDatabase;
import View.FirstPage;

public class Main {
    public static void main(String[] args) {
        new FirstPage();
        UserDatabase.load();
    }
}