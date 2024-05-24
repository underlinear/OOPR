package database;

import java.sql.SQLException;
import java.sql.Statement;

public class InitializeData extends MySQLConnection{
	
	public static void main(String[] args) {
		try {
			new InitializeData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public InitializeData() throws SQLException{
		
		TableManager tm = new TableManager();
		
		tm.dropAllTables();
		
		tm.createAllTables();
		
		// Inserting random programming books for example
		
		String query = "INSERT INTO Books (title, author, quantity, available_quantity, date_published, category) VALUES " +
		        "('1984', 'George Orwell', 8, 8, '1949-06-08', 'Fiction'), " +
		        "('A Tale of Two Cities', 'Charles Dickens', 10, 10, '1859-01-01', 'Fiction'), " +
		        "('Alice''s Adventures in Wonderland', 'Lewis Carroll', 12, 12, '1865-11-26', 'Fiction'), " +
		        "('Ang Tundo Man May Langit Din', 'Andres Cristobal Cruz', 10, 10, '1986-01-01', 'Filipino Literature'), " +
		        "('Automate the Boring Stuff with Python', 'Al Sweigart', 14, 14, '2015-04-14', 'Programming'), " +
		        "('Banaag at Sikat', 'Lope K. Santos', 5, 5, '1906-01-01', 'Filipino Literature'), " +
		        "('Biyaya ng Lupa', 'Tomas Pinpin', 9, 9, '1906-01-01', 'Filipino Literature'), " +
		        "('Brave New World', 'Aldous Huxley', 7, 7, '1932-01-01', 'Fiction'), " +
		        "('C Programming Language', 'Brian W. Kernighan, Dennis M. Ritchie', 6, 6, '1978-02-22', 'Programming'), " +
		        "('Clean Code', 'Robert C. Martin', 10, 10, '2008-08-01', 'Programming'), " +
		        "('Code Complete', 'Steve McConnell', 12, 12, '1993-06-09', 'Programming'), " +
		        "('Compilers: Principles, Techniques, and Tools', 'Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman', 5, 5, '1986-01-01', 'Programming'), " +
		        "('Concrete Mathematics', 'Ronald L. Graham, Donald E. Knuth, Oren Patashnik', 4, 4, '1989-03-01', 'Programming'), " +
		        "('Cracking the Coding Interview', 'Gayle Laakmann McDowell', 15, 15, '2008-07-01', 'Programming'), " +
		        "('Crime and Punishment', 'Fyodor Dostoevsky', 14, 14, '1866-01-01', 'Fiction'), " +
		        "('Dekada ''70', 'Lualhati Bautista', 12, 12, '1983-01-01', 'Filipino Literature'), " +
		        "('Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', 7, 7, '1994-10-31', 'Programming'), " +
		        "('Don Quixote', 'Miguel de Cervantes', 8, 8, '1605-01-01', 'Fiction'), " +
		        "('Eloquent JavaScript', 'Marijn Haverbeke', 11, 11, '2011-12-14', 'Programming'), " +
		        "('Effective C++', 'Scott Meyers', 8, 8, '1991-03-25', 'Programming'), " +
		        "('Effective Java', 'Joshua Bloch', 9, 9, '2001-05-28', 'Programming'), " +
		        "('El Filibusterismo', 'Jose Rizal', 8, 8, '1891-09-18', 'Filipino Literature'), " +
		        "('Frankenstein', 'Mary Shelley', 10, 10, '1818-01-01', 'Fiction'), " +
		        "('Grapes of Wrath', 'John Steinbeck', 10, 10, '1939-04-14', 'Fiction'), " +
		        "('Grokking Algorithms', 'Aditya Y. Bhargava', 10, 10, '2016-05-01', 'Programming'), " +
		        "('Head First Design Patterns', 'Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson', 10, 10, '2004-10-25', 'Programming'), " +
		        "('Introduction to Algorithms', 'Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein', 7, 7, '1990-09-01', 'Programming'), " +
		        "('Introduction to the Theory of Computation', 'Michael Sipser', 5, 5, '1996-01-01', 'Programming'), " +
		        "('Java: The Complete Reference', 'Herbert Schildt', 10, 10, '1997-02-01', 'Programming'), " +
		        "('JavaScript: The Good Parts', 'Douglas Crockford', 8, 8, '2008-05-15', 'Programming'), " +
		        "('Learning Python', 'Mark Lutz', 12, 12, '1999-10-01', 'Programming'), " +
		        "('Lord of the Flies', 'William Golding', 6, 6, '1954-09-17', 'Fiction'), " +
		        "('Moby-Dick', 'Herman Melville', 9, 9, '1851-10-18', 'Fiction'), " +
		        "('Mythical Man-Month', 'Frederick P. Brooks Jr.', 8, 8, '1975-04-01', 'Programming'), " +
		        "('Nang Gabing Maging Akin Ka', 'Efren Abueg', 6, 6, '1984-01-01', 'Filipino Literature'), " +
		        "('Noli Me Tangere', 'Jose Rizal', 10, 10, '1887-05-01', 'Filipino Literature'), " +
		        "('One Hundred Years of Solitude', 'Gabriel García Márquez', 8, 8, '1967-05-30', 'Fiction'), " +
		        "('Operating Systems: Three Easy Pieces', 'Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau', 7, 7, '2014-03-01', 'Programming'), " +
		        "('Patterns of Enterprise Application Architecture', 'Martin Fowler', 6, 6, '2002-11-15', 'Programming'), " +
		        "('Pride and Prejudice', 'Jane Austen', 7, 7, '1813-01-28', 'Fiction'), " +
		        "('Pro Git', 'Scott Chacon, Ben Straub', 10, 10, '2009-08-01', 'Programming'), " +
		        "('Python Crash Course', 'Eric Matthes', 15, 15, '2015-11-01', 'Programming'), " +
		        "('Refactoring: Improving the Design of Existing Code', 'Martin Fowler', 6, 6, '1999-07-08', 'Programming'), " +
		        "('Si Amapola sa 65 na Kabanata', 'Ricky Lee', 7, 7, '2009-01-01', 'Filipino Literature'), " +
		        "('Structure and Interpretation of Computer Programs', 'Harold Abelson, Gerald Jay Sussman', 7, 7, '1985-07-01', 'Programming'), " +
		        "('The Adventures of Huckleberry Finn', 'Mark Twain', 9, 9, '1884-12-10', 'Fiction'), " +
		        "('The Art of Computer Programming', 'Donald E. Knuth', 4, 4, '1968-01-01', 'Programming'), " +
		        "('The Catcher in the Rye', 'J.D. Salinger', 12, 12, '1951-07-16', 'Fiction'), " +
		        "('The Clean Coder', 'Robert C. Martin', 9, 9, '2011-05-23', 'Programming'), " +
		        "('The Great Gatsby', 'F. Scott Fitzgerald', 5, 5, '1925-04-10', 'Fiction'), " +
		        "('The Hobbit', 'J.R.R. Tolkien', 11, 11, '1937-09-21', 'Fiction'), " +
		        "('The Lord of the Rings', 'J.R.R. Tolkien', 10, 10, '1954-07-29', 'Fiction'), " +
		        "('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 8, 8, '1975-04-01', 'Programming'), " +
		        "('The Picture of Dorian Gray', 'Oscar Wilde', 8, 8, '1890-07-20', 'Fiction'), " +
		        "('The Pragmatic Programmer', 'Andrew Hunt and David Thomas', 8, 8, '1999-10-30', 'Programming'), " +
		        "('To Kill a Mockingbird', 'Harper Lee', 10, 10, '1960-07-11', 'Fiction'), " +
		        "('War and Peace', 'Leo Tolstoy', 15, 15, '1869-01-01', 'Fiction'), " +
		        "('Wuthering Heights', 'Emily Brontë', 7, 7, '1847-12-01', 'Fiction'), " +
		        "('You Don’t Know JS', 'Kyle Simpson', 1, 0, '2014-12-28', 'Programming');";

		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		String user = "INSERT INTO Users (email, password, user_type) VALUES"
					 +"('admin@toadinone.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'admin');";
		stmt.execute(user);
		
		String insertQuery = "INSERT INTO Students (student_number, first_name, last_name, middle_name, year_level, section, program_id) VALUES "
			    + "('02220004241', 'Arcadio', 'Flocarencia Jr.', 'R', 2, 'YB-2', 1),"
			    + "('02220004271', 'Trevor James', 'Dolim', 'B', 2, 'YB-2', 1),"
			    + "('0220-1892-888', 'Noriel Edward', 'Ong', 'L', 2, 'YB-2', 1),"
			    + "('02220003439', 'Romille', 'Ilaida', 'M', 2, 'YB-2', 1),"
			    + "('02220010198', 'John Lloyd', 'Torre', NULL, 2, 'YB-2', 1),"
			    + "('1111', 'Alice', 'Amber', 'A', 1, 'YB-6', 2),"
			    + "('2222', 'Billy', 'Bob', 'B', 3, 'YB-6', 2),"
			    + "('3333', 'Charlie', 'Chaplin', 'C', 2, 'YB-1', 3);";
		
		stmt.execute(insertQuery);
		
		
		System.out.println("works!");
		}
}
