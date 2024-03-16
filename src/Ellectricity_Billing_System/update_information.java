package Ellectricity_Billing_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class update_information extends JFrame implements ActionListener {
    JLabel nameText;
    JTextField addressText,cityText,stateText,emailText,phoneText;
    String meter;
    JButton update,cancel;
    update_information(String meter){
        setUndecorated(true);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        this.meter = meter;
        setSize(777, 450);
        setLocationRelativeTo(null); // Centering the frame
        setResizable(false); // Disabling resizing
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(null);

        JLabel heading = new JLabel("Update Your Information");
        heading.setBounds(30,10,400,40);
        heading.setForeground(new Color(0, 102, 204)); // Dark blue text
        heading.setFont(new Font("Arial",Font.BOLD,25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,25);
        add(name);

        nameText = new JLabel("");
        nameText.setBounds(150,70,200,25);
        add(nameText);

        JLabel meterNo = new JLabel("Meter No.");
        meterNo.setBounds(30,110,100,25);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(150,110,100,25);
        add(meterText);


        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,25);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(150,150,200,25);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,25);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(150,190,200,25);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,25);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(150,230,200,25);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(30,270,100,25);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(150,270,200,25);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(30,310,100,25);
        add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(150,310,200,25);
        add(phoneText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            if (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBackground(new Color(0, 102, 204));
        update.setForeground(Color.white);
        update.setBounds(200,380,120,25);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(0, 102, 204));
        cancel.setForeground(Color.white);
        cancel.setBounds(30,380,120,25);
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(360,410,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imgLabel = new JLabel(imageIcon1);
        imgLabel.setBounds(420,0,360,410);
        add(imgLabel);

        setIconImage(logo.getImage());
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==update){
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            try{
                database c = new database();
                c.statement.executeUpdate("update new_customer set address ='"+saddress+"', city = '"+scity+"', state = '"+sstate+"', email = '"+semail+"', phone_no ='"+sphone+"' where meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new update_information("");
    }
}
