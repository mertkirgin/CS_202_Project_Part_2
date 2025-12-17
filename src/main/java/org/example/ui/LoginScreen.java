package org.example.ui;

import org.example.database.UserDAO;
import org.example.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private UserDAO userDAO;

    public LoginScreen() {
        // window settings
        setTitle("E-Commerce System - Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window
        setLayout(new GridBagLayout()); // Using GridBag to center the card

        // we create an instance of our worker class that talks to the database
        userDAO = new UserDAO();

        // background panel (an image would be good later)
        getContentPane().setBackground(new Color(240, 242, 245));

        // card
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(40, 40, 40, 40) // Padding
        ));

        // title
        JLabel lblTitle = new JLabel("Welcome");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(new Color(51, 51, 51));

        // email password login
        JLabel lblEmail = new JLabel("Email Address");
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtEmail = new JTextField(20);
        txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword = new JPasswordField(20);
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(30, 144, 255)); // Dodger Blue
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // sign up
        JButton btnRegister = new JButton("Don't have an account? Sign Up");
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setForeground(new Color(0, 102, 204));
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to card layout
        cardPanel.add(lblTitle);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        cardPanel.add(lblEmail);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        cardPanel.add(txtEmail);

        cardPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        cardPanel.add(lblPassword);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        cardPanel.add(txtPassword);

        cardPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        cardPanel.add(btnLogin);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        cardPanel.add(btnRegister);

        add(cardPanel);

        // listeners
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        getRootPane().setDefaultButton(btnLogin);

        btnRegister.addActionListener(e -> {
            new SignUpScreen().setVisible(true);
            dispose();
        });
    }

    // login logic
    private void handleLogin() {
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // check if the user exists
        User user = userDAO.login(email, password);

        if (user != null) {
            String role = user.getuRole();
            JOptionPane.showMessageDialog(this, "Welcome " + user.getuName() + " (" + role + ")");

            this.dispose();

            switch (role) {
                case "Customer":
                    new CustomerDashboard().setVisible(true);
                    break;
                case "Seller":
                    new SellerDashboard().setVisible(true);
                    break;
                case "Admin":
                    new AdminDashboard().setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Unknown Role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Email or Password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}