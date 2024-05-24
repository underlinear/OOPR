package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

public class Borrow extends MySQLConnection{
	private String title;
	
	public Borrow(String title) {
		this.title = title;
	}
	
	public boolean borrowBook() {
        String bookIDQuery = "SELECT book_id, available_quantity FROM Books WHERE title = ?";
        int bookID = 0;
        int availableQuantity = 0;

        try {
            PreparedStatement statement = conn.prepareStatement(bookIDQuery);
            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bookID = resultSet.getInt("book_id");
                availableQuantity = resultSet.getInt("available_quantity");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (availableQuantity <= 0) {
            System.out.println("Sorry, the book is not available for borrowing.");
            return false;
        }

        String reservationCountQuery = "SELECT COUNT(*) AS reservation_count FROM Reservations WHERE book_id = ? AND reserved_until >= ? AND is_cancelled = FALSE";
        int reservationCount = 0;

        try {
            PreparedStatement reservationCountStatement = conn.prepareStatement(reservationCountQuery);
            reservationCountStatement.setInt(1, bookID);
            reservationCountStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

            ResultSet reservationCountResultSet = reservationCountStatement.executeQuery();

            if (reservationCountResultSet.next()) {
                reservationCount = reservationCountResultSet.getInt("reservation_count");
            }

            reservationCountResultSet.close();
            reservationCountStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String userReservationQuery = "SELECT COUNT(*) AS user_reservation_count FROM Reservations WHERE book_id = ? AND user_id = ? AND reserved_until >= ? AND is_cancelled = FALSE";
        boolean userHasReservation = false;

        try {
            PreparedStatement userReservationStatement = conn.prepareStatement(userReservationQuery);
            userReservationStatement.setInt(1, bookID);
            userReservationStatement.setInt(2, User.userID);
            userReservationStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            ResultSet userReservationResultSet = userReservationStatement.executeQuery();

            if (userReservationResultSet.next()) {
                userHasReservation = userReservationResultSet.getInt("user_reservation_count") > 0;
            }

            userReservationResultSet.close();
            userReservationStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!userHasReservation && reservationCount >= availableQuantity) {
            System.out.println("Sorry, the book is currently reserved by others.");
            return false;
        }

        String updateQuery = "UPDATE Books SET available_quantity = available_quantity - 1 WHERE book_id = ?";

        try {
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setInt(1, bookID);
            updateStatement.executeUpdate();

            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertQuery = "INSERT INTO Borrowings (user_id, book_id, borrow_date, return_date, is_returned) VALUES (?, ?, ?, NULL, FALSE)";

        try {
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setInt(1, User.userID);
            insertStatement.setInt(2, bookID);
            insertStatement.setDate(3, new java.sql.Date(new Date().getTime()));

            int rowsInserted = insertStatement.executeUpdate();

            insertStatement.close();

            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
