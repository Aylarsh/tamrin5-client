package View;

import Logic.SignupLogic;
import Model.User;

import javax.swing.*;

public class SignupPage extends MainFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    SignupLogic signupLogic = new SignupLogic();
    SignupPage() {
        super(500,500);
    }

    @Override
    protected void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // No layout manager

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 25);
        add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 25);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 25);
        add(passwordField);

        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
        backButton.setBounds(150,250,100,25);
        backButton.setFocusPainted(false);
        mainPanel.add(backButton);
        signupButton.setBounds(150, 150, 100, 25);
        signupButton.setFocusPainted(false);
        mainPanel.add(signupButton);
        signupButton.addActionListener(e->{
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User newUser = new User(username, password);
            User.getForSignupUsers().add(newUser);
            signupLogic.signUp();
            if (signupLogic.isSignedUp()){
                JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "Username already exists. Try again.", "Failure", JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(e->{
            new FirstPage();
            this.dispose();
        });
    }
}
