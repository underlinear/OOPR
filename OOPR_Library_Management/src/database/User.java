package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import accountretrieval.TLSEmail;

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
	
	public static boolean verifyStudentNumber (String studentNumber) {
        String sql = "SELECT s.first_name, u.email " +
                     "FROM Students s " +
                     "JOIN Users u ON s.student_number = u.student_number " +
                     "WHERE s.student_number = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, studentNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                TLSEmail tls = new TLSEmail();
                String code = generateCode(studentNumber);
                System.out.print(email);
                tls.generateEmail(email, firstName, code);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public static String generateCode(String studentNumber) {
		Random r = new Random();
		int fourDigit = 1000 + r.nextInt(9000);
		updateVerificationCode(studentNumber, String.valueOf(fourDigit));
		
		
		return String.valueOf(fourDigit);
	}
	
	public static void updateVerificationCode(String studentNumber, String verificationCode) {
        String sql = "UPDATE Users SET verification_code = ? WHERE student_number = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
 
            statement.setString(1, verificationCode);
            statement.setString(2, studentNumber);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static boolean verifyCode(String studentNumber, String verificationCode) throws SQLException {
        // SQL query to get the verification_code for a specific student_number
        String query = "SELECT verification_code FROM Users WHERE student_number = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the student_number parameter
            stmt.setString(1, studentNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the verification_code from the result set
                    String storedCode = rs.getString("verification_code");

                    // Compare the retrieved verification_code with the passed verificationCode
                    return verificationCode.equals(storedCode);
                } else {
                    // No matching student found
                    return false;
                }
            }
        }
    }
	
}
