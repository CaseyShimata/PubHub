package examples.pubhub.utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookDAOImpl;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;

/**
 * Class used to retrieve DAO Implementations. Serves as a factory. Also manages a single instance of the database connection.
 */
public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "shimatacb";
	private static final String CONNECTION_PASSWORD = "7ollercoaster";
	private static final String URL = "jdbc:postgresql://localhost:5432/PubHub";
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			//test if connection is good
//			DatabaseMetaData meta = connection.getMetaDat();
//			System.out.println(meta.getDatabaseProductName());
			
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	
	public static BookDAO getBookDAO() {
		///dao is parent of impl (upcasting 
		///use this in case child bookdaoimpl is 
		///changed we know it will always use parent funcs 
		///exa: vipBookDAOImpl still uses same bookDAO
		return new BookDAOImpl();
	}
	
	public static TagDAO getTagDAO() {
		return new TagDAOImpl();
	}
	
	

}
