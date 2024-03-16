package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JLabel heading, meterNumText, customerName, meterNum, address, city, state, email, phone;
    JButton next, cancel;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;

    newCustomer() {
        setUndecorated(true);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        setSize(700, 450);
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Disable resizing

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(50, 25, 250, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 23));
        panel.add(heading);

        customerName = new JLabel("Customer Name");
        customerName.setBounds(50, 90, 120, 25);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180, 90, 200, 25);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 130, 120, 25);
        panel.add(meterNum);

        meterNumText = new JLabel("");
        meterNumText.setBounds(180, 130, 200, 25);
        panel.add(meterNumText);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterNumText.setText("" + Math.abs(number));

        address = new JLabel("Address ");
        address.setBounds(50, 170, 120, 25);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180, 170, 200, 25);
        panel.add(addressText);

        city = new JLabel("City ");
        city.setBounds(50, 210, 120, 25);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180, 210, 200, 25);
        panel.add(cityText);

        state = new JLabel("State ");
        state.setBounds(50, 250, 120, 25);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180, 250, 200, 25);
        panel.add(stateText);

        email = new JLabel("Email ");
        email.setBounds(50, 290, 120, 25);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180, 290, 200, 25);
        panel.add(emailText);

        phone = new JLabel("Phone ");
        phone.setBounds(50, 330, 120, 25);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180, 330, 200, 25);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(280, 400, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(50, 400, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        add(imgLable, BorderLayout.WEST);

        setIconImage(logo.getImage());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String sname = nameText.getText();
            String smeter = meterNumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            String queryCustomer = "insert into new_customer values('" + sname + "','" + smeter + "','" + saddress + "','" + scity + "','" + sstate + "','" + eemail + "','" + sphone + "')";
            String querySignup = "insert into Signup values('" + smeter + "','','" + sname + "','','')";

            try {
                database c = new database();
                c.statement.executeUpdate(queryCustomer);
                c.statement.executeUpdate(querySignup);

                JOptionPane.showMessageDialog(null, "Customer details added successfully");
                setVisible(false);
                new meterInfo(smeter);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
