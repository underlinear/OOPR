package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.sql.ResultSet;

public class SignupManager extends MySQLConnection{
	private String email;
	private String password;
	private String student_number; // TODO: Store in Students table
	
	// Might not be needed
	
	/*
	
	private String student_number;
	private String first_name;
	private String last_name;
	private String middle_name;
	private int year_level;
	private String section;
	private int user_id;
	private int program_id;
	
	// Not sure if still needed
	 * 
	public SignupManager(String email, String password, String student_number, String first_name, String last_name, String middle_name, int year_level, String section, int program_id) {
		this.email = email;
		this.password = password;
		this.student_number = student_number;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.year_level = year_level;
		this.section = section;
		this.program_id = program_id;
	}
	
	*/
	
	public SignupManager(String email, String password, String student_number) {
		this.email = email;
		this.password  = password;
		this.student_number = student_number;
	}
	
	// If handling more information for signup, addStudent()
	
	/*
	public int addStudent() throws SQLException {
		int rowsAffected = 0;
		
		PreparedStatement usersQuery = conn.prepareStatement(
				"INSERT INTO Users (email, password, user_type)" +
				"VALUES (?, ?, 'Student')");
		
		PasswordHandler ph = new PasswordHandler(password);
		usersQuery.setString(1, email);
		usersQuery.setString(2, ph.hashPassword());
		
		rowsAffected += usersQuery.executeUpdate();
		
		// TODO: Get User ID of newly created row on Users table
		PreparedStatement getUserIdQuery = conn.prepareStatement(
			    "SELECT user_id FROM Users WHERE email = ?");
		getUserIdQuery.setString(1, email);
		ResultSet resultSet = getUserIdQuery.executeQuery();

		if (resultSet.next()) {
			user_id = resultSet.getInt("user_id");
		}
		
		PreparedStatement studentsQuery = conn.prepareStatement(
                "INSERT INTO Students (student_number, first_name, last_name, middle_name, year_level, section, user_id, program_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
		 studentsQuery.setString(1, student_number); // student_number
         studentsQuery.setString(2, first_name);     // first_name
         studentsQuery.setString(3, last_name);      // last_name
         studentsQuery.setString(4, middle_name);    // middle_name
         studentsQuery.setInt(5, year_level);        // year_level
         studentsQuery.setString(6, section);        // section
         studentsQuery.setInt(7, user_id);           // user_id
         studentsQuery.setInt(8, program_id); 
         
         rowsAffected += studentsQuery.executeUpdate();
         
         return rowsAffected;
		
	} */
	
	public boolean isEmailDuplicate(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
	
	public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	
	private boolean isStudentNumberExists(String studentNumber) throws SQLException {
	    String query = "SELECT COUNT(*) FROM Students WHERE student_number = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, studentNumber);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
	
	public boolean isStudentNumberDuplicate(String student_number) throws SQLException {
	    String query = "SELECT COUNT(*) FROM Users WHERE student_number = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, student_number);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    }
	    return false;
	}

	public int signup() throws SQLException {
	    PasswordHandler ph = new PasswordHandler(password);

	    if (!isStudentNumberExists(student_number)) {
	        return 1; 
	    }

	    if (isStudentNumberDuplicate(student_number)) {
	        return 2;
	    }

	    if (!isValidEmail(email)) {
	        return 3;
	    }

	    if (isEmailDuplicate(email)) {
	        return 4;
	    }

	    if (!ph.isValidPassword()) {
	        return 5;
	    }

	    String insertQuery = "INSERT INTO Users (email, password, student_number, user_type) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
	        stmt.setString(1, email);
	        stmt.setString(2, ph.hashPassword());
	        stmt.setString(3, student_number);
	        stmt.setString(4, "student");
	        stmt.executeUpdate();
	        return 0;
	    }
	}
}
