package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Objects;

public class Signup extends JFrame implements ActionListener {
    // Components
    Choice loginASCho;
    JTextField meterText, EmployerText, userNameText, nameText, passwordText;
    JButton create, back;

    Signup() {
        super("Signup Page");
        // Setting up the window
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        setContentPane(new JPanel());
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 380);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());

        // Labels
        JLabel createAs = new JLabel("Create Account As");
        createAs.setFont(new Font("Arial", Font.PLAIN, 13));
        createAs.setBounds(30, 50, 140, 20);
        add(createAs);

        // Choice
        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170, 50, 150, 25);
        loginASCho.setFont(new Font("Arial", Font.PLAIN, 13));
        add(loginASCho);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setFont(new Font("Arial", Font.PLAIN, 13));
        meterNo.setVisible(false);
        add(meterNo);


        // Text fields
        meterText = new JTextField();
        meterText.setBounds(170, 100, 150, 25);
        meterText.setFont(new Font("Arial", Font.PLAIN, 13));
        meterText.setVisible(false);
        add(meterText);


        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30, 100, 125, 20);
        Employer.setFont(new Font("Arial", Font.PLAIN, 13));
        Employer.setVisible(true);
        add(Employer);

        EmployerText = new JTextField();
        EmployerText.setBounds(170, 100, 150, 25);
        EmployerText.setFont(new Font("Arial", Font.PLAIN, 13));
        EmployerText.setVisible(true);
        add(EmployerText);


        JLabel userName = new JLabel("UserName");
        userName.setBounds(30, 140, 125, 20);
        userName.setFont(new Font("Arial", Font.PLAIN, 13));
        add(userName);

        userNameText = new JTextField();
        userNameText.setBounds(170, 140, 150, 25);
        userNameText.setFont(new Font("Arial", Font.PLAIN, 13));
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 180, 125, 20);
        name.setFont(new Font("Arial", Font.PLAIN, 13));
        add(name);

        nameText = new JTextField("");
        nameText.setBounds(170, 180, 150, 25);
        nameText.setFont(new Font("Arial", Font.PLAIN, 13));
        add(nameText);


        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        password.setFont(new Font("Arial", Font.PLAIN, 13));
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(170, 220, 150, 25);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 13));
        add(passwordText);

        // Listeners
        meterText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup  where meter_no = '" + meterText.getText() + "'");
                    if (resultSet.next()) {
                        nameText.setText(resultSet.getString("name"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        loginASCho.addItemListener(e -> {
            String user = loginASCho.getSelectedItem();
            if ("Customer".equals(user)) {
                Employer.setVisible(false);
                nameText.setEditable(false);
                EmployerText.setVisible(false);
                meterNo.setVisible(true);
                meterText.setVisible(true);
            } else {
                Employer.setVisible(true);
                nameText.setEditable(true);
                EmployerText.setVisible(true);
                meterNo.setVisible(false);
                meterText.setVisible(false);
            }
        });

        // Buttons
        back = new JButton("Back");
        back.setBackground(new Color(0, 102, 204));
        back.setForeground(Color.white);
        back.setBounds(30, 285, 100, 26);
        back.addActionListener(this);
        add(back);

        create = new JButton("Create");
        create.setBackground(new Color(0, 102, 204));
        create.setForeground(Color.white);
        create.setBounds(160, 285, 100, 26);
        create.addActionListener(this);
        add(create);

        // Image
        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(340, 30, 250, 250);
        add(boyLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String sloginAs = loginASCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try {
                database c = new database();
                String query;
                if ("Admin".equals(sloginAs)) {
                    query = "INSERT INTO Signup VALUES ('" + smeter + "', '" + susername + "', '" + sname + "','" + spassword + "','" + sloginAs + "')";
                } else {
                    query = "UPDATE Signup SET username = '" + susername + "', password = '" + spassword + "', usertype = '" + sloginAs + "' WHERE meter_no = '" + smeter + "'";
                }

                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created");
                setVisible(false);
                new Login();

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
