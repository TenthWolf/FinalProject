package DB;

import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DataSQL {

	public static void main(String[] args) throws SocketTimeoutException {
		// Wait 7 seconds when run
		// Error in Connection process: maybe wrong location

		int choice;
		// Word Storing Section.
		// A do while loop to take multiple words from the user.
		do {
			// A scanner to take input word from user.
			Scanner in = new Scanner(System.in);

			// Using Try and catch to handle checked exception like sqlexception and
			// classnotfound exception.
			try {
				// Asking user to enter a word.
				System.out.println("Enter a word:");
				String word = in.next();
				// Loading the jdbc driver.
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				// Create Connection.
				// hostname = LAPTOP-84BGFJK3
				// serverInstance = sqlexpress
				String connectionURL = "jdbc:sqlserver://LAPTOP-84BGFJK3\\SQLEXPRESS\\Word_Ocurrences\\net:1433 "
						+ " databaseName=Word_Ocurrences;" + "userName = LAPTOP-84BGFJK3\\17876;" + "password = ;"
						+ "integratedSecurity=true;";
				// Establishing Connection.
				Connection con = DriverManager.getConnection(connectionURL);
				// Creating statement.
				PreparedStatement ps = con.prepareStatement("INSERT into WORDS (Selection) VALUES(?)");
				// Setting the word entered by the user in the query.
				ps.setString(1, word);
				// Executing the query.
				ps.executeUpdate();
				// Showing the database table word after addition of the user word.
				System.out.println("Database after addition of your word");
				Statement st = con.createStatement();
				ResultSet rs = ps.executeQuery("select * from WORDS");
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}

				con.close();
			} catch (ClassNotFoundException | SQLException s) {
				System.out.println(s.getMessage());
			}
			System.out.println("Type 1 to enter more words or Type 0 to end program");
			choice = in.nextInt();
		} while (choice == 1);

		// Word Ocurrence Section
		System.out.println("Frequency of each word present in the database is:");
		// Creating a map to store the word and corresponding frequency.
		Map<String, Integer> frequency = new LinkedHashMap<>();
		// Same as word storing section accessing the database and get the resultset.
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://LAPTOP-84BGFJK3\\sqlexpress.Word_Ocurrences.windows.net:1433"
					+ "databaseName=Word_Ocurrences;" + "userName = LAPTOP-84BGFJK3\\17876;" + "password = ;"
					+ "integratedSecurity=true;";
			Connection con = DriverManager.getConnection(connectionURL);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from WORDS");
			while (rs.next()) {
				// Getting the word from the database.
				String s = rs.getString(1);
				// Checking if the word already present in map or not.
				if (frequency.get(s) == null) {
					// If not present then adding the word with frequency 1 in the map.
					frequency.put(s, 1);
				} else {
					// Else increasing the frequency if it's already present in the map.
					frequency.put(s, frequency.get(s) + 1);
				}
			}
			// Closing the database connection.
			con.close();
		} catch (ClassNotFoundException | SQLException s) {
			System.out.println(s.getMessage());
		}
		// Printing the words with it's frequency.
		Set<String> key = frequency.keySet();
		for (String k : key) {
			System.out.println("Word: " + k + " frequency: " + frequency.get(k));

		}
	}

}
