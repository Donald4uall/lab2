package com.userprofile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UserProfileApp {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("User Profile");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        frame.add(panel);

        // Add fields and labels
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel photoLabel = new JLabel("Photo:");
        JTextField photoField = new JTextField();
        JButton choosePhotoButton = new JButton("Choose Photo");

        JButton submitButton = new JButton("Submit");

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(photoLabel);
        panel.add(photoField);
        panel.add(choosePhotoButton); // Add "Choose Photo" button
        panel.add(submitButton);

        // Add Action Listener for Choose Photo Button
        choosePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Photo");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    photoField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Add Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String gender = genderField.getText().trim();
                String ageText = ageField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();
                String photoPath = photoField.getText().trim();

                StringBuilder errors = new StringBuilder();
                if (firstName.isEmpty()) errors.append("First name is required.\n");
                if (lastName.isEmpty()) errors.append("Last name is required.\n");
                if (!gender.matches("(?i)male|female")) errors.append("Gender must be Male or Female.\n");
                if (!ageText.matches("\\d+")) errors.append("Age must be a number.\n");
                if (!phone.matches("\\d{10}")) errors.append("Phone must be 10 digits.\n");
                if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) errors.append("Invalid email format.\n");

                if (errors.length() > 0) {
                    JOptionPane.showMessageDialog(frame, errors.toString(), "Input Errors", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Display User Profile
                    ImageIcon photo = null;
                    if (!photoPath.isEmpty()) {
                        File photoFile = new File(photoPath);
                        if (photoFile.exists()) {
                            photo = new ImageIcon(photoPath);
                            // Resize photo to fit dialog
                            Image img = photo.getImage();
                            Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            photo = new ImageIcon(resizedImg);
                        }
                    }

                    String profileInfo = String.format(
                        "Name: %s %s\nGender: %s\nAge: %s\nPhone: %s\nEmail: %s",
                        firstName, lastName, gender, ageText, phone, email
                    );

                    JOptionPane.showMessageDialog(frame, profileInfo, "User Profile", JOptionPane.INFORMATION_MESSAGE, photo);
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
