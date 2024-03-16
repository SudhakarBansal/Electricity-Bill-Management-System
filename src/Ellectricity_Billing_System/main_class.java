package Ellectricity_Billing_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class main_class extends JFrame implements ActionListener {
    String accType;
    String meter_pass;
    JButton newCustomer, customerDetails, depositDetails,calculateBill,updateInfo,viewInfo,payBill,billDetails,genBill,exit;

    main_class(String accType, String meter_pass) {
        super("Online Electricity Billing Portal");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        this.meter_pass = meter_pass;
        this.accType = accType;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Background Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image image = imageIcon.getImage().getScaledInstance(1530, 830, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        // Create buttons with text and icons
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomers.png"));
        newCustomer = new JButton();
        newCustomer.setIcon(icon1);
        newCustomer.setBorderPainted(false);

        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        customerDetails = new JButton();
        customerDetails.setIcon(icon2);
        customerDetails.setBorderPainted(false);

        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
        depositDetails = new JButton();
        depositDetails.setIcon(icon3);
        depositDetails.setBorderPainted(false);

        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/calculatorbills.png"));
        calculateBill = new JButton();
        calculateBill.setIcon(icon4);
        calculateBill.setBorderPainted(false);

        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        exit = new JButton();
        exit.setIcon(icon5);
        exit.setBorderPainted(false);

        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        viewInfo = new JButton();
        viewInfo.setIcon(icon6);
        viewInfo.setBorderPainted(false);

        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/upinfo.png"));
        updateInfo = new JButton();
        updateInfo.setIcon(icon7);
        updateInfo.setBorderPainted(false);

        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/genBill.png"));
        genBill = new JButton();
        genBill.setIcon(icon8);
        genBill.setBorderPainted(false);

        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/billDetail.png"));
        billDetails = new JButton();
        billDetails.setIcon(icon9);
        billDetails.setBorderPainted(false);

        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        payBill = new JButton();
        payBill.setIcon(icon10);
        payBill.setBorderPainted(false);

        // Set button widths and heights
        int buttonWidth = 200;
        int buttonHeight = 200;

        // Calculate x-coordinate for the center of the screen
        int centerX = ((Toolkit.getDefaultToolkit().getScreenSize().width - buttonWidth * 3) / 2) - 50;
        int centerY = ((Toolkit.getDefaultToolkit().getScreenSize().height - buttonWidth * 3) / 2) + 25;


        // Set button positions
        // Admin Buttons
        newCustomer.setBounds(centerX, centerY, buttonWidth, buttonHeight);
        customerDetails.setBounds(centerX + buttonWidth + 50, centerY, buttonWidth, buttonHeight);
        depositDetails.setBounds(centerX + 2 *  (buttonWidth + 50), centerY, buttonWidth, buttonHeight);
        calculateBill.setBounds(centerX, centerY + buttonHeight + 50, buttonWidth, buttonHeight);
        exit.setBounds(centerX + buttonWidth + 50, centerY + buttonHeight + 50, buttonWidth, buttonHeight);

        // Customer Buttons
        viewInfo.setBounds(centerX, centerY, buttonWidth, buttonHeight);
        updateInfo.setBounds(centerX + buttonWidth + 50, centerY, buttonWidth, buttonHeight);
        genBill.setBounds(centerX + 2 *  (buttonWidth + 50), centerY, buttonWidth, buttonHeight);
        billDetails.setBounds(centerX, centerY + buttonHeight + 50, buttonWidth, buttonHeight);
        payBill.setBounds(centerX + 2 *  (buttonWidth + 50), centerY + buttonHeight + 50, buttonWidth, buttonHeight);

        // Add action listeners
        newCustomer.addActionListener(this);
        customerDetails.addActionListener(this);
        depositDetails.addActionListener(this);
        calculateBill.addActionListener(this);
        exit.addActionListener(this);
        viewInfo.addActionListener(this);
        updateInfo.addActionListener(this);
        genBill.addActionListener(this);
        billDetails.addActionListener(this);
        payBill.addActionListener(this);


        // Add buttons to the background image label
        imageLabel.setLayout(null); // Set layout to null to position buttons freely
        if (accType.equals("Admin")){
            imageLabel.add(newCustomer);
            imageLabel.add(customerDetails);
            imageLabel.add(depositDetails);
            imageLabel.add(calculateBill);
        }else {
            imageLabel.add(viewInfo);
            imageLabel.add(updateInfo);
            imageLabel.add(genBill);
            imageLabel.add(billDetails);
            imageLabel.add(payBill);
        }
        imageLabel.add(exit);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(logo.getImage());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        Object source = e.getSource();
        if (source == newCustomer) {
            new newCustomer();
        } else if (source == customerDetails) {
            new customer_details();
        } else if (source == depositDetails) {
            new deposit_details();
        } else if (source == calculateBill) {
            new calculate_bill();
        }else if (source == updateInfo) {
            new update_information(meter_pass);
        }else if (source == viewInfo) {
            new view_information(meter_pass);
        }else if (source == payBill) {
            new pay_bill(meter_pass);
        }else if (source == billDetails) {
            new bill_details(meter_pass);
        }else if (source == genBill) {
            new generate_bill(meter_pass);
        }else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new main_class("", "");
    }
}
