package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static Connection conn = null;

    public MySQLConnection() {

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
          
            System.out.print("\t\tConnection Established at jdbc:mysql://localhost:3306/library_management \n");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}