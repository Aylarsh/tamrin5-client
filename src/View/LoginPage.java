package View;

import Logic.LoginLogic;
import Model.User;

import javax.swing.*;

public class LoginPage extends MainFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginLogic loginLogic = new LoginLogic();
    LoginPage() {
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

        JButton login = new JButton("Login");
        JButton backButton = new JButton("Back");
        backButton.setBounds(150,250,100,25);
        backButton.setFocusPainted(false);
        mainPanel.add(backButton);
        login.setBounds(150, 150, 100, 25);
        login.setFocusPainted(false);
        mainPanel.add(login);
        login.addActionListener(e->{
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User newUser = new User(username, password);
            User.getForLoginUsers().add(newUser);
            loginLogic.Login();
            if (loginLogic.isLoggedIn()){
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new TaskPage(username);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Username does not exist. Try again.", "Failure", JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(e->{
            new FirstPage();
            this.dispose();
        });
    }
}
