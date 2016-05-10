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
 * Servlet implementation class Enes connects to database
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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); /**< Our writer to type html codes */
        Db dao = new Db("group2Project", "root", "1234");
		Connection connection = dao.getConnection(); /**< database connection variable*/
        
        out.println("<html>");
		
    	if (connection != null) {
    		out.println("You made it, take control your database now!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
        
    	
    	try {
			Statement st = connection.createStatement();
			DatabaseMetaData dbm = connection.getMetaData();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, "LargestCities", null);
			
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
    	
        out.println();
        out.println("<form name=\"loginForm\" method=\"post\" action=\"Enes\">");
        out.println("Search: <input type=\"text\" name=\"searchText\"/> <br/>");
        out.println("<input type=\"submit\" name=\"Search\" value=\"Search\" />");
        out.println("<input type=\"submit\" name=\"GetSelected\" value=\"Get Records Saved\" />");
        out.println("</form>");
       
    	out.println("</html>");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter writer = response.getWriter();
         Db dao = new Db("group2Project", "root", "1234");
 		   Connection connection = dao.getConnection(); /**< database connection variable*/
		if(request.getParameter("Search")!=null){
			String text = request.getParameter("searchText");
	   		   
			try {
				Statement st = connection.createStatement();
				st.executeUpdate(
				        "CREATE TABLE IF NOT EXISTS LargestCities ("
				        + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
				        + " cname VARCHAR(255) NOT NULL,"
				        + " population VARCHAR(255),"
				        + " gps  VARCHAR(255))");
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
		    	while (res.hasNext())
		    	{
		    		QuerySolution binding = res.nextSolution();
		    		String itm =  binding.get("cityLabel").toString();
		    		String itm2 =  binding.get("population").toString();
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
			try{
			DatabaseMetaData dbm = connection.getMetaData();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, "LargestCitiesReq", null);
			
			if (tables.next()) {
				  
			}else {
				doGet(request, response);
				return;
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
			try {
				Statement st = connection.createStatement();
				String sqlQuery = "SELECT * FROM LargestCitiesReq";
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
				int id = res.getInt("ID");
				String cname = res.getString("CNAME");
				String pop = res.getString("POPULATION");
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
			try {
        		Statement st = connection.createStatement();
            	st.executeUpdate(
    			        "CREATE TABLE IF NOT EXISTS LargestCitiesReq ("
    			        + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
    			        + " cname VARCHAR(255) NOT NULL,"
    			        + " population VARCHAR(255),"
    			        + " gps  VARCHAR(255))");
    			String[] selectedRows = request.getParameterValues("labels");
    			
    			for (String str : selectedRows) {
    				int id = Integer.parseInt(str);
    				System.out.println(id);
    				String selQuery = "SELECT cname,population,gps FROM LargestCities WHERE id="+id;
    				ResultSet res = st.executeQuery(selQuery);
    				String cname = "";
    				String pop = "";
    				String gps = "";
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
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else if(request.getParameter("Back") != null){
		
		try {
			Statement st = connection.createStatement();
			st.executeUpdate("DROP table LargestCities");
			st.executeUpdate("DROP table LargestCitiesReq");
			doGet(request, response);
			System.out.println("I'm back bro");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	}
}
	