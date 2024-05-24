package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler{
	
	private String password;
	
	public PasswordHandler(String password) {
		this.password = password;
	}
	
	public boolean isValidPassword(){
		//rules:
		//  password length greater than 8
		//  Must contain one symbol
		//  Must contain one uppercase letter
		//  Must contain one lowercase letter
		//  Must contain a number
		if (password.length() < 8) {
			return false;
		}
		
		String specialSymbols = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		for(int i = 0; i <= password.length(); i++) {
			if (i == password.length()) {
				return false;
			}
			if (specialSymbols.contains(String.valueOf(password.charAt(i)))) {
                break;
            }
		}
		
		boolean digit = false, lowercase = false, uppercase = false;
		for(int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				digit = true;
			}
			else if (Character.isLowerCase(password.charAt(i))) {
				lowercase = true;
			}
			else if (Character.isUpperCase(password.charAt(i))) {
				uppercase = true;
			}
		}
		
		if(!(digit && lowercase && uppercase)) {
			return false;
		}
		return true;
	}
	
	public String hashPassword() {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // Add password bytes to digest
            md.update(password.getBytes());
            
            // Get the hashed bytes
            byte[] hashedBytes = md.digest();
            
            // Convert hashed bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException
            e.printStackTrace();
            return null;
        }
    }

}