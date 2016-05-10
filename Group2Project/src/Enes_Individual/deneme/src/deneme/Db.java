package deneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Enes
 * 
 * This class is for the database connection
 */
public class Db {
	private Connection connection = null;
	
	/**
	 * Constructor of the class
	 * @param dbName : Name of the database. 
	 * @param username : Username for accessing to database 
	 * @param password : Password for accessing to database
	 */
	public Db(String dbName,String username,String password)
	{
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    		return;
    	}
    	try {
    		// Try to make connection with given constructor parameters.
    		this.connection = DriverManager
    		.getConnection("jdbc:mysql://ec2-52-50-117-148.eu-west-1.compute.amazonaws.com/"+dbName,username,password);

    	} catch (SQLException e) {
    		e.printStackTrace();
    		return;
    	}
    }
	/**
	 * @return connection object.
	 */
	public Connection getConnection()
	{
		return this.connection;
	}
}