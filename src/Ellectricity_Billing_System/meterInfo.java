package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocCho, meterTypCho,phaseCodeCho, billTypeCho;
    JButton submit;
    String meterNumber;
    meterInfo(String meternumber){
        this.meterNumber =meternumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));

        JLabel heading  =new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50,80,150,20);
        panel.add(meterNumber);

         JLabel meterNumberText = new JLabel(meternumber);
         meterNumberText.setBounds(200,80,150,20);
         panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Location");
        meterLoc.setBounds(50,120,150,20);
        panel.add(meterLoc);

        meterLocCho =new Choice();
        meterLocCho.add("Outside");
        meterLocCho.add("Inside");
        meterLocCho.setBounds(200,120,150,20);
        panel.add(meterLocCho);

        JLabel meterTyp = new JLabel("Meter Type");
        meterTyp.setBounds(50,160,150,20);
        panel.add(meterTyp);

        meterTypCho =new Choice();
        meterTypCho.add("Electric Meter");
        meterTypCho.add("Solar Meter");
        meterTypCho.add("Smart Meter");
        meterTypCho.setBounds(200,160,150,20);
        panel.add(meterTypCho);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,200,150,20);
        panel.add(phaseCode);

        phaseCodeCho =new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(200,200,150,20);
        panel.add(phaseCodeCho);


        JLabel billtyp = new JLabel("Bill Type");
        billtyp.setBounds(50,240,150,20);
        panel.add(billtyp);

        billTypeCho =new Choice();
        billTypeCho.add("Normal");
        billTypeCho.add("Industrial");
        billTypeCho.setBounds(200,240,150,20);
        panel.add(billTypeCho);


        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50,280,200,20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,320,150,20);
        panel.add(note);

        JLabel note1 = new JLabel("By default bill is calculated for 30 days only");
        note1.setBounds(50,340,400,20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel,"East");


        setSize(700,500);
        setLocation(400,200);
        setIconImage(logo.getImage());
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){
            String smeterNum = meterNumber;
            String smeterLoc = meterLocCho.getSelectedItem();
            String smeterTyp = meterTypCho.getSelectedItem();
            String sphaseCode = phaseCodeCho.getSelectedItem();
            String sbillTyp = billTypeCho.getSelectedItem();
            String sday ="30";

            String query_meterInfo = "insert into meter_info values('"+smeterNum+"','"+smeterLoc+"','"+smeterTyp+"','"+sphaseCode+"','"+sbillTyp+"','"+sday+"')";
            try{
                database c= new database();
                c.statement.executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null,"Meter Information Submitted Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new meterInfo("");
    }
}
