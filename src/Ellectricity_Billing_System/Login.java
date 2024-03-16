package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class Login extends JFrame implements ActionListener {
    JTextField userText, passwordText;
    Choice loginChoice;
    JButton loginButton, signupButton;

    Login() {
        super("Login");

        // Setting up the icon
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        setIconImage(logo.getImage());

        // Setting up the background color
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Logo
        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(285, 285, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(15, 5, 250, 250);
        add(profileLabel);

        // Title
        JLabel titleLabel = new JLabel("Electricity Billing System");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // Dark blue text
        titleLabel.setBounds(300, 20, 400, 40);
        add(titleLabel);

        // Username Label and Text Field
        JLabel usernameLabel = new JLabel("Username :");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        usernameLabel.setBounds(300, 80, 100, 25);
        add(usernameLabel);

        userText = new JTextField();
        userText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        userText.setBounds(420, 80, 180, 25);
        add(userText);

        // Password Label and Text Field
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        passwordLabel.setBounds(300, 120, 100, 25);
        add(passwordLabel);

        passwordText = new JPasswordField(); // Use JPasswordField for password input
        passwordText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        passwordText.setBounds(420, 120, 180, 25);
        add(passwordText);

        // Login As Label and Choice Component
        JLabel loginAsLabel = new JLabel("Login As :");
        loginAsLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        loginAsLabel.setBounds(300, 160, 100, 25);
        add(loginAsLabel);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        loginChoice.setBounds(420, 160, 180, 25);
        add(loginChoice);

        // Signup Button
        signupButton = new JButton("Signup");
        signupButton.setBounds(370, 210, 100, 26);
        signupButton.setBackground(new Color(0, 102, 204));
        signupButton.setForeground(Color.white);
        signupButton.addActionListener(this);
        add(signupButton);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(500, 210, 100, 26);
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(this);
        add(loginButton);

        // Setting frame properties
        setSize(650, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Disable resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String suser = loginChoice.getSelectedItem();

            try {
                database d = new database();
                String query = "select * from Signup where username = '" + susername + "' and password = '" + spassword + "' and usertype ='" + suser + "'";
                ResultSet resultSet = d.statement.executeQuery(query);

                if (resultSet.next()) {
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
