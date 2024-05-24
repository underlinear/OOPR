package toadinone;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.BookRetrieval;
import database.User;
import database.Book;
import database.Borrow;
import database.Return;
import database.VisitPurpose;

public class Student_Home_Page extends JFrame {
    private JLabel dateLabel;
    private JLabel timeLabel;
    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeFormatter;
    private JPanel currentPanel = null;

    public Student_Home_Page() {
    	
    	if(!User.isLoggedIn) {
    		this.dispose();
    		new Login();
    		return;
    	}
    	
        this.setLayout(null);
        this.setSize(1000, 700);
        this.setResizable(false);
        getContentPane().setBackground(Color.GREEN);
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 150, 0));
        topPanel.setBounds(0, 50, 1000, 85);
        topPanel.setLayout(null);

        JLabel icon = new JLabel();
        ImageIcon profile = new ImageIcon("profile.png");
        icon.setIcon(profile);
        icon.setBounds(0, 0, 50, 50);

        JLabel studentName = new JLabel(User.name);
        studentName.setFont(new Font("Arial", Font.BOLD, 26));
        studentName.setForeground(Color.WHITE);
        studentName.setBounds(130, 7, 500, 50);

        
        JLabel studentSection = new JLabel();
		try {
			studentSection.setText(User.getProgramNameByStudentNumber(User.studentNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			studentSection.setText("Something went wrong...");
		}
        studentSection.setFont(new Font("Arial", Font.BOLD, 18));
        studentSection.setForeground(new Color(245, 245, 220));
        studentSection.setBounds(130, 40, 500, 50);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setBounds(580, 30, 200, 30);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(700, 30, 200, 30);

        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        JLabel sign = new JLabel("|");
        sign.setFont(new Font("Arial", Font.BOLD, 30));
        sign.setForeground(Color.WHITE);
        sign.setBounds(820, 18, 50, 50);
        
        JButton logOut = new JButton("Log Out");
        logOut.setFont(new Font("Arial", Font.BOLD, 18));
        logOut.setForeground(Color.WHITE);
        logOut.setBackground(new Color(0, 150, 0));
        logOut.setFocusPainted(false);
        logOut.setBorderPainted(false);
        logOut.setBounds(830, 18, 150, 50);
        
        logOut.addActionListener(e -> {
        	this.dispose();
        	User.isLoggedIn = false;
        	new Login();
        	return;
        });
        
        updateDateTime();
        
        topPanel.add(logOut);
        topPanel.add(sign);
        topPanel.add(studentSection);
        topPanel.add(studentName);
        topPanel.add(icon);
        topPanel.add(dateLabel);
        topPanel.add(timeLabel);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 135, 300, 500);
        leftPanel.setLayout(null);

        JButton borrowBooks = new JButton("Borrow Books");
        borrowBooks.setBounds(50, 70, 200, 50);
        borrowBooks.setFocusPainted(false);

        JButton returnBooks = new JButton("Return Books");
        returnBooks.setBounds(50, 160, 200, 50);
        returnBooks.setFocusPainted(false);

        JButton reserveBooks = new JButton("Reserve Books");
        reserveBooks.setBounds(50, 260, 200, 50);
        reserveBooks.setFocusPainted(false);

        JButton visitPurpose = new JButton("Visit Purpose");
        visitPurpose.setBounds(50, 360, 200, 50);
        visitPurpose.setFocusPainted(false);
        
        borrowBooks.addActionListener(e -> {
            borrowBook();
        });
        
        returnBooks.addActionListener(e -> {
            returnBook();
        });
        
        reserveBooks.addActionListener(e -> {
            reserveBooks();
        });
        
        visitPurpose.addActionListener(e -> {
            visitPurpose();
        });

        leftPanel.add(borrowBooks);
        leftPanel.add(returnBooks);
        leftPanel.add(reserveBooks);
        leftPanel.add(visitPurpose);

        this.add(topPanel);
        this.add(leftPanel);
        this.setVisible(true);

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
    }

    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        dateLabel.setText(dateFormatter.format(now));
        timeLabel.setText(timeFormatter.format(now));
    }
    
    private void borrowBook(){
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        
        JPanel borrowBookPanel = new JPanel();
        borrowBookPanel.setBackground(new Color(245, 245, 220));
        borrowBookPanel.setBounds(300, 135, 700, 500);
        borrowBookPanel.setLayout(null);

        JTextField searchBar = new JTextField();
        searchBar.setBounds(20, 70, 200, 30);

        JButton searchButton = new JButton("\uD83D\uDD0D");
        searchButton.setBounds(230, 70, 30, 30);
        searchButton.setFocusPainted(false);

    	BookRetrieval search = new BookRetrieval();
    	
    	String[] columnNames = {"", "NAME OF BOOKS", "Author", "Date", "Category"};
    	List<Book> books = null;
		try {
			books = search.fetchBooksFromDatabase();
		} catch (SQLException e1) {
			// err
			e1.printStackTrace();
		}
		
		int numBooks = books.size();
    	Object[][] data = new Object[numBooks][5];
    	
    	for(int i = 0; i < books.size(); i++) {
    		data[i][0] = false;
    		data[i][1] = books.get(i).getTitle();
    		data[i][2] = books.get(i).getAuthor();
    		data[i][3] = books.get(i).getDatePublished();
    		data[i][4] = books.get(i).getCategory();
    	}
    	
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                    case 2:
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; 
            }
        };
        
        String[] categories = {"ALL", "Programming", "Filipino Literature", "Fiction"};
        JComboBox<String> categoryComboBox
         = new JComboBox<>(categories);
        categoryComboBox.setBounds(550, 70, 120, 30);

        JButton borrowButton = new JButton("BORROW");
        borrowButton.setBounds(550, 330, 100, 30);

        borrowButton.addActionListener(e -> {
            JPanel selectedDataPanel = new JPanel();
            selectedDataPanel.setLayout(new GridLayout(0, 1));
            
            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean isChecked = (Boolean) model.getValueAt(i, 0);
                if (isChecked != null && isChecked) {
                    String bookName = (String) model.getValueAt(i, 1);
                    String author = (String) model.getValueAt(i, 2);
                    String date = (String) model.getValueAt(i, 3);
                    
                    JLabel nameLabel = new JLabel("Book Name: " + bookName);
                    JLabel authorLabel = new JLabel("Author: " + author);
                    JLabel dateLabel = new JLabel("Date: " + date);
                    
                    selectedDataPanel.add(nameLabel);
                    selectedDataPanel.add(authorLabel);
                    selectedDataPanel.add(dateLabel);
                    
                    Borrow b = new Borrow(bookName);
                    if(!b.borrowBook()) {
                    	JOptionPane.showMessageDialog(null, "This book is unavailable for now.", "Unavailable", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            
            JOptionPane.showMessageDialog(null, new JScrollPane(selectedDataPanel), "Selected Books", JOptionPane.PLAIN_MESSAGE);
        });

        JTable table = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 650, 200);

        searchButton.addActionListener(e -> {
            String searchQuery = searchBar.getText().trim();
            String selectedCategory = (String) categoryComboBox.getSelectedItem();

            if (searchQuery.isEmpty() && selectedCategory.equals("ALL")) {
                sorter.setRowFilter(null);
            } else {
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                if (!searchQuery.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + searchQuery, 1, 2));
                }
                
                if (!selectedCategory.equals("ALL")) {
                    filters.add(RowFilter.regexFilter("(?i)" + selectedCategory, 4));
                }

                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
                sorter.setRowFilter(combinedFilter);
            }
        });
        
        borrowBookPanel.add(borrowButton);

        this.add(borrowBookPanel);

        borrowBookPanel.add(searchBar);
        borrowBookPanel.add(searchButton);
        borrowBookPanel.add(scrollPane);
        borrowBookPanel.add(categoryComboBox);
        borrowBookPanel.add(borrowButton);

        this.add(borrowBookPanel);
        this.revalidate();
        this.repaint();   
        
        currentPanel = borrowBookPanel;
    }
    
    private String getSelectedBooks(DefaultTableModel model) {
        StringBuilder selectedBooks = new StringBuilder("");
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean isChecked = (Boolean) model.getValueAt(i, 0);
            if (isChecked != null && isChecked) {
                String bookName = (String) model.getValueAt(i, 1);
                selectedBooks.append(bookName).append(", ");
            }
        }
        if (selectedBooks.length() > 0) {
            selectedBooks.setLength(selectedBooks.length() - 2);
        }
        return selectedBooks.toString();
    }
    
    private void returnBook() {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        JPanel returnBookPanel = new JPanel();
        returnBookPanel.setBackground(new Color(245, 245, 220));
        returnBookPanel.setBounds(300, 135, 700, 500);
        returnBookPanel.setLayout(null);

        JTextField searchBar = new JTextField();
        searchBar.setBounds(20, 70, 200, 30);

        JButton searchButton = new JButton("\uD83D\uDD0D");
        searchButton.setBounds(230, 70, 30, 30);
        searchButton.setFocusPainted(false);
        
        Return search = new Return();

        String[] columnNames = {"", "NAME OF BOOKS", "Author", "Date", "Category"};
        List<Book> books = search.getBooksNotReturnedByUser(User.userID);
		int numBooks = books.size();
    	Object[][] data = new Object[numBooks][5];
    	
    	for(int i = 0; i < books.size(); i++) {
    		data[i][0] = false;
    		data[i][1] = books.get(i).getTitle();
    		data[i][2] = books.get(i).getAuthor();
    		data[i][3] = books.get(i).getDatePublished();
    		data[i][4] = books.get(i).getCategory();
    	}

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                    case 2:
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        JTable table = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 650, 200);

        String[] categories = {"ALL", "Programming", "Filipino Literature", "Fiction"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setBounds(550, 70, 120, 30);
        
        searchButton.addActionListener(e -> {
            String searchQuery = searchBar.getText().trim();
            String selectedCategory = (String) categoryComboBox.getSelectedItem();

            if (searchQuery.isEmpty() && selectedCategory.equals("ALL")) {
                sorter.setRowFilter(null);
            } else {
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                if (!searchQuery.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + searchQuery, 1, 2));
                }
                
                if (!selectedCategory.equals("ALL")) {
                    filters.add(RowFilter.regexFilter("(?i)" + selectedCategory, 4));
                }

                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
                sorter.setRowFilter(combinedFilter);
            }
        });

        JButton returnButton = new JButton("RETURN");
        returnButton.setBounds(550, 330, 100, 30);
        returnButton.addActionListener(e -> {
            int rowCount = model.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                Boolean isSelected = (Boolean) model.getValueAt(i, 0);
                
                if (isSelected != null && isSelected) {
                    String title = (String) model.getValueAt(i, 1);
                    Return r = new Return();
                    r.returnBook(title);
                }
            }
            List<Book> updatedBooks = search.getBooksNotReturnedByUser(User.userID);
            model.setRowCount(0); 

            for (Book book : updatedBooks) {
                Object[] rowData = {false, book.getTitle(), book.getAuthor(), book.getDatePublished(), book.getCategory()};
                model.addRow(rowData);
            }

            table.repaint();
        });



        returnBookPanel.add(searchBar);
        returnBookPanel.add(searchButton);
        returnBookPanel.add(scrollPane);
        returnBookPanel.add(categoryComboBox);
        returnBookPanel.add(returnButton); 

        this.add(returnBookPanel);
        this.revalidate();
        this.repaint();
        
        currentPanel = returnBookPanel;
    }
    
    private void reserveBooks() {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }

        JPanel reserveBooksPanel = new JPanel();
        reserveBooksPanel.setBackground(new Color(245, 245, 220));
        reserveBooksPanel.setBounds(300, 135, 700, 500);
        reserveBooksPanel.setLayout(null);

        JTextField searchBar = new JTextField();
        searchBar.setBounds(20, 70, 200, 30);

        JButton searchButton = new JButton("\uD83D\uDD0D");
        searchButton.setBounds(230, 70, 30, 30);
        searchButton.setFocusPainted(false);
        
        BookRetrieval br = new BookRetrieval();
        List<Book> books = null;
		try {
			books = br.fetchBooksFromDatabase();
		} catch (SQLException e1) {
			// err
			e1.printStackTrace();
		}
		
		int numBooks = books.size();
        String[] columnNames = {"", "NAME OF BOOKS", "Author", "Date", "Category"};
        Object[][] data = new Object[numBooks][5];
        
        for(int i = 0; i < books.size(); i++) {
    		data[i][0] = false;
    		data[i][1] = books.get(i).getTitle();
    		data[i][2] = books.get(i).getAuthor();
    		data[i][3] = books.get(i).getDatePublished();
    		data[i][4] = books.get(i).getCategory();
    	}

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                    case 2:
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; 
            }
        };

        JTable table = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 650, 200);

        String[] categories = {"ALL", "Programming", "Filipino Literature", "Fiction"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setBounds(550, 70, 120, 30);
        
        searchButton.addActionListener(e -> {
            String searchQuery = searchBar.getText().trim();
            String selectedCategory = (String) categoryComboBox.getSelectedItem();

            if (searchQuery.isEmpty() && selectedCategory.equals("ALL")) {
                sorter.setRowFilter(null);
            } else {
                List<RowFilter<Object, Object>> filters = new ArrayList<>();

                if (!searchQuery.isEmpty()) {
                    filters.add(RowFilter.regexFilter("(?i)" + searchQuery, 1, 2));
                }
                
                if (!selectedCategory.equals("ALL")) {
                    filters.add(RowFilter.regexFilter("(?i)" + selectedCategory, 4));
                }

                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
                sorter.setRowFilter(combinedFilter);
            }
        });

        JButton reserveButton = new JButton("RESERVE");
        reserveButton.setBounds(550, 330, 100, 30);

        reserveButton.addActionListener(e -> {
            JPanel selectedDataPanel = new JPanel();
            selectedDataPanel.setLayout(new GridLayout(0, 1));

            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean isChecked = (Boolean) model.getValueAt(i, 0);
                if (isChecked != null && isChecked) {
                    String bookName = (String) model.getValueAt(i, 1);
                    String author = (String) model.getValueAt(i, 2);
                    String date = (String) model.getValueAt(i, 3);

                    JLabel nameLabel = new JLabel("Book Name: " + bookName);
                    JLabel authorLabel = new JLabel("Author: " + author);
                    JLabel dateLabel = new JLabel("Date: " + date);

                    selectedDataPanel.add(nameLabel);
                    selectedDataPanel.add(authorLabel);
                    selectedDataPanel.add(dateLabel);
                }
            }
            
            JOptionPane.showMessageDialog(null, new JScrollPane(selectedDataPanel), "Selected Books", JOptionPane.PLAIN_MESSAGE);
        });

        reserveBooksPanel.add(reserveButton);

        this.add(reserveBooksPanel);

        reserveBooksPanel.add(searchBar);
        reserveBooksPanel.add(searchButton);
        reserveBooksPanel.add(scrollPane);
        reserveBooksPanel.add(categoryComboBox);
        reserveBooksPanel.add(reserveButton);

        this.add(reserveBooksPanel);
        this.revalidate();
        this.repaint();   

        currentPanel = reserveBooksPanel;
    }

    
    public void visitPurpose(){
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        
        JPanel visitPurpose = new JPanel();
        visitPurpose.setBackground(new Color(245, 245, 220));
        visitPurpose.setBounds(300, 135, 700, 500);
        visitPurpose.setLayout(null);
        
        JLabel studentID = new JLabel("STUDENT ID");
        studentID.setFont(new Font("Arial", Font.BOLD, 20));
        studentID.setBounds(180, 50, 300, 50);
        
        JTextField studentIDField = new JTextField("");
        studentIDField.setBounds(180, 100, 380, 50);
        
        JLabel purpose = new JLabel("Visitation Purpose");
        purpose.setFont(new Font("Arial", Font.BOLD, 20));
        purpose.setBounds(180, 180, 300, 50);
        
        String[] options = {"Access Wifi", "Quiet Room", "Study", "Read"};
        JComboBox<String> purposeComboBox = new JComboBox<>(options);
        purposeComboBox.setBounds(180, 230, 380, 50);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.setFocusPainted(false);
        submitButton.setBounds(315, 320, 100, 50);
        
        submitButton.addActionListener(e -> {
            String studentIDInput = studentIDField.getText();
            String purposeInput = (String) purposeComboBox.getSelectedItem();
            System.out.println("Student ID: " + studentIDInput);
            System.out.println("Purpose: " + purposeInput);
            
            VisitPurpose vp = new VisitPurpose();
            try {
				vp.insertVisitPurpose(studentIDInput, purposeInput, User.userID);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        visitPurpose.add(studentID);
        visitPurpose.add(studentIDField);
        visitPurpose.add(purpose);
        visitPurpose.add(purposeComboBox);
        visitPurpose.add(submitButton);
        this.add(visitPurpose);
        this.revalidate();
        this.repaint();
        
        currentPanel = visitPurpose;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Student_Home_Page::new);
    }
}
