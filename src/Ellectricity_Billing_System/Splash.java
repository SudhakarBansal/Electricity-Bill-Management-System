package Ellectricity_Billing_System;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Splash extends JFrame {
    final JProgressBar progressBar;

    Splash() {
        // Remove window decorations
        setUndecorated(true);

        // Load application logo
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon/icon.png")));

        // Load splash image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Splash.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        // Create and configure the progress bar
        progressBar = new JProgressBar();

        // Set the preferred size of the progress bar (change width)
        progressBar.setPreferredSize(new Dimension(600, 7));

        // Set the background and foreground color of the progress bar (change color)
        progressBar.setBackground(Color.darkGray); // Background color
        progressBar.setForeground(new Color(251, 229, 198)); // Progress color

        // Remove border
        progressBar.setBorder(null);

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        // Disable percentage display
        progressBar.setStringPainted(false);

        // Add the progress bar to the bottom of the frame
        add(progressBar, BorderLayout.SOUTH);

        // Set frame properties
        setSize(600, 300);
        setLocationRelativeTo(null); // Center the window
        setIconImage(logo.getImage());
        setVisible(true);

        try {
            // Simulate loading
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(30); // Sleep for 30 milliseconds to simulate loading
                progressBar.setValue(i); // Update the progress bar value
            }
            setVisible(false);

            // Once loading is complete, open the Login window
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
