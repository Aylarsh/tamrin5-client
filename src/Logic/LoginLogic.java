package Logic;

import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginLogic {
    private String name;
    private String pass;
    private boolean loggedIn;
    public void Login(){
        name = User.getForLoginUsers().get(0).getName();
        pass = User.getForLoginUsers().get(0).getPassWord();
        User.getForLoginUsers().remove(0);
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println(name);
            out.println("Login");
            out.println(name);
            out.println(pass);
            String response = in.readLine();
            System.out.println(response);
            if (response.equals("FAILURE")){
                loggedIn = false;
            } else if (response.equals("SUCCESS")) {
                loggedIn = true;
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
