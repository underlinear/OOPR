package toadinone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.YearMonth;

public class System_Management extends JFrame {

    // Variables declaration
    private JButton btnCalendar, btnDashboard, btnLogout, btnPDetails, btnSManagement, btnShelves, btnTransaction;
    private JLabel lblCalIcon, lblCalendarIcon, lblNavText, lblPDIcon, lblSMIcon, lblShelvesIcon, lblTextDashboard, lblTransactionIcon, lblELibraryText;
    private JPanel mainPanel, navPanel, logoutPanel, elibraryPanel, dstCmpPanel, mainContentPanel, systemManagementPanel;

    public System_Management() {
        initComponents();
    }
    

    private void initComponents() {
        // Initialize components
        setVisible(true);
        setResizable(false);
        mainPanel = new JPanel();
        elibraryPanel = new JPanel();
        lblELibraryText = new JLabel();
        logoutPanel = new JPanel();
        btnLogout = new JButton();
        navPanel = new JPanel();
        lblNavText = new JLabel();
        dstCmpPanel = new JPanel();
        btnDashboard = new JButton();
        btnSManagement = new JButton();
        btnTransaction = new JButton();
        btnCalendar = new JButton();
        btnShelves = new JButton();
        btnPDetails = new JButton();
        lblCalendarIcon = new JLabel();
        lblSMIcon = new JLabel();
        lblTransactionIcon = new JLabel();
        lblCalIcon = new JLabel();
        lblShelvesIcon = new JLabel();
        lblPDIcon = new JLabel();
        mainContentPanel = new JPanel();
        lblTextDashboard = new JLabel();
        systemManagementPanel = new JPanel();

        // Set default close operation
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Main Panel
        mainPanel.setBackground(new Color(204, 204, 204));
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setLayout(null);

        // eLibrary Panel
        elibraryPanel.setBackground(new Color(107, 147, 35));
        elibraryPanel.setLayout(null);
        lblELibraryText.setFont(new Font("SansSerif", Font.BOLD, 19));
        lblELibraryText.setForeground(new Color(255, 255, 255));
        lblELibraryText.setText("eLibrary Soft");
        lblELibraryText.setBounds(50, 10, 130, 30);

        elibraryPanel.add(lblELibraryText);
        mainPanel.add(elibraryPanel);
        elibraryPanel.setBounds(0, 0, 230, 50);

        // Logout Panel
        logoutPanel.setBackground(new Color(107, 173, 35));
        logoutPanel.setLayout(null);
        btnLogout.setBackground(new Color(107, 173, 35));
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setText("LOG OUT");
        btnLogout.setBorder(null);
        btnLogout.setBounds(460, 10, 80, 30);

        logoutPanel.add(btnLogout);
        mainPanel.add(logoutPanel);
        logoutPanel.setBounds(230, 0, 570, 50);

        // Navigation Panel
        navPanel.setBackground(new Color(28, 35, 42));
        navPanel.setLayout(null);
        lblNavText.setFont(new Font("SansSerif", 0, 17));
        lblNavText.setForeground(new Color(153, 153, 153));
        lblNavText.setText("MAIN NAVIGATION");
        lblNavText.setBounds(50, 10, 150, 20);

        navPanel.add(lblNavText);
        mainPanel.add(navPanel);
        navPanel.setBounds(0, 50, 230, 40);

        // Dashboard, System Management, and Transaction Panel (dstCmpPanel)
          dstCmpPanel.setBackground(new java.awt.Color(33, 46, 48));

        btnDashboard.setBackground(new java.awt.Color(33, 46, 48));
        btnDashboard.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); 
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDashboard.setFocusPainted(false);

