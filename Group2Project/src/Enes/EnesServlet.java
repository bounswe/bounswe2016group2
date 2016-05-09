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
 * Servlet implementation class Yunus connects to database
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
         if(request.getParameter("Save")!=null){
			String [] selectedRows = request.getParameterValues("labels");
			for (String str : selectedRows) {
				System.out.println(str);
			}
		}
        out.println("<html>");
        
		Db dao = new Db("group2Project", "root", "1234");
		Connection connection = dao.getConnection(); /**< database connection variable*/
		
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
				Sparql sparql = new Sparql();
		    	org.apache.jena.query.ResultSet res = sparql.getQueryResults();
		   	/*while (res.hasNext())
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
		    	}*/
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
        out.println("</form>");
       
    	out.println("</html>");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("Search")!=null){
			String text = request.getParameter("searchText");
			 PrintWriter writer = response.getWriter();
	           Db dao = new Db("group2Project", "root", "1234");
	   		   Connection connection = dao.getConnection(); /**< database connection variable*/
	   		   
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
		        writer.println("<form action=\"Enes\" method=\"GET\"> ");
	            writer.println("<table style=\"width:100%\">");
	            writer.println("<tr>");
	            writer.println("<th>"+"CNAME"+"</th>");
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
			        		writer.println("<td>"+itm.substring(0,itm.indexOf("@"))+"</td>");
			        		writer.println("<td>"+itm2.substring(0,itm2.indexOf("^"))+"</td>");
			        		writer.println("<td>"+itm3.substring(0,itm3.indexOf("^"))+"</td>");
			        		writer.println("<td><input type=\"checkbox\" name=\"labels\""+"value=\""+id+"\"/></td>");
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
		}/*else if(request.getParameter("Save")!=null){
			String [] selectedRows = request.getParameterValues("labels");
			for (String str : selectedRows) {
				System.out.println(str);
			}
		}*/else{
			doGet(request, response);
		}
	}

}