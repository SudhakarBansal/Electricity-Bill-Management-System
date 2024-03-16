package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class view_information extends JFrame implements ActionListener {
    String view;
    JButton cancel;
    view_information(String view){
        setUndecorated(true);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        this.view = view;
        setSize(850, 650);
        setLocationRelativeTo(null); // Centering the frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Your Information");
        heading.setBounds(70,5,500,50);
        heading.setFont(new Font("Arial",Font.BOLD,25) );
        add(heading);


        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70,80,100,20);
        add(nameLabel);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBounds(200,80,150,20);
        add(nameLabelText);


        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(70,140,130,20);
        add(meterNo);


        JLabel meterNoText = new JLabel("");
        meterNoText.setBounds(200,140,150,20);
        add(meterNoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);


        JLabel addressText = new JLabel("");
        addressText.setBounds(200,200,150,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);


        JLabel cityText = new JLabel("");
        cityText.setBounds(200,260,150,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);


        JLabel stateText = new JLabel("");
        stateText.setBounds(600,80,150,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600,140,230,20);
        add(emailText);


        JLabel phone = new JLabel("phone");
        phone.setBounds(500,200,100,20);
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600,200,150,20);
        add(phoneText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+view+"'");
            if (resultSet.next()){
                nameLabelText.setText(resultSet.getString("name"));
                meterNoText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(24, 118, 242));
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(70, 350, 120, 25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon a1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewinfo.png"));
        Image a2 = a1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(a2);
        JLabel img = new JLabel(i3);
        img.setBounds(100, 320, 600, 300);
        add(img);
        setIconImage(logo.getImage());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_information("");

    }
}