        btnSManagement.setBackground(new java.awt.Color(33, 46, 48));
        btnSManagement.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); 
        btnSManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnSManagement.setText("System Management");
        btnSManagement.setBorder(null);

        btnTransaction.setBackground(new java.awt.Color(33, 46, 48));
        btnTransaction.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); 
        btnTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnTransaction.setText("Transaction");
        btnTransaction.setBorder(null);
        btnTransaction.addActionListener(evt -> transactionActionPerformed(evt));

        btnCalendar.setBackground(new java.awt.Color(33, 46, 48));
        btnCalendar.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); 
        btnCalendar.setForeground(new java.awt.Color(255, 255, 255));
        btnCalendar.setText("Calendar");
        btnCalendar.setBorder(null);
        btnCalendar.addActionListener(e -> {
        	dispose();
        	ELibrarySoft elibsoft = new ELibrarySoft();
        	elibsoft.calendar();
        });

        btnShelves.setBackground(new java.awt.Color(33, 46, 48));
        btnShelves.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); 
        btnShelves.setForeground(new java.awt.Color(255, 255, 255));
        btnShelves.setText("My Shelves");
        btnShelves.setBorder(null);

        btnPDetails.setBackground(new java.awt.Color(33, 46, 48));
        btnPDetails.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); 
        btnPDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnPDetails.setText("Personal Details");
        btnPDetails.setBorder(null);

        lblCalendarIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/DASHBOARD.png")));
        lblSMIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/SM1.png")));
        lblTransactionIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/TRANSACTION.png")));
        lblCalIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/CALENDAR_1.png")));
        lblShelvesIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/SHELVE.png")));
        lblPDIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/PERSONAL-INFORMATION.png")));

        GroupLayout dstCmpPanelLayout = new GroupLayout(dstCmpPanel);
        dstCmpPanel.setLayout(dstCmpPanelLayout);
        dstCmpPanelLayout.setHorizontalGroup(
            dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dstCmpPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCalendarIcon)
                    .addComponent(lblSMIcon)
                    .addComponent(lblTransactionIcon)
                    .addComponent(lblCalIcon)
                    .addComponent(lblShelvesIcon)
                    .addComponent(lblPDIcon))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnDashboard, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransaction, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSManagement, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPDetails, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShelves, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalendar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        dstCmpPanelLayout.setVerticalGroup(
            dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dstCmpPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblCalendarIcon)
                    .addComponent(btnDashboard, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblSMIcon)
                    .addComponent(btnSManagement, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblTransactionIcon)
                    .addComponent(btnTransaction, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblCalIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalendar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblShelvesIcon)
                    .addComponent(btnShelves, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dstCmpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPDIcon)
                    .addComponent(btnPDetails, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        
        mainPanel.add(dstCmpPanel);
        dstCmpPanel.setBounds(0, 90, 230, 410);

        // Main Content Panel
        mainContentPanel.setBackground(new Color(204, 204, 204));
        mainContentPanel.setLayout(new CardLayout());

        // System Management Panel
        systemManagementPanel.setBackground(new Color(204, 204, 204));
        systemManagementPanel.setLayout(null);

        JLabel lblSysManagement = new JLabel("SYSTEM MANAGEMENT");
        lblSysManagement.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblSysManagement.setForeground(new Color(51, 51, 51));
        lblSysManagement.setBounds(30, 20, 250, 30);

        systemManagementPanel.add(lblSysManagement);

        JPanel todaysTransactionsPanel = createWhitePanel("Today's Transactions", 30, 60, 500, 100);
        systemManagementPanel.add(todaysTransactionsPanel);

        JPanel reservedDataPanel = createTransactionPanel("Reserved Data");
        JPanel borrowedDataPanel = createTransactionPanel("Borrowed Data");
        JPanel returnedDataPanel = createTransactionPanel("Returned Data");
        JPanel violationDataPanel = createTransactionPanel("Violation Data");

        todaysTransactionsPanel.add(reservedDataPanel);
        todaysTransactionsPanel.add(borrowedDataPanel);
        todaysTransactionsPanel.add(returnedDataPanel);
        todaysTransactionsPanel.add(violationDataPanel);

        JPanel allTransactionsPanel = createWhitePanel("All Transactions", 30, 180, 500, 100);
        systemManagementPanel.add(allTransactionsPanel);

        JPanel allReservedDataPanel = createTransactionPanel("Reserved Data");
        JPanel allBorrowedDataPanel = createTransactionPanel("Borrowed Data");
        JPanel allReturnedDataPanel = createTransactionPanel("Returned Data");
        JPanel allViolationDataPanel = createTransactionPanel("Violation Data");

        allTransactionsPanel.add(allReservedDataPanel);
        allTransactionsPanel.add(allBorrowedDataPanel);
        allTransactionsPanel.add(allReturnedDataPanel);
        allTransactionsPanel.add(allViolationDataPanel);

        JPanel editCataloguePanel = createWhitePanel("Edit Catalogue", 30, 300, 500, 100);
        systemManagementPanel.add(editCataloguePanel);

        JPanel booksPanel = createTransactionPanel("Books");
        JPanel userPanel = createTransactionPanel("User");

        editCataloguePanel.add(booksPanel);
        editCataloguePanel.add(userPanel);

        mainContentPanel.add(systemManagementPanel, "System Management");
        mainContentPanel.add(lblTextDashboard, "Dashboard");

        mainPanel.add(mainContentPanel);
        mainContentPanel.setBounds(230, 50, 570, 450);

        // Add main panel to the frame
        getContentPane().add(mainPanel);

        // Pack and set location
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createWhitePanel(String title, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setBackground(Color.WHITE);
        panel.setBounds(x, y, width, height);
        panel.setLayout(new GridLayout(1, 4, 10, 10));
        return panel;
    }

    private JPanel createTransactionPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel.add(label, BorderLayout.CENTER);

        JButton button = new JButton("More info");
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setContentAreaFilled(false); // Make the button transparent
        panel.add(button, BorderLayout.SOUTH);

        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/Icon/TOAD.png")), SwingConstants.CENTER);
        panel.add(logo, BorderLayout.NORTH);

        return panel;
    }

    public static void main(String args[]) {
        new System_Management();
    }

    private void transactionActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



