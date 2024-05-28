package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import toadinone.System_Management;

public class LoginManager extends MySQLConnection {
	private String studentNumber = "";
	private String password = "";
	
	public LoginManager(String studentNumber, String password) {
		this.studentNumber = studentNumber;
		this.password = password;
	}
	
	public LoginManager() {
		this.studentNumber = "";
		this.password = "";
	}
	
	public boolean login(String studentNumber, String password) throws SQLException {
		
        if (studentNumber == null || studentNumber.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        
        PasswordHandler pHandle = new PasswordHandler(password);

        String query = "SELECT user_id FROM Users WHERE student_number = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, studentNumber);
        pstmt.setString(2, pHandle.hashPassword());
        ResultSet rs = pstmt.executeQuery();
        
        boolean loginSuccessful = rs.next();
        
        if(loginSuccessful) {
        	String nameQuery = "SELECT first_name, last_name, middle_name FROM Students WHERE student_number = ?";
        	PreparedStatement nameStmt = conn.prepareStatement(nameQuery);
            nameStmt.setString(1, studentNumber);
            ResultSet rs1 = nameStmt.executeQuery();
            rs1.next();
            
        	String firstName = rs1.getString("first_name");
        	String lastName = rs1.getString("last_name");
        	String middleName = rs1.getString("middle_name");
        	
        	String fullName = lastName + ", " + firstName + " " + middleName;
        	
        	new User( firstName,fullName, rs.getInt("user_id"), true, studentNumber);
        	
        }
        
        rs.close();
        pstmt.close();
        
        return loginSuccessful;
    }
		
	public boolean login() throws SQLException {
		return login(this.studentNumber, this.password);
	}
	
}
