package Ellectricity_Billing_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Objects;

public class pay_bill extends JFrame implements ActionListener {
    Choice searchMonthChoice;
    String meter;
    JButton pay, back;

    pay_bill(String meter) {
        setUndecorated(true);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background
        this.meter = meter;
        setSize(500, 530);
        setLocationRelativeTo(null); // Centering the frame
        setLayout(null);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 28));
        heading.setBounds(35, 20, 200, 30);
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(35, 80, 200, 20);
        add(meterNumber);

        JLabel meterNumberText = new JLabel("");
        meterNumberText.setBounds(300, 80, 200, 20);
        add(meterNumberText);

        JLabel name = new JLabel("Name");
        name.setBounds(35, 140, 200, 20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(300, 140, 200, 20);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35, 200, 200, 20);
        add(month);

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
        searchMonthChoice.setBounds(300, 200, 150, 20);
        add(searchMonthChoice);
        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);


        JLabel unitText = new JLabel("");
        unitText.setBounds(300,260,200,20);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(35,320,200,20);
        add(totalBill);


        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(300,320,200,20);
        add(totalBillText);


        JLabel status = new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);


        JLabel statusText = new JLabel("");
        statusText.setBounds(300,380,200,20);
        statusText.setForeground(Color.RED);
        add(statusText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            while (resultSet.next()){
                meterNumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        searchMonthChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+ searchMonthChoice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(150,460,100,25);
        pay.addActionListener(this);
        add(pay);


        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(35,460,100,25);
        back.addActionListener(this);
        add(back);

        setIconImage(logo.getImage());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==pay){
            try{
                database c = new database();
                c.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+ searchMonthChoice.getSelectedItem()+"'");
            }catch (Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
