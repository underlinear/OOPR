package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Return extends MySQLConnection{
	public List<Book> getBooksNotReturnedByUser(int userId) {
        List<Book> books = new ArrayList<>();

        // SQL query to select books that haven't been returned by a specific user
        String sql = "SELECT b.book_id, b.title, b.author, b.quantity, b.category, b.date_published, b.available_quantity " +
                     "FROM Borrowings br " +
                     "JOIN Books b ON br.book_id = b.book_id " +
                     "WHERE br.user_id = ? AND br.is_returned = FALSE";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bookID = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");
                String category = resultSet.getString("category");
                String datePublished = resultSet.getString("date_published");
                int availableQuantity = resultSet.getInt("available_quantity");

                // Create a Book object for each row and add it to the list
                Book book = new Book(author, title, datePublished, category, quantity, availableQuantity, bookID);
                books.add(book);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
	
	public void returnBook(String title) {
		
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
		
		
        String updateQuantityQuery = "UPDATE Books SET available_quantity = available_quantity + 1 WHERE book_id = ?";
        String updateBorrowingsQuery = "UPDATE Borrowings SET return_date = ?, is_returned = TRUE WHERE book_id = ? AND user_id = ? AND is_returned = FALSE";

        try {
            // Increment available_quantity of the specified book_id
            PreparedStatement updateQuantityStatement = conn.prepareStatement(updateQuantityQuery);
            updateQuantityStatement.setInt(1, bookID);
            updateQuantityStatement.executeUpdate();

            // Set the return_date to current date and is_returned to true
            PreparedStatement updateBorrowingsStatement = conn.prepareStatement(updateBorrowingsQuery);
            updateBorrowingsStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            updateBorrowingsStatement.setInt(2, bookID);
            updateBorrowingsStatement.setInt(3, User.userID);
            updateBorrowingsStatement.executeUpdate();

            updateQuantityStatement.close();
            updateBorrowingsStatement.close();
            
            System.out.println("Book returned successfully. Book ID: " + bookID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
