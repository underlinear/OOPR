package toadinone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.Random;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ELibrarySoft extends JFrame {

    private JPanel rightPanel;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JPanel daysPanel;
    private JPanel sysManagementPanel = new JPanel();
    private JPanel transactionPanel = new JPanel();
    private JPanel shelvesPanel = new JPanel();

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
        
        logoutButton.addActionListener(e -> {
        	dispose();
        	new Login();
        	return;
        });
        // Create the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(43, 57, 72));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel navLabel = new JLabel("Main Navigation");
        navLabel.setForeground(new Color(224, 224, 224));
        navLabel.setFont(new Font("Arial", Font.BOLD, 24));
        navLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        leftPanel.add(navLabel);

        leftPanel.add(Box.createVerticalStrut(30));

        // Add individual buttons with icons
        leftPanel.add(createIconButton("/Icon/DASHBOARD.png", "Dashboard", e -> dashboard()));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createIconButton("/Icon/SM1.png", "System Management", e -> systemManagement()));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createIconButton("/Icon/TRANSACTION.png", "Transaction", e -> transaction()));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createIconButton("/Icon/CALENDAR_1.png", "Calendar", e -> calendar()));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createIconButton("/Icon/SHELVE.png", "My Shelves", e -> shelves()));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createIconButton("/Icon/PERSONAL-INFORMATION.png", "Personal Details", e -> personalDetails()));

        // Create the right panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new CardLayout());

        // Add panels to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.CENTER);
        
        calendarPanel = new JPanel(new BorderLayout());
        rightPanel.add(calendarPanel, "Calendar");
    }
    
    private JPanel createIconButton(String iconPath, String text, ActionListener actionListener) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(43, 57, 72));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        panel.add(iconLabel);

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setBackground(new Color(43, 57, 72));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(actionListener);

        panel.add(button);
        return panel;
    }

    private void dashboard(){
        rightPanel.removeAll();
        // Add your dashboard component
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Dashboard"));
        rightPanel.add(dashboardPanel, "Dashboard");
        ((CardLayout) rightPanel.getLayout()).show(rightPanel, "Dashboard");
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void systemManagement(){
        rightPanel.removeAll();
        sysManagementPanel.removeAll();
        sysManagementPanel.setSize(774, 620);
        sysManagementPanel.setBackground(new Color(245, 245, 220));
        sysManagementPanel.setLayout(null);

        JLabel title = new JLabel("SYSTEM MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(40, 55, 300, 20);

        JPanel todayTransaction = new JPanel();
        todayTransaction.setBackground(Color.WHITE);
        todayTransaction.setBounds(40,90,700,125);
        todayTransaction.setLayout(null);

        JLabel systemManagement = new JLabel("Today's Transactions");
        systemManagement.setFont(new Font("Arial", Font.BOLD, 16));
        systemManagement.setBounds(10, 0, 700, 20);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10,20,690,105);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            JLabel label = new JLabel("Transaction " + (i+1) + ": $" + (random.nextInt(1000) + 1));
            contentPanel.add(label);
        }
        scrollPane.setViewportView(contentPanel);

        todayTransaction.add(systemManagement);
        todayTransaction.add(scrollPane);


        JPanel allTransaction = new JPanel();
        allTransaction.setBackground(Color.WHITE);
        allTransaction.setBounds(40,240,700,125);
        allTransaction.setLayout(null);

        JLabel allTitle = new JLabel("All Transactions");
        allTitle.setFont(new Font("Arial", Font.BOLD, 16));
        allTitle.setBounds(10, 0, 700, 20);

        JScrollPane allScrollPane = new JScrollPane();
        allScrollPane.setBounds(10,20,690,105);
        allScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allScrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        JPanel allContentPanel = new JPanel();
        allContentPanel.setLayout(new BoxLayout(allContentPanel, BoxLayout.Y_AXIS));
        Random allRandom = new Random();
        for (int i = 0; i < 20; i++) {
            JLabel label = new JLabel("Transaction " + (i+1) + ": $" + (allRandom.nextInt(1000) + 1));
            allContentPanel.add(label);
        }
        allScrollPane.setViewportView(allContentPanel);

        allTransaction.add(allScrollPane);
        allTransaction.add(allTitle);

        JPanel editCatalogue = new JPanel();
        editCatalogue.setBackground(Color.WHITE);
        editCatalogue.setBounds(40,390,700,160);
        editCatalogue.setLayout(null);

        JLabel editCatalogueTitle = new JLabel("Edit Catalogue");
        editCatalogueTitle.setFont(new Font("Arial", Font.BOLD, 16));
        editCatalogueTitle.setBounds(10, 0, 700, 20);

        JScrollPane editCatalogueScrollPane = new JScrollPane();
        editCatalogueScrollPane.setBounds(10,20,690,90);
        editCatalogueScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editCatalogueScrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        JPanel editCatalogueContentPanel = new JPanel();
        editCatalogueContentPanel.setLayout(new BoxLayout(editCatalogueContentPanel, BoxLayout.Y_AXIS));

        // Adding checkboxes for each item in the edit catalogue
        for (int i = 0; i < 10; i++) {
            JCheckBox checkBox = new JCheckBox("Item " + (i+1));
            editCatalogueContentPanel.add(checkBox);
        }
        editCatalogueScrollPane.setViewportView(editCatalogueContentPanel);

        editCatalogue.add(editCatalogueScrollPane);
        editCatalogue.add(editCatalogueTitle);

        JButton addButton = new JButton("Add");
        addButton.setFocusPainted(false);
        addButton.setBounds(260, 120, 80, 30);
        addButton.addActionListener(e -> {
                // Open a dialog for input
                JTextField nameField = new JTextField();
                JTextField authorField = new JTextField();
                JTextField dateField = new JTextField();
                JTextField genreField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Author:"));
                panel.add(authorField);
                panel.add(new JLabel("Date Published:"));
                panel.add(dateField);
                panel.add(new JLabel("Genre:"));
                panel.add(genreField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Book",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    String author = authorField.getText();
                    String date = dateField.getText();
                    String genre = genreField.getText();

                    JLabel label = new JLabel(name + " by " + author + ", " + date + ", Genre: " + genre);
                    editCatalogueContentPanel.add(label);
                    editCatalogueScrollPane.revalidate();
                    editCatalogueScrollPane.repaint();
                }
            });
            editCatalogue.add(addButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.setFocusPainted(false);
            deleteButton.setBounds(370, 120, 80, 30);
            deleteButton.addActionListener(e -> {
            Component[] components = editCatalogueContentPanel.getComponents();
            StringBuilder message = new StringBuilder("Are you sure you want to delete these books:\n");
            boolean atLeastOneChecked = false;
            for (Component component : components) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        message.append(checkBox.getText()).append("\n");
                        atLeastOneChecked = true;
                    }
                }
            }
            if (atLeastOneChecked) {
                int result = JOptionPane.showConfirmDialog(null, message.toString(), "Confirm Deletion",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    for (int i = components.length - 1; i >= 0; i--) {
                        Component component = components[i];
                        if (component instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) component;
                            if (checkBox.isSelected()) {
                                editCatalogueContentPanel.remove(i);
                            }
                        }
                    }
                    editCatalogueScrollPane.revalidate();
                    editCatalogueScrollPane.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select at least one book to delete.", "No Selection",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        editCatalogue.add(deleteButton);

        sysManagementPanel.add(todayTransaction);
        sysManagementPanel.add(allTransaction);
        sysManagementPanel.add(editCatalogue);
        sysManagementPanel.add(title);

        rightPanel.add(sysManagementPanel, "System Management");
        ((CardLayout) rightPanel.getLayout()).show(rightPanel, "System Management");
        
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void transaction() {
        rightPanel.removeAll();
        transactionPanel.removeAll();

        transactionPanel.setLayout(null);

        JPanel todaysTransaction = new JPanel();
        todaysTransaction.setBackground(Color.WHITE);
        todaysTransaction.setBounds(35, 50, 325, 500);
        todaysTransaction.setLayout(null);

        JLabel todaysTitle = new JLabel("Transactions");
        todaysTitle.setFont(new Font("Arial", Font.BOLD, 24));
        todaysTitle.setBounds(10, 15, 300, 50);

        JScrollPane todayScrollPane = new JScrollPane();
        todayScrollPane.setBounds(10, 70, 307, 370);
        todayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        todayScrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        JPanel todayContentPanel = new JPanel();
        todayContentPanel.setLayout(new BoxLayout(todayContentPanel, BoxLayout.Y_AXIS));
        // Fetch and display transactions from the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management", "root", "root");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions")) {
            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transaction_id");
                boolean isPaid = resultSet.getBoolean("is_paid");
                Date dateOfTransaction = resultSet.getDate("date_of_transaction");
                String studentId = resultSet.getString("student_number");
                double amountToBePaid = resultSet.getDouble("amount_to_be_paid");
                
                if(isPaid) {
                	JCheckBox checkBox = new JCheckBox("Student Number: " + studentId + "\t , Amount: " + amountToBePaid);
                	todayContentPanel.add(checkBox);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        todayScrollPane.setViewportView(todayContentPanel);

        todaysTransaction.add(todaysTitle);
        todaysTransaction.add(todayScrollPane);
        
        JPanel pendingTransaction = new JPanel();
        pendingTransaction.setBackground(Color.WHITE);
        pendingTransaction.setBounds(415,50,325,500);
        pendingTransaction.setLayout(null);

        JLabel pendingTitle = new JLabel("Pending Transactions");
        pendingTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pendingTitle.setBounds(10, 15, 307, 50);

        JScrollPane pendingScrollPane = new JScrollPane();
        pendingScrollPane.setBounds(10,70,307,370);
        pendingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pendingScrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        JPanel pendingContentPanel = new JPanel();
        pendingContentPanel.setLayout(new BoxLayout(pendingContentPanel, BoxLayout.Y_AXIS));
        // Fetch and display transactions from the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management", "root", "root");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions")) {
            while (resultSet.next()) {
                //int transactionId = resultSet.getInt("transaction_id");
                boolean isPaid = resultSet.getBoolean("is_paid");
                //Date dateOfTransaction = resultSet.getDate("date_of_transaction");
                String studentId = resultSet.getString("student_number");
                double amountToBePaid = resultSet.getDouble("amount_to_be_paid");
                
                if(!isPaid) {
                	JCheckBox checkBox = new JCheckBox("Student Number: " + studentId + "\t , Amount: " + amountToBePaid);
                	pendingContentPanel.add(checkBox);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        pendingScrollPane.setViewportView(pendingContentPanel);

        JButton confirmPaymentButton = new JButton("Confirm Payment");
        confirmPaymentButton.setFocusPainted(false);
        confirmPaymentButton.setBounds(10, 460, 150, 30);
        confirmPaymentButton.addActionListener(e -> {
            StringBuilder confirmedTransactions = new StringBuilder("Do you want to confirm these transactions?\n");
            boolean atLeastOneChecked = false;
            for (Component component : pendingContentPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        confirmedTransactions.append(checkBox.getText()).append("\n");
                        atLeastOneChecked = true;
                    }
                }
            }

            if (atLeastOneChecked) {
                int result = JOptionPane.showConfirmDialog(null, confirmedTransactions.toString(), "Confirm Payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    for (int i = pendingContentPanel.getComponentCount() - 1; i >= 0; i--) {
                        Component component = pendingContentPanel.getComponent(i);
                        if (component instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) component;
                            if (checkBox.isSelected()) {
                                pendingContentPanel.remove(i);
                                todayContentPanel.add(component);
                                
                                String student_number = checkBox.getText().split(" ")[2].trim();
  
                                database.TransactionsHandler.updateIsPaidStatus(student_number);
                                
                                repaint();
                                revalidate();
                            }
                        }
                    }
                    pendingContentPanel.revalidate();
                    pendingContentPanel.repaint();
                } else {
                    for (Component component : pendingContentPanel.getComponents()) {
                        if (component instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) component;
                            if (checkBox.isSelected()) {
                                checkBox.setSelected(false);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select at least one transaction to confirm.", "No Selection", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        pendingTransaction.add(pendingTitle);
        pendingTransaction.add(pendingScrollPane);
        pendingTransaction.add(confirmPaymentButton);
        
        transactionPanel.add(todaysTransaction);
        transactionPanel.add(pendingTransaction);
        
        rightPanel.add(transactionPanel, "Transaction");
        ((CardLayout) rightPanel.getLayout()).show(rightPanel, "Transaction");
        
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void shelves() {
        rightPanel.removeAll();

        // Create the main panel to hold the shelves
        shelvesPanel.setBackground(Color.WHITE);
        shelvesPanel.setBounds(50, 50, 900, 1000);
        shelvesPanel.setLayout(new BoxLayout(shelvesPanel, BoxLayout.Y_AXIS));

        // Main Shelf Panel 1
        JPanel mainShelf1 = new JPanel();
        mainShelf1.setBackground(Color.WHITE);
        mainShelf1.setLayout(new BoxLayout(mainShelf1, BoxLayout.Y_AXIS));

        JScrollPane mainScrollPane1 = new JScrollPane(mainShelf1);
        mainScrollPane1.setPreferredSize(new Dimension(400, 500));
        mainScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane1.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        // Inner Panels for Main Shelf 1
        for (int i = 0; i < 4; i++) {
            JPanel innerPanel = new JPanel();
            innerPanel.setBackground(Color.LIGHT_GRAY);
            innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
            for (int j = 0; j < 5; j++) {
                innerPanel.add(new JLabel("Book " + (i * 5 + j + 1)));
            }

            JScrollPane innerScrollPane = new JScrollPane(innerPanel);
            innerScrollPane.setPreferredSize(new Dimension(350, 100));
            innerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            innerScrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

            mainShelf1.add(innerScrollPane);
            mainShelf1.add(Box.createVerticalStrut(10));
        }

        // Main Shelf Panel 2
        JPanel mainShelf2 = new JPanel();
        mainShelf2.setBackground(Color.WHITE);
        mainShelf2.setLayout(new BoxLayout(mainShelf2, BoxLayout.Y_AXIS));

        JScrollPane mainScrollPane2 = new JScrollPane(mainShelf2);
        mainScrollPane2.setPreferredSize(new Dimension(400, 500));
        mainScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane2.setBorder(new LineBorder(new Color(43, 57, 72), 1));

        // Inner Panels for Main Shelf 2
        for (int i = 0; i < 4; i++) {
            JPanel innerPanel = new JPanel();
            innerPanel.setBackground(Color.LIGHT_GRAY);
            innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
            for (int j = 0; j < 5; j++) {
                innerPanel.add(new JLabel("Book " + (i * 5 + j + 1)));
            }

            JScrollPane innerScrollPane = new JScrollPane(innerPanel);
            innerScrollPane.setPreferredSize(new Dimension(350, 100));
            innerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            innerScrollPane.setBorder(new LineBorder(new Color(43, 57, 72), 1));

            mainShelf2.add(innerScrollPane);
            mainShelf2.add(Box.createVerticalStrut(10));
        }

        // Add main scroll panes to shelvesPanel
        shelvesPanel.add(mainScrollPane1);
        shelvesPanel.add(Box.createVerticalStrut(20));
        shelvesPanel.add(mainScrollPane2);

        // Add shelvesPanel to rightPanel
        rightPanel.add(shelvesPanel, "Shelves");
        ((CardLayout) rightPanel.getLayout()).show(rightPanel, "Shelves");

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void personalDetails(){
        rightPanel.removeAll();
        // Add your personal details component
        JPanel personalDetailsPanel = new JPanel();
        personalDetailsPanel.add(new JLabel("Personal Details"));
        rightPanel.add(personalDetailsPanel, "PersonalDetails");
        ((CardLayout) rightPanel.getLayout()).show(rightPanel, "PersonalDetails");
        rightPanel.revalidate();
        rightPanel.repaint();
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

        // Add calendar panel to right panel and show it
        rightPanel.add(calendarPanel, "Calendar");
        ((CardLayout) rightPanel.getLayout()).show(rightPanel, "Calendar");
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