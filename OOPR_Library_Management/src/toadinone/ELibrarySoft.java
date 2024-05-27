package toadinone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;

public class ELibrarySoft extends JFrame {

    private JPanel rightPanel;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JPanel daysPanel;

    public ELibrarySoft() {
        setTitle("eLibrary Soft");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(173, 223, 72));
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(1000, 50));

        JLabel titleLabel = new JLabel("eLibrary Soft");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        topPanel.add(titleLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(173, 223, 72));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        topPanel.add(logoutButton, BorderLayout.EAST);
        
        // Create the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(43, 57, 72));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel navLabel = new JLabel("Main Navigation");
        navLabel.setForeground(new Color(224, 224, 224));
        navLabel.setFont(new Font("Arial", Font.BOLD, 24));
        navLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        navLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        leftPanel.add(navLabel);
        
        // Create the right panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new CardLayout());

        // Add individual buttons with specific settings
        JButton button1 = new JButton("Dashboard");
        button1.setFont(new Font("Arial", Font.BOLD, 17));
        button1.setMaximumSize(new Dimension(Integer.MAX_VALUE, button1.getMinimumSize().height));
        button1.addActionListener(e -> {
            dashboard();
        });
        button1.setBackground(new Color(43, 57, 72));
        button1.setForeground(Color.WHITE);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(button1);

        JButton button2 = new JButton("System Management");
        button2.setFont(new Font("Arial", Font.BOLD, 17));
        button2.setMaximumSize(new Dimension(Integer.MAX_VALUE, button2.getMinimumSize().height));
        button2.addActionListener(e -> {
            systemManagement();
        });
        button2.setBackground(new Color(43, 57, 72));
        button2.setForeground(Color.WHITE);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(button2);

        JButton button3 = new JButton("Transaction");
        button3.setFont(new Font("Arial", Font.BOLD, 17));
        button3.setMaximumSize(new Dimension(Integer.MAX_VALUE, button3.getMinimumSize().height));
        button3.addActionListener(e -> {
            transaction();
        });
        button3.setBackground(new Color(43, 57, 72));
        button3.setForeground(Color.WHITE);
        button3.setBorderPainted(false);
        button3.setFocusPainted(false);
        button3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(button3);

        JButton button4 = new JButton("Calendar");
        button4.setFont(new Font("Arial", Font.BOLD, 17));
        button4.setMaximumSize(new Dimension(Integer.MAX_VALUE, button4.getMinimumSize().height));
        button4.addActionListener(e -> {
            calendar();
        });
        button4.setBackground(new Color(43, 57, 72));
        button4.setForeground(Color.WHITE);
        button4.setBorderPainted(false);
        button4.setFocusPainted(false);
        button4.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(button4);

        JButton button5 = new JButton("My Shelves");
        button5.setFont(new Font("Arial", Font.BOLD, 17));
        button5.setMaximumSize(new Dimension(Integer.MAX_VALUE, button5.getMinimumSize().height));
        button5.addActionListener(e -> {
            shelves();
        });
        button5.setBackground(new Color(43, 57, 72));
        button5.setForeground(Color.WHITE);
        button5.setBorderPainted(false);
        button5.setFocusPainted(false);
        button5.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(button5);

        JButton button6 = new JButton("Personal Details");
        button6.setFont(new Font("Arial", Font.BOLD, 17));
        button6.setMaximumSize(new Dimension(Integer.MAX_VALUE, button6.getMinimumSize().height));
        button6.addActionListener(e -> {
            personalDetails();
        });
        button6.setBackground(new Color(43, 57, 72));
        button6.setForeground(Color.WHITE);
        button6.setBorderPainted(false);
        button6.setFocusPainted(false);
        button6.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(button6);

        // Add panels to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.CENTER);
        
        calendarPanel = new JPanel(new BorderLayout());
        rightPanel.add(calendarPanel, BorderLayout.CENTER);
    }
    
    private void dashboard(){
        removeCalendarPanel();
        rightPanel.removeAll();
    }
    
    private void systemManagement(){
        removeCalendarPanel();
        rightPanel.removeAll();
    }
    
    private void transaction(){
        removeCalendarPanel();
        rightPanel.removeAll();
    }
    
    private void shelves(){
        removeCalendarPanel();
        rightPanel.removeAll();
    }
    
    private void personalDetails(){
        removeCalendarPanel();
        rightPanel.removeAll();
    }
    
    void calendar() {
        // Remove any existing calendar components
        calendarPanel.removeAll();

        // Create calendar components
        LocalDate now = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(now);
        monthLabel = new JLabel(currentMonth.getMonth().toString() + " " + currentMonth.getYear());
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        calendarPanel.add(monthLabel, BorderLayout.NORTH);

        daysPanel = new JPanel(new GridLayout(0, 7));
        addDayLabels(currentMonth);
        calendarPanel.add(daysPanel, BorderLayout.CENTER);

        // Add calendar panel to layered pane and set it to not be opaque
        rightPanel.add(calendarPanel, BorderLayout.CENTER);
        calendarPanel.setOpaque(false);

        // Repaint the rightPanel to make sure changes are visible
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void addDayLabels(YearMonth currentMonth) {
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            daysPanel.add(label);
        }

        int daysInMonth = currentMonth.lengthOfMonth();
        LocalDate firstOfMonth = currentMonth.atDay(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;

        for (int i = 1; i <= dayOfWeek; i++) {
            JLabel label = new JLabel("");
            daysPanel.add(label);
        }

        for (int day = 1; day <= daysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            daysPanel.add(label);
        }
    }
    
    private void removeCalendarPanel() {
        rightPanel.remove(calendarPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ELibrarySoft().setVisible(true);
        });
    }
}
