package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class TransactionsHandler extends MySQLConnection{
	public static void updateIsPaidStatus(String student_number) {
		String query = "UPDATE Transactions SET is_paid = true, date_of_transaction = ? WHERE student_number = ?;" ;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			stmt.setString(2, student_number);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
