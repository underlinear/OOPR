package toadinone;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ForgotPassword extends JFrame{
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
        studentNumberLabel.setBounds(70, 170, 150, 50);
        
        JTextField studentNumberField = new JTextField(20);
        studentNumberField.setBounds(100, 220, 300, 35);
        
        JLabel loginLabel = new JLabel("Recover Account");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 36));
        loginLabel.setForeground(new Color(107, 173, 35));
        loginLabel.setBounds(115, 50, 480, 50);
        
        JButton sendCodeButton = new JButton("Send code to my email");
        sendCodeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sendCodeButton.setForeground(new Color(107, 183, 35));
        sendCodeButton.setBackground(Color.WHITE); 
        sendCodeButton.setBorder(BorderFactory.createLineBorder(new Color(107, 173, 35), 2));
        sendCodeButton.setBounds(140, 300, 200, 30);
        sendCodeButton.setFocusPainted(false);
        
        sendCodeButton.addActionListener(e -> {
        	
        });
        
        
        
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
