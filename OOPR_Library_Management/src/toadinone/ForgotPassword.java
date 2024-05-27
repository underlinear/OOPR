package toadinone;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import database.PasswordHandler;
import database.User;

public class ForgotPassword extends JFrame{
	
	private String studentNumber;
	
	public ForgotPassword() {
		initComponents();
		setResizable(false);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initComponents() {
		JPanel leftPanel = new JPanel();
	    leftPanel.setBackground(new Color(107, 173, 35));
	    leftPanel.setBounds(0, 0, 320, 500);
	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	    
	    File iconFile = new File("src/Icon/arrow.png");
        if (!iconFile.exists()) {
            System.err.println("Could not find the image.");
            System.exit(1);
        }
        ImageIcon icon = new ImageIcon(iconFile.getPath());

        JButton arrowButton = new JButton(icon);
        arrowButton.setBounds(20, 20, 50, 50);
        arrowButton.setBackground(getBackground());
        arrowButton.setBorder(null);
        arrowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton goBackButton = new JButton("Back to Sign-In");
        goBackButton.setBounds(50, 35, 130, 20);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBorder(null);
        goBackButton.setBackground(new Color(107, 173, 35)); 
        goBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        goBackButton.addActionListener(e -> {
        	dispose();
        	new Login();
        	return;
        });
        
        arrowButton.addActionListener(e -> {
        	dispose();
        	new Login();
        	return;
        });

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
	    
	    JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(320, 0, 480, 500);
        rightPanel.setLayout(null);
        
        JLabel studentNumberLabel = new JLabel("Enter Student Number:");
        studentNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        studentNumberLabel.setForeground(new Color(102, 102, 102));
        studentNumberLabel.setBounds(70, 170, 300, 50);
        
        JTextField studentNumberField = new JTextField(20);
        studentNumberField.setBounds(100, 220, 300, 35);
        
        JLabel loginLabel = new JLabel("Recover Account");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 36));
        loginLabel.setForeground(new Color(107, 173, 35));
        loginLabel.setBounds(115, 50, 480, 50);
        
        JLabel failedLabel = new JLabel();
        failedLabel.setBounds(100, 255, 400, 20);
        failedLabel.setForeground(new Color(216, 30, 0));
        failedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton sendCodeButton = new JButton("Send code to my email");
        sendCodeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sendCodeButton.setForeground(new Color(107, 183, 35));
        sendCodeButton.setBackground(Color.WHITE); 
        sendCodeButton.setBorder(BorderFactory.createLineBorder(new Color(107, 173, 35), 2));
        sendCodeButton.setBounds(140, 280, 200, 30);
        sendCodeButton.setFocusPainted(false);
        
        JButton verifyButton = new JButton("Verify");
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        verifyButton.setForeground(new Color(107, 183, 35));
        verifyButton.setBackground(Color.WHITE); 
        verifyButton.setBorder(BorderFactory.createLineBorder(new Color(107, 173, 35), 2));
        verifyButton.setBounds(140, 280, 200, 30);
        verifyButton.setFocusPainted(false);
        
        JButton newPasswordButton = new JButton("Set Password");
        newPasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        newPasswordButton.setForeground(new Color(107, 183, 35));
        newPasswordButton.setBackground(Color.WHITE); 
        newPasswordButton.setBorder(BorderFactory.createLineBorder(new Color(107, 173, 35), 2));
        newPasswordButton.setBounds(140, 280, 200, 30);
        newPasswordButton.setFocusPainted(false);
        
        sendCodeButton.addActionListener(e -> {
        	if(User.verifyStudentNumber(studentNumberField.getText())){
        		studentNumberField.setBorder(UIManager.getBorder("TextField.border"));
        		studentNumberLabel.setText("Enter code sent to your email:");
        		studentNumber = new String(studentNumberField.getText());
        		studentNumberField.setText("");
        		rightPanel.remove(sendCodeButton);
        		failedLabel.setText("");
        		rightPanel.add(verifyButton);
        		rightPanel.revalidate();
        		repaint();
        	}
        	else {
        		failedLabel.setText("\u26A0 Invalid student number!");
        		studentNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
        	}
        });
        
        verifyButton.addActionListener(e -> {
        	try {
				if(User.verifyCode(studentNumber, studentNumberField.getText())) {
					studentNumberField.setBorder(UIManager.getBorder("TextField.border"));
	        		studentNumberLabel.setText("Enter a new password:");
	        		studentNumberField.setText("");
	        		rightPanel.remove(verifyButton);
	        		failedLabel.setText("");
	        		rightPanel.add(newPasswordButton);
	        		rightPanel.revalidate();
	        		repaint();
				}
				else {
					failedLabel.setText("\u26A0 Incorrect code!");
					studentNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        });
        
        newPasswordButton.addActionListener(e -> {
        	PasswordHandler ph = new PasswordHandler(studentNumberField.getText());
        	if(ph.isValidPassword()) {
        		try {
					ph.updatePasswordByStudentNumber(studentNumber);
					JOptionPane.showMessageDialog(null, "Successfully changed password.", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new Login();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	else {
        		failedLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        		failedLabel.setText("\u26A0 Invalid password, use lower, uppercase letters, and symbols, with a length > 8.");
				studentNumberField.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
        	}
        });
        
        rightPanel.add(failedLabel);
        rightPanel.add(studentNumberLabel);
        rightPanel.add(studentNumberField);
        rightPanel.add(loginLabel);
        rightPanel.add(sendCodeButton);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(welcomeLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(keepConnectedLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(signUpInfoLabel);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(signUpButton);
        leftPanel.add(Box.createVerticalGlue());
        add(arrowButton);
        add(goBackButton);
		add(leftPanel);
		add(rightPanel);
		
		setLayout(null);
	}
	
	public static void main(String[] args) {
		new ForgotPassword();
	}
}
