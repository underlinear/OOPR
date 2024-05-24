package database;

import java.sql.SQLException;
import java.sql.Statement;

public class TableManager extends MySQLConnection{
	
	public void createAllTables() throws SQLException{
		createProgramsTable();
		createStudentsTable();
		createUsersTable();
		createBooksTable();
		createBorrowingsTable();
		createReservationsTable();
		createVisitPurposeTable();
	}
	
	public void dropAllTables() throws SQLException {
		String[] queries = {
				"DROP TABLE IF EXISTS Visits",
	            "DROP TABLE IF EXISTS Reservations",
	            "DROP TABLE IF EXISTS Borrowings",
	            "DROP TABLE IF EXISTS Books",
	            "DROP TABLE IF EXISTS Users",
	            "DROP TABLE IF EXISTS Students",
	            "DROP TABLE IF EXISTS Programs",
	            "DROP TABLE IF EXISTS Books"
	        };

	        try (Statement stmt = conn.createStatement()) {
	            for (String query : queries) {
	                stmt.execute(query);
	            }
	        }
	}
	
	public void dropSpecificTable(String table) throws SQLException{
		String query = "DROP TABLE IF EXISTS" + table + ";";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
	}
	
	public void createUsersTable() throws SQLException {
		String query = "CREATE TABLE Users ("
			    + "user_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "email VARCHAR(100), "
			    + "password VARCHAR(128), "
			    + "user_type VARCHAR(10 ), "
			    + "student_number VARCHAR(20), "
			    + "FOREIGN KEY (student_number) REFERENCES Students(student_number)"
			    + ");";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
	}
	
	public void createProgramsTable() throws SQLException {
		String query = "CREATE TABLE Programs ("
			    + "program_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "program_name VARCHAR(100), "
			    + "program_code VARCHAR(20)"
			    + ");";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		// FOR TESTING:
		
		String insertQuery = "INSERT INTO Programs (program_name, program_code) " +
		"VALUES ('Bachelor of Computer Science', 'BSCS'),"
		+ "('Bachelor of Science in Nursing', 'BSN'),"
		+ "('Bachelor of Science in Information Technology', 'BSIT')";
		Statement insertstmt = conn.createStatement();
		insertstmt.execute(insertQuery);
	}
	
	public void createStudentsTable() throws SQLException {
		String query = "CREATE TABLE Students ("
			    + "student_number VARCHAR(20) PRIMARY KEY, "
			    + "first_name VARCHAR(50), "
			    + "last_name VARCHAR(50), "
			    + "middle_name VARCHAR(50), "
			    + "year_level INT, "
			    + "section VARCHAR(10), "
			    + "program_id INT, "
			    + "FOREIGN KEY (program_id) REFERENCES Programs(program_id)"
			    + ");";

		Statement stmt = conn.createStatement();
		stmt.execute(query);
	}
	
	public void createBorrowingsTable() throws SQLException {
		String query = "CREATE TABLE Borrowings ("
			    + "borrowing_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "user_id INT, "
			    + "book_id INT, "
			    + "borrow_date DATE, "
			    + "return_date DATE, "
			    + "is_returned BOOLEAN, "
			    + "FOREIGN KEY (user_id) REFERENCES Users(user_id), "
			    + "FOREIGN KEY (book_id) REFERENCES Books(book_id)"
			    + ");";
		Statement stmt = conn.createStatement();
        stmt.execute(query);
	}
	
	public void createBooksTable() throws SQLException {
		String query = "CREATE TABLE Books ("
			    + "book_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "title VARCHAR(100), "
			    + "author VARCHAR(100), "
			    + "quantity INT, "
			    + "category VARCHAR(30), "
			    + "date_published DATE, "
			    + "available_quantity INT"
			    + ");";
		Statement stmt = conn.createStatement();
        stmt.execute(query);
	}
	
	public void createReservationsTable() throws SQLException {
		String query = "CREATE TABLE Reservations ("
			    + "reservation_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "user_id INT, "
			    + "book_id INT, "
			    + "reservation_date DATE, "
			    + "reserved_until DATE, "
			    + "is_cancelled BOOLEAN, "
			    + "FOREIGN KEY (user_id) REFERENCES Users(user_id), "
			    + "FOREIGN KEY (book_id) REFERENCES Books(book_id)"
			    + ");";
		Statement stmt = conn.createStatement();
        stmt.execute(query);
	}
	
	public void createAdminsTable() throws SQLException {
		String query = "CREATE TABLE Admins ("
			    + "admin_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "user_id INT, "
			    + "first_name VARCHAR(50), "
			    + "last_name VARCHAR(50), "
			    + "middle_name VARCHAR(50), "
			    + "employee_type VARCHAR(50), "
			    + "FOREIGN KEY (user_id) REFERENCES Users(user_id)"
			    + ");";
		Statement stmt = conn.createStatement();
        stmt.execute(query);
	}
	
	public void createVisitPurposeTable() throws SQLException {
		String query = "CREATE TABLE Visits ("
			    + "visit_id INT AUTO_INCREMENT PRIMARY KEY, "
			    + "purpose VARCHAR(30),"
			    + "user_id INT,"
			    + "student_id VARCHAR(20),"
			    + "time_visited DATETIME,"
			    + "FOREIGN KEY (user_id) REFERENCES Users(user_id)"
			    + ");";
		Statement stmt = conn.createStatement();
        stmt.execute(query);
	}
}