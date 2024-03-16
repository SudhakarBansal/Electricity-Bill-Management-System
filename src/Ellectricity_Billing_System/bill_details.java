package Ellectricity_Billing_System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Objects;

public class bill_details extends JFrame {
    String meter;

    bill_details(String meter) {
        super("View Bills");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));
        this.meter = meter;
        setSize(700, 650);
        setLocationRelativeTo(null); // Centering the frame
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        try {
            database c = new database();
            String query_bill = "select * from bill where meter_no = '" + meter + "'";
            ResultSet resultSet = c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);
        setIconImage(logo.getImage());
        setVisible(true);
    }

    public static void main(String[] args) {
        new bill_details("");
    }
}
