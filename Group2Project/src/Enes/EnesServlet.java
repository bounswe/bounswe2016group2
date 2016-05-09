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
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.LiteralImpl;

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
			ResultSet tables = dbm.getTables(null, null, "TORTURE", null);
			if (tables.next()) {
			  // Table exists
			}
			else {
				st.executeUpdate(
				        "CREATE TABLE IF NOT EXISTS Torture ("
				        + " id INTEGER PRIMARY KEY AUTO_INCREMENT,"
				        + " nadress VARCHAR(255) NOT NULL,"
				        + " label VARCHAR(255),"
				        + " imageL  VARCHAR(255))");
				Sparql sparql = new Sparql();
		    	org.apache.jena.query.ResultSet res = sparql.getQueryResults();
		    	while (res.hasNext())
		    	{
		    		QuerySolution binding = res.nextSolution();
		    		String itm = binding.get("thing").toString();
		    		LiteralImpl itm2 = (LiteralImpl) binding.getLiteral("thingLabel");
		    		String s = itm2.toString();
		    		String itm3 =  binding.get("image").toString();
		    		String query = " INSERT into Torture (nadress, label, imageL)"+" values (?, ?, ?)";
		    		
		    		      // create the mysql insert preparedstatement
		    		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		    		      preparedStmt.setString (1, itm);
		    		      preparedStmt.setString (2, s.substring(0,s.length()-3));
		    		      preparedStmt.setString(3, itm3);		    		
		    		      // execute the preparedstatement
		    		      preparedStmt.execute();
		    	}
			}
					connection.close();
			
		} catch (SQLException e) {
			out.print("NOPE </br>");
			e.printStackTrace();
		}
    	
        out.println();
        out.println("<form name=\"loginForm\" method=\"post\" action=\"Enes\">");
        out.println("Search: <input type=\"text\" name=\"searchText\"/> <br/>");
        out.println("<input type=\"submit\" value=\"Search\" />");
        out.println("</form>");
       
    	out.println("</html>");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("searchText")!=null){
			String text = request.getParameter("searchText");
			 PrintWriter writer = response.getWriter();
	           Db dao = new Db("group2Project", "root", "1234");
	   		   Connection connection = dao.getConnection(); /**< database connection variable*/
	   		   Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * from Torture");
	            writer.println("<html>");
	            writer.println("<body>");
		        writer.println("<form action=\"Enes\" method=\"GET\"> ");
	            writer.println("<table style=\"width:100%\">");
	            writer.println("<tr>");
	            writer.println("<th>"+"Label"+"</th>");
	            writer.println("<th>"+"LabelThing"+"</th>");
	            writer.println("<th>"+"Image"+"</th>");
	            writer.println("<th>Save</th>");
	            writer.println("</tr>");
	            while (rs.next())
	        	{
	            	int id = rs.getInt("ID");
	            	String nadress = rs.getString("NADRESS");
	            	String label = rs.getString("LABEL");
	            	String image = rs.getString("IMAGEL");
	        		writer.println("<tr>");
	        		writer.println("<td>"+nadress+"</td>");
	        		writer.println("<td>"+label+"</td>");
	        		writer.println("<td>"+image+"</td>");
	        		writer.println("<td><input type=\"checkbox\" name=\"labels\""+"value=\""+id+"\"/></td>");
	                writer.println("</tr>"); 
	        	}
	            writer.println("</table>");
	            writer.println("<input type=\"submit\" value=\"Save\" />");
	            writer.println("</form>");
	            writer.println("</body>");
	            writer.println("</html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(request.getParameter("Save")!=null){
			
		}else{
			doGet(request, response);
		}
	}

}