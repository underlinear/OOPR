package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends MySQLConnection{
	public static String name;
	public static int userID;
	public static boolean isLoggedIn = false;
	public static String studentNumber;
	
	public User(String name, int userID, boolean isLoggedIn, String studentNumber) {
		User.name = name;
		User.userID = userID;
		User.isLoggedIn = isLoggedIn;
		User.studentNumber = studentNumber;
	}
	
	public static String getProgramNameByStudentNumber(String studentNumber) throws SQLException {
        String query = "SELECT p.program_name FROM Students s "
                + "JOIN Programs p ON s.program_id = p.program_id "
                + "WHERE s.student_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("program_name");
                } else {
                    return null;
                }
            }
        }
    }
}
