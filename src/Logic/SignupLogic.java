package Logic;

import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SignupLogic {
    private String name;
    private String pass;
    private boolean signedUp;

    public void signUp(){
       name = User.getForSignupUsers().get(0).getName();
       pass = User.getForSignupUsers().get(0).getPassWord();
       User.getForSignupUsers().remove(0);
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println(name);
            out.println("SIGNUP");
            out.println(name);
            out.println(pass);
            String response = in.readLine();
            System.out.println(response);
            if (response.equals("FAILURE")){
                signedUp = false;
            } else if (response.equals("SUCCESS")) {
                signedUp = true;
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSignedUp() {
        return signedUp;
    }
}

