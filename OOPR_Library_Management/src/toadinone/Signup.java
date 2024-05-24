package toadinone;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.sql.SQLException;

import database.SignupManager;

public class Signup extends javax.swing.JFrame {

    public Signup() {
        initComponents();
        setVisible(true);
        setResizable(false);
        
    }

    private void initComponents() {
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(800, 500));
        

        // Create left panel for signup
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(500, 500));
        leftPanel.setLayout(null);

        JLabel createAccountLabel = new JLabel("CREATE ACCOUNT");
        createAccountLabel.setForeground(new Color(107, 173, 35));
        createAccountLabel.setFont(new Font("Serif", Font.BOLD, 34));
        createAccountLabel.setBounds(100, 50, 500, 50);

        JTextField studentNumberField = new JTextField(20);
        studentNumberField.setBounds(100, 170, 335, 35);
        JTextField emailField = new JTextField(20);
        emailField.setBounds(100, 260, 335, 35);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 350, 335, 35);

        JLabel studentNumberLabel = new JLabel("Student ID:");
        studentNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        studentNumberLabel.setBounds(70, 100, 150, 100);
        studentNumberLabel.setForeground(new Color(102, 102, 102));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(70, 190, 100, 100);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(102, 102, 102));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 280, 100, 100);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(102, 102, 102));

        JButton signupButton = new JButton("SIGN UP");
        signupButton.setForeground(new Color(107, 173, 35));
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(107, 173, 35), 2, true));
        signupButton.setBounds(200, 415, 90, 30);
        signupButton.setFocusPainted(false);
        
        JLabel studentNumberErrorLabel = new JLabel();
        studentNumberErrorLabel.setBounds(100, 205, 500, 20);
        studentNumberErrorLabel.setForeground(new Color(216, 30, 0));
        studentNumberErrorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel passwordErrorLabel = new JLabel();
        passwordErrorLabel.setBounds(100, 385, 500, 20);
        passwordErrorLabel.setForeground(new Color(216, 30, 0));
        passwordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        
        JLabel emailErrorLabel = new JLabel();
        emailErrorLabel.setBounds(100, 295, 500, 20);
        emailErrorLabel.setForeground(new Color(216, 30, 0));
        emailErrorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        signupButton.addActionListener(e -> {
        	String full_name = studentNumberField.getText();
        	String email = emailField.getText();
        	String password = new String(passwordField.getPassword());
        	
        	SignupManager sm = new SignupManager(email, password, full_name);
        	
        	try {
				Border defaultBorder = UIManager.getBorder("TextField.border");
        		int result = sm.signup();
        		
        		// Resetting every label and field to default state. Nung wala pang red na border tas mga text na warning sign.
        		
        		studentNumberErrorLabel.setText("");
				passwordErrorLabel.setText("");
				emailErrorLabel.setText("");
				
				studentNumberField.setBorder(defaultBorder);
				passwordField.setBorder(defaultBorder);
				emailField.setBorder(defaultBorder);
				
        		
				if(result == 0) {
					JOptionPane.showMessageDialog(null,"Successfully Signed-up!", "Success", JOptionPane.INFORMATION_MESSAGE);		
				}
				else if(result == 4) {
					studentNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
					studentNumberErrorLabel.setText("\u26A0 Invalid student number.");
					
				}
				else if(result == 1) {
					emailField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
					emailErrorLabel.setText("\u26A0 Invalid email.");

				}
				else if(result == 2) {
					emailField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
					emailErrorLabel.setText("\u26A0 Email is already in use by an existing account.");
				}
				else if(result == 3) {
					passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
					passwordErrorLabel.setText("\u26A0 Invalid password, use lower, uppercase letters, and symbols, with a length > 8.");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        leftPanel.add(Box.createVerticalStrut(40)); // Add vertical space
        leftPanel.add(createAccountLabel);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(studentNumberLabel);
        leftPanel.add(studentNumberField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(emailLabel);
        leftPanel.add(emailField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(signupButton);
        leftPanel.add(emailErrorLabel);
        leftPanel.add(passwordErrorLabel);
        leftPanel.add(studentNumberErrorLabel);

        // Create right panel for sign-in
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(107, 173, 35));
        rightPanel.setPreferredSize(new Dimension(300, 500));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("WELCOME USER!");
        welcomeLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel detailsLabel = new JLabel("Enter your personal details");
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsLabel.setForeground(Color.WHITE);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel journeyLabel = new JLabel("and start journey with us");
        journeyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        journeyLabel.setForeground(Color.WHITE);
        journeyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signinButton = new JButton("SIGN IN");
        signinButton.setForeground(Color.WHITE);
        signinButton.setBackground(new Color(107, 173, 35));
        signinButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        signinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signinButton.setFocusPainted(false);
        
        Dimension buttonSize = new Dimension(90, 30);
        signinButton.setMaximumSize(buttonSize);
        signinButton.setPreferredSize(buttonSize);
        
        signinButton.addActionListener(e -> {
        	dispose();
        	new Login();
        });

        rightPanel.add(Box.createVerticalStrut(150)); // Add vertical space
        rightPanel.add(welcomeLabel);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(detailsLabel);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(journeyLabel);
        rightPanel.add(Box.createVerticalStrut(40));
        rightPanel.add(signinButton);

        // Add panels to main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Set main panel as content pane and configure frame
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}
