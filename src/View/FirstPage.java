package View;

import javax.swing.*;
import java.io.IOException;

public class FirstPage extends MainFrame{
    public FirstPage() {
        super(500, 500);
    }

    @Override
    protected void initComponents() {
        JButton signupButton = new JButton("Signup");
        JButton loginButton = new JButton("Login");
        signupButton.setFocusPainted(false);
        loginButton.setFocusPainted(false);
        signupButton.setBounds(200,70,100,40);
        mainPanel.add(signupButton);
        loginButton.setBounds(200,140,100,40);
        mainPanel.add(loginButton);
        signupButton.addActionListener(e->{
            new SignupPage();
            this.dispose();
        });
        loginButton.addActionListener(e->{
            new LoginPage();
            this.dispose();
        });

    }
}
