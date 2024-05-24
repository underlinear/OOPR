package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRetrieval extends MySQLConnection {
    // private static final int TOLERANCE_LEVEL = 4; // Adjust this value as needed
    // private String search = "";

    // public BookRetrieval(String search){
    //    this.search = search;
    // }
    
    // public BookRetrieval(){
    //    this.search = "";
    // }

    public List<Book> fetchBooksFromDatabase() throws SQLException {
        List<Book> books = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT author, title, quantity, category, available_quantity, date_published, book_id FROM Books";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String author = resultSet.getString("author");
            String title = resultSet.getString("title");
            String datePublished = resultSet.getString("date_published");
            String category = resultSet.getString("category");
            int quantity = resultSet.getInt("quantity");
            int availableQuantity = resultSet.getInt("available_quantity");
            int book_id = resultSet.getInt("book_id");
            books.add(new Book(author, title, datePublished, category, quantity, availableQuantity, book_id));
        }

        return books;
    }
    
    public List<Book> fetchBooksFromDatabase(String category) throws SQLException {
        List<Book> books = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT author, title, quantity, category, available_quantity, date_published, book_id FROM Books";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
        	String categoryColumn = resultSet.getString("category");
        	
        	if(category == categoryColumn) {
        		String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String datePublished = resultSet.getString("date_published");
                int quantity = resultSet.getInt("quantity");
                int availableQuantity = resultSet.getInt("available_quantity");
                int book_id = resultSet.getInt("book_id");
                books.add(new Book(author, title, datePublished, categoryColumn, quantity, availableQuantity, book_id));
        	}
        }
        return books;
    }
    
    public List<Book> fetchBooksWithZeroAvailableQuantity() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT author, title, quantity, category, available_quantity, date_published, book_id " +
                       "FROM Books " +
                       "WHERE available_quantity = 0";
        
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String datePublished = resultSet.getString("date_published");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                int availableQuantity = resultSet.getInt("available_quantity");
                int book_id = resultSet.getInt("book_id");
                books.add(new Book(author, title, datePublished, category, quantity, availableQuantity, book_id));
            }
        }
        return books;
    }


    /*
    // Unused

    private List<Book> fuzzySearch(List<Book> books, String searchQuery, int tolerance) {
        List<Book> results = new ArrayList<>();
        String lowerCaseSearchQuery = searchQuery.toLowerCase();

        for (Book book : books) {
        	String lowerCaseAuthor = book.getAuthor().toLowerCase();
            String lowerCaseTitle = book.getTitle().toLowerCase();

            int distanceAuthor = levenshteinDistance(lowerCaseSearchQuery, lowerCaseAuthor);
            int distanceTitle = levenshteinDistance(lowerCaseSearchQuery, lowerCaseTitle);

            if (distanceAuthor <= tolerance || distanceTitle <= tolerance ||
                lowerCaseAuthor.contains(lowerCaseSearchQuery) || lowerCaseTitle.contains(lowerCaseSearchQuery)) {
                results.add(book);
            }
        }

        return results;
    }
    
    
    // Unused
    
    private int levenshteinDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // Create a matrix
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Initialize the matrix
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[i][j] = j;  // If first string is empty, insert all characters of second string
                } else if (j == 0) {
                    dp[i][j] = i;  // If second string is empty, remove all characters of first string
                } else {
                    // If the characters are the same, no new operation is needed
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                    dp[i][j] = Math.min(Math.min(
                            dp[i - 1][j] + 1,      // Remove
                            dp[i][j - 1] + 1),     // Insert
                            dp[i - 1][j - 1] + cost);  // Replace
                }
            }
        }
        return dp[len1][len2];
    }
    
    // Returns a string array of matching book titles only
    
    public List<Book> getMatchingBooks(String searchQuery) {
        try {
            List<Book> books = fetchBooksFromDatabase();
            return fuzzySearch(books, searchQuery, TOLERANCE_LEVEL);

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return an empty array in case of an error
        }
    }
    
    public List<Book> getMatchingBooks() {
        try {
            List<Book> books = fetchBooksFromDatabase();
            return fuzzySearch(books, search, TOLERANCE_LEVEL);

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return an empty array in case of an error
        }
    }
    */
}
