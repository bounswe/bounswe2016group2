package deneme;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.QuerySolution;

/**
 * @author Enes
 * 
 * Servlet implementation class
 * This is the main class for the project.
 */
@WebServlet("/Enes")
public class EnesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * When GET request is sent to the page this function works.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); /**< Our writer to type html codes */
        Db dao = new Db("group2Project", "root", "1234");
		Connection connection = dao.getConnection(); /**< database connection variable*/
        
        out.println("<html>");
		
        /**
         * Check if connection is made.
         */
    	if (connection != null) {
    		out.println("Success , take control of your database now!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
        
    	
    	try {
    		/**
    		 * st: Statement object to execute sql queries.
    		 */
			Statement st = connection.createStatement();
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "LargestCities", null);
			
			/**
			 * If table is already created , drop it.
			 */
			if (tables.next()) {
				st.executeUpdate("DROP table LargestCities");
			  System.out.println("Hayda");  
			}
			else {
				
				 st.executeUpdate(
				        "CREATE TABLE IF NOT EXISTS LargestCities ("
				        + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
				        + " cname VARCHAR(255) NOT NULL,"
				        + " population VARCHAR(255),"
				        + " gps  VARCHAR(255))");
				
					System.out.println("Heyyo");
			}
					connection.close();
		} catch (SQLException e) {
			out.print("NOPE </br>");
			e.printStackTrace();
		}
    	
    	/**
    	 * Following lines of codes are for creating a form
    	 * which contains 2 submit button.
    	 * Search: To make sparql query with a given parameter.
    	 * GetSelected: To get and show the items saved to the database. 
    	 */
        out.println("Enter threshold value to the box to find cities having more or equal population to that value !");
        out.println("Like : 9607787, 14112536 etc.");
        out.println("<form name=\"loginForm\" method=\"post\" action=\"Enes\">");
        out.println("Search: <input type=\"text\" name=\"searchText\"/> <br/>");
        out.println("<input type=\"submit\" name=\"Search\" value=\"Search\" />");
        out.println("<input type=\"submit\" name=\"GetSelected\" value=\"Get Records Saved\" />");
        out.println("</form>"); 
    	out.println("</html>");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		/**
		 * Create print writer to produce html page.
		 */
		 PrintWriter writer = response.getWriter();
         Db dao = new Db("group2Project", "root", "1234");
         /**< database connection variable*/Connection connection = dao.getConnection(); 
		if(request.getParameter("Search")!=null && !request.getParameter("searchText").isEmpty()){
			// Get parameter from textbox
			String text = request.getParameter("searchText");
	   		   
			try {
				/**
				 * Statement execute for creating table
				 */
				Statement st = connection.createStatement();
				st.executeUpdate(
				        "CREATE TABLE IF NOT EXISTS LargestCities ("
				        + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
				        + " cname VARCHAR(255) NOT NULL,"
				        + " population VARCHAR(255),"
				        + " gps  VARCHAR(255))");
				/**
				 * Create form for the query results.
				 */
	            writer.println("<html>");
	            writer.println("<body>");
		        writer.println("<form action=\"Enes\" method=\"POST\"> ");
	            writer.println("<table style=\"width:100%\">");
	            writer.println("<tr>");
	            writer.println("<th>"+"CITY NAME"+"</th>");
	            writer.println("<th>"+"POPULATION"+"</th>");
	            writer.println("<th>"+"GPS"+"</th>");
	            writer.println("<th>Save</th>");
	            writer.println("</tr>");
	            Sparql sparql = new Sparql(text);
		    	org.apache.jena.query.ResultSet res = sparql.getQueryResults();
		    	int id=1;
		    	/**
		    	 * Loop all result objects returned from sparql query.
		    	 * Get item values and save into the database
		    	 * And write them to the screen in the format given above.
		    	 */
		    	while (res.hasNext())
		    	{
		    		QuerySolution binding = res.nextSolution();
		    		//Get city name
		    		String itm =   binding.get("cityLabel").toString();
		    		//Get city population
		    		String itm2 =  binding.get("population").toString();
		    		//Get city location
		    		String itm3 =  binding.get("gps").toString();
		    		String query = " INSERT into LargestCities (cname, population, gps)"+" values (?, ?, ?)";
		    		      // create the mysql insert preparedstatement
		    		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		    		      preparedStmt.setString(1, itm.substring(0,itm.indexOf("@")));
		    		      preparedStmt.setString(2, itm2.substring(0,itm2.indexOf("^")));
		    		      preparedStmt.setString(3, itm3.substring(0,itm3.indexOf("^")));		    		
		    		      // execute the preparedstatement
		    		      preparedStmt.execute();
		    		      writer.println("<tr>");
			        		writer.println("<td align='center'>"+itm.substring(0,itm.indexOf("@"))+"</td>");
			        		writer.println("<td align='center'>"+itm2.substring(0,itm2.indexOf("^"))+"</td>");
			        		writer.println("<td align='center'>"+itm3.substring(0,itm3.indexOf("^"))+"</td>");
			        		writer.println("<td align='center'><input type=\"checkbox\" name=\"labels\""+"value=\""+id+"\"/></td>");
			                writer.println("</tr>"); 
		    		      id++;
		    	}
	            writer.println("</table>");
	            writer.println("<input type=\"submit\" name=\"Save\" value=\"Save\" />");
	            writer.println("</form>");
	            writer.println("</body>");
	            writer.println("</html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(request.getParameter("GetSelected") != null){
			/**
			 * Check if or not there are records user saved.
			 * If not then alert the user with a message box
			 */
			try{
			DatabaseMetaData dbm = connection.getMetaData();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, "LargestCitiesReq", null);
			
			if (tables.next()) {
				  
			}else {
				/**
				 * If user tries to get results without saving 
				 * any data to the database s/he will get this alert.
				 */
				writer.println("<script type=\"text/javascript\">");  
				writer.println("alert('You have not saved anything to db so far !');");  
				writer.println("</script>");
				doGet(request, response);
				return;
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
			try {
				/**
				 * Following query is for taking all records user has saved to the database.
				 */
				Statement st = connection.createStatement();
				String sqlQuery = "SELECT * FROM LargestCitiesReq";
				/**
				 * res: Result set of data obtained from the database
				 */
				ResultSet res = st.executeQuery(sqlQuery);
				writer.println("<html>");
	            writer.println("<body>");
	            writer.println("<form action=\"Enes\" method=\"POST\"> ");
	            writer.println("<table style=\"width:100%\">");
	            writer.println("<tr>");
	            writer.println("<th>"+"CITY NAME"+"</th>");
	            writer.println("<th>"+"POPULATION"+"</th>");
	            writer.println("<th>"+"GPS"+"</th>");
	            writer.println("</tr>");
				while(res.next()){
					// GET id from each row
				int id = res.getInt("ID");
					// GET city name from each row
				String cname = res.getString("CNAME");
					// GET population from each row
				String pop = res.getString("POPULATION");
					// GET gps from each row
				String gps = res.getString("GPS");
				writer.println("<tr>");
        		writer.println("<td align='center'>"+cname+"</td>");
        		writer.println("<td align='center'>"+pop+"</td>");
        		writer.println("<td align='center'>"+gps+"</td>");
                writer.println("</tr>"); 
				}
				writer.println("</table>");
				writer.println("<input type=\"submit\" name=\"Back\" value=\"Back\" />");
	            writer.println("</form>");
	            writer.println("</body>");
	            writer.println("</html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(request.getParameter("Save") != null){
			/**
			 * This if branch is for handling 
			 * the response for saving records to the database.
			 */
			try {
				// Table creation is made if table does not exist.
        		Statement st = connection.createStatement();
            	st.executeUpdate(
    			        "CREATE TABLE IF NOT EXISTS LargestCitiesReq ("
    			        + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
    			        + " cname VARCHAR(255) NOT NULL,"
    			        + " population VARCHAR(255),"
    			        + " gps  VARCHAR(255))");
            	// GET selected values from
    			String[] selectedRows = request.getParameterValues("labels");
    			/**
    			 * For each selected values
    			 * Get original record from the database 
    			 * using the value which is the id of the record.
    			 */
    			for (String str : selectedRows) {
    				int id = Integer.parseInt(str);
    				System.out.println(id);
    				// Get record by given id
    				String selQuery = "SELECT cname,population,gps FROM LargestCities WHERE id="+id;
    				ResultSet res = st.executeQuery(selQuery);
    				String cname = "";
    				String pop = "";
    				String gps = "";
    				// Set the values for the database fields.
    				if(res.next()){
    					cname = res.getString("CNAME");
    					pop = res.getString("POPULATION");
    					gps = res.getString("GPS");
    					System.out.println(cname);
    				}
    				String query = " INSERT into LargestCitiesReq (cname, population, gps)"+" values (?, ?, ?)";
	    		      // create the mysql insert preparedstatement
	    		      PreparedStatement preparedStmt = connection.prepareStatement(query);
	    		      preparedStmt.setString(1, cname);
	    		      preparedStmt.setString(2, pop);
	    		      preparedStmt.setString(3, gps);		    		
	    		      // execute the preparedstatement
	    		      preparedStmt.execute();
    			}
    			st.executeUpdate("DROP table LargestCities");
    			doGet(request, response);
    			writer.println("<script type=\"text/javascript\">");  
    			writer.println("alert('Selected Items are Saved to Db !');");  
    			writer.println("</script>");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else if(request.getParameter("Back") != null){
		/**
		 * This response is taken when back button is clicked.
		 * This response redirects to the main page.
		 */
		try {
			// When page redirects to main page
			// All tables deleted from the database.
			Statement st = connection.createStatement();
			st.executeUpdate("DROP table LargestCities");
			st.executeUpdate("DROP table LargestCitiesReq");
			doGet(request, response);
			writer.println("<script type=\"text/javascript\">");  
			writer.println("alert('Your table is destroyed !');");  
			writer.println("</script>");
			System.out.println("I'm back bro");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}else{
		// If user wants to search without entering a parameter to search box
		// s/he will be alerted with a message box
		writer.println("<script type=\"text/javascript\">");  
		writer.println("alert('Textbox should not be empty to make search !');");  
		writer.println("</script>");
		doGet(request, response);
		return;
	}
	}
}
	