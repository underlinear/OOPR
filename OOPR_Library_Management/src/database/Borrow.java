package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Borrow extends MySQLConnection{
	private String title;
	
	public Borrow(String title) {
		this.title = title;
	}
	
	public boolean borrowBook() {
		
		String bookIDQuery = "SELECT book_id FROM Books WHERE title = ?";
		int bookID = 0;
		
		try {
		    PreparedStatement statement = conn.prepareStatement(bookIDQuery);
		    statement.setString(1, title);
		    
		    ResultSet resultSet = statement.executeQuery();
		    
		    if (resultSet.next()) {
		        bookID = resultSet.getInt("book_id");
		    }
		    
		    resultSet.close();
		    statement.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		String updateQuery = "UPDATE Books "
                + "SET available_quantity = available_quantity - 1 "
                + "WHERE book_id = " + bookID + ";";

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(updateQuery);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		
		
        String insertQuery = "INSERT INTO Borrowings (user_id, book_id, borrow_date, return_date, is_returned) "
                + "VALUES (?, ?, ?, NULL, FALSE)";

        try {
            PreparedStatement statement = conn.prepareStatement(insertQuery);

            statement.setInt(1, User.userID);
            statement.setInt(2, bookID);
            statement.setDate(3, new java.sql.Date(new Date().getTime()));

            int rowsInserted = statement.executeUpdate();

            statement.close();

            if (rowsInserted > 0) {
                System.out.println("Book borrowed successfully.");
                return true;
            } else {
                System.out.println("Failed to borrow book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
