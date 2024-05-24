package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Reserve extends MySQLConnection{
	public void reserveBooks(String title) throws SQLException {
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
                + "WHERE book_id = ?";

        try {
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setInt(1, bookID);
            updateStatement.executeUpdate();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalDate reservationDate = LocalDate.now();
        LocalDate reservedUntil = reservationDate.plusDays(3);

        String reserveQuery = "INSERT INTO Reservations(user_id, book_id, reservation_date, reserved_until, is_cancelled) VALUES (?, ?, ?, ?, FALSE)";

        try {
            PreparedStatement ps = conn.prepareStatement(reserveQuery);
            ps.setInt(1, User.userID);
            ps.setInt(2, bookID);
            ps.setDate(3, java.sql.Date.valueOf(reservationDate));
            ps.setDate(4, java.sql.Date.valueOf(reservedUntil));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
}
