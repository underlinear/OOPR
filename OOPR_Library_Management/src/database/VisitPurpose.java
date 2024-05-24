package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class VisitPurpose extends MySQLConnection{
	public void insertVisitPurpose(String student_ID, String purpose, int user_id) throws SQLException {
		String query = "INSERT INTO Visits(purpose, time_visited, student_id, user_id) VALUES" +
					   "(?, ?, ?, ?);";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, purpose);
		ps.setTimestamp(2, new Timestamp(new Date().getTime()));
		ps.setString(3, student_ID);
		ps.setInt(4, user_id);
		ps.execute();
		
		
	}
}
