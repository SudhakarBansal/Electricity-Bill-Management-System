package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class generate_bill extends JFrame implements ActionListener {
    private Choice searchMonthChoice;
    private String meter;
    private JTextArea area;
    private JButton generateButton;

    public generate_bill(String meter) {
        this.meter = meter;
        setTitle("Generate Bill");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        setSize(500, 700);
        setResizable(false); // Disable resizing

        setLocationRelativeTo(null);
        JPanel topPanel = new JPanel(new BorderLayout()); // Set layout manager to BorderLayout
        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(); // Left panel for meter label
        JLabel meterLabel = new JLabel("Meter Number: " + meter);
        meterLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        leftPanel.add(meterLabel);
        topPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(); // Right panel for month label and choice
        JLabel monthLabel = new JLabel("Month: ");
        monthLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        rightPanel.add(monthLabel);

        searchMonthChoice = new Choice();
        searchMonthChoice.add("January");
        searchMonthChoice.add("February");
        searchMonthChoice.add("March");
        searchMonthChoice.add("April");
        searchMonthChoice.add("May");
        searchMonthChoice.add("June");
        searchMonthChoice.add("July");
        searchMonthChoice.add("August");
        searchMonthChoice.add("September");
        searchMonthChoice.add("October");
        searchMonthChoice.add("November");
        searchMonthChoice.add("December");
        rightPanel.add(searchMonthChoice);

        topPanel.add(rightPanel, BorderLayout.EAST);

        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.CENTER);

        generateButton = new JButton("Generate Bill");
        generateButton.addActionListener(this);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(generateButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setIconImage(logo.getImage());
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            database c = new database();
            String selectedMonth = searchMonthChoice.getSelectedItem();
            area.setText("\n\tPower Limited \n\tElectricity Bill For Month of " + selectedMonth + ", 2023\n\n\n");
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no ='" + meter + "'");
            if (resultSet.next()) {
                area.append("\n\tCustomer Name\t: " + resultSet.getString("name"));
                area.append("\n\tCustomer Meter Number\t: " + resultSet.getString("meter_no"));
                area.append("\n\tCustomer Address \t: " + resultSet.getString("address"));
                area.append("\n\tCustomer City\t\t: " + resultSet.getString("city"));
                area.append("\n\tCustomer State\t: " + resultSet.getString("state"));
                area.append("\n\tCustomer Email\t: " + resultSet.getString("email"));
                area.append("\n\tCustomer Phone Number\t: " + resultSet.getString("phone_no"));

            }

            resultSet = c.statement.executeQuery("select * from meter_info where meter_number ='" + meter + "'");
            if (resultSet.next()) {
                area.append("\n\tCustomer Meter Location\t: " + resultSet.getString("meter_location"));
                area.append("\n\tCustomer Meter Type\t: " + resultSet.getString("meter_type"));
                area.append("\n\tCustomer Phase Code\t: " + resultSet.getString("phase_code"));
                area.append("\n\tCustomer Bill Type\t: " + resultSet.getString("bill_type"));
                area.append("\n\tCustomer Days\t: " + resultSet.getString("Days"));

            }
            resultSet = c.statement.executeQuery("select * from tax");
            if (resultSet.next()) {
                area.append("\n\n\tCost Per Unit\t\t: " + resultSet.getString("cost_per_unit"));
                area.append("\n\tMeter Rent\t\t: " + resultSet.getString("meter_rent"));
                area.append("\n\tService Charge\t: " + resultSet.getString("service_charge"));
                area.append("\n\tService Tax\t\t: " + resultSet.getString("service_tax"));
                area.append("\n\tSwacch Bharat\t\t: " + resultSet.getString("swacch_bharat"));
                area.append("\n\tGST\t\t: " + resultSet.getString("gst"));

            }
            resultSet = c.statement.executeQuery("select * from bill where meter_no = '" + meter + "' and month = '" + searchMonthChoice.getSelectedItem() + "'");
            if (resultSet.next()) {
                area.append("\n\tCurrent Month\t\t: " + resultSet.getString("month"));
                area.append("\n\tUnits Consumed\t: " + resultSet.getString("unit"));
                area.append("\n\tTotal Charges\t\t: " + resultSet.getString("total_bill"));
                area.append("\n\tTotal Payable\t\t: " + resultSet.getString("total_bill"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new generate_bill(""));
    }
}
