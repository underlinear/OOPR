package toadinone;

import javax.swing.*;

import database.LoginManager;
import database.PasswordHandler;
import database.User;

import java.awt.*;
import java.sql.SQLException;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        setResizable(false);
        setVisible(true); // Make the frame visible
    }

    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setLayout(null);
        

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(107, 173, 35));
        rightPanel.setBounds(0, 0, 320, 500);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("WELCOME BACK!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel keepConnectedLabel = new JLabel("To keep connected with us please");
        keepConnectedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        keepConnectedLabel.setForeground(Color.WHITE);
        keepConnectedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signUpInfoLabel = new JLabel("Sign-up with your personal info.");
        signUpInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpInfoLabel.setForeground(Color.WHITE);
        signUpInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(107, 173, 35));
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        signUpButton.addActionListener(e -> {
        	dispose();
        	new Signup();
        });
        
        Dimension buttonSize = new Dimension(90, 30);
        signUpButton.setMaximumSize(buttonSize);
        signUpButton.setPreferredSize(buttonSize);
        signUpButton.setFocusPainted(false);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(welcomeLabel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(keepConnectedLabel);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(signUpInfoLabel);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(signUpButton);
        rightPanel.add(Box.createVerticalGlue());

        mainPanel.add(rightPanel);

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(320, 0, 480, 500);
        leftPanel.setLayout(null);

        JLabel loginLabel = new JLabel("Login Account");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 36));
        loginLabel.setForeground(new Color(107, 173, 35));
        loginLabel.setBounds(140, 50, 480, 50);

        JLabel studentNumberLabel = new JLabel("Student Number:");
        studentNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        studentNumberLabel.setForeground(new Color(102, 102, 102));
        studentNumberLabel.setBounds(70, 120, 150, 50);

        JTextField studentNumberField = new JTextField(20);
        studentNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        studentNumberField.setBounds(100, 170, 300, 35);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(102, 102, 102));
        passwordLabel.setBounds(70, 210, 100, 50);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passwordField.setBounds(100, 260, 300, 35);

        JButton forgotPasswordButton = new JButton("<html><u>Forgot Password?</u></html>");
        forgotPasswordButton.setBounds(100, 320, 120, 20);
        forgotPasswordButton.setFocusPainted(false);
        forgotPasswordButton.setBackground(Color.WHITE);
        forgotPasswordButton.setForeground(new Color(107, 173, 35));
        forgotPasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordButton.setBorder(null);
        
        forgotPasswordButton.addActionListener(e -> {
        	dispose();
        	new ForgotPassword();
        });
        

        JButton signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signInButton.setForeground(new Color(107, 183, 35));
        signInButton.setBackground(Color.WHITE); 
        signInButton.setBorder(BorderFactory.createLineBorder(new Color(107, 173, 35), 2));
        signInButton.setBounds(200, 360, 100, 30);
        signInButton.setFocusPainted(false);
        
        JLabel failedLabel = new JLabel("");
        failedLabel.setBounds(100, 300, 400, 20);
        failedLabel.setForeground(new Color(216, 30, 0));
        failedLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        signInButton.addActionListener(e -> {
        	
        	String studentNumber = studentNumberField.getText();
			String password = new String(passwordField.getPassword());
			
			if(studentNumber.equals("admin") && password.equals("admin")) {
				ELibrarySoft els = new ELibrarySoft();
				els.main(null);
				this.dispose();
			}
			
			
			LoginManager lm = new LoginManager(studentNumber, password); // lm.login() para i verify
			
			try {
				if( lm.login() == true ) {
					dispose();
					new Student_Home_Page();
					return;
				}
				else {
					studentNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
					passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
					failedLabel.setText("\u26A0 Incorrect student number or password!");
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
        });

        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(loginLabel);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(studentNumberLabel);
        leftPanel.add(studentNumberField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(forgotPasswordButton);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(signInButton);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(failedLabel);

        mainPanel.add(leftPanel);

        // Configure frame
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}