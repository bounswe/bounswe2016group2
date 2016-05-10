

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Servlet implementation class YigitServlet
 */
@WebServlet("/YigitOzgumus")
public class YigitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int duration = 0;
	//TODO
	int queryLimit = 10; // Default Value
       
    /** Constructor of the class
     * @see HttpServlet#HttpServlet()
     */
    public YigitServlet() {
        super();
    }
    /** Creates the database connection using the credentials defined in mainPageServlet.
     * 	Checks the class and the SQL availability
     * @return The database connection object
     */
    protected Connection establishDatabase(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch(ClassNotFoundException e1){
    		e1.printStackTrace();
    	}
    	Connection conn;
    	try{
    		// The url,usr and password are kept in mainPage for modularity
    		conn = DriverManager.getConnection(MainPageServlet.DB_URL,MainPageServlet.DB_USER,MainPageServlet.DB_PASSWORD);
    		return conn;
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
    /**
     * This method parses the movie JSON and uploads it to the DB Yigit_Table
     * It seperates the labels and adds it to the SQL Query
     */
    protected void createTable(){
    	Connection conn = null;
    	Statement stat = null;
    	// Creating the connection
    	conn = establishDatabase();
    	try {
			stat = conn.createStatement();
			JSONArray movies = getMovies();
			String query = "INSERT INTO Yigit_Table VALUES";
			for(int i=0; i<movies.size(); i++){
				String movieData = movies.toJSONString();
				query += "(\"";
				JSONObject movie = (JSONObject) movies.get(i);
				JSONObject film = (JSONObject) movie.get("FilmLabel");
				query += film.get("value");
				query += "\", \"";
				JSONObject duration = (JSONObject) movie.get("Duration");
				query += duration.get("value");
				query += "\", \"";
				JSONObject composer = (JSONObject) movie.get("ComposerLabel");
				query += composer.get("value");
				query += "\", \"";
				JSONObject genre = (JSONObject) movie.get("genreLabel");
				query += genre.get("value");
				query += "\", \"";
				JSONObject date = (JSONObject) movie.get("dateLabel");
				query += date.get("value");
				query += "\"),";
			}
			if (query != null) {
				stat.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * This method uses sparql query service and gets the query result data, then exports to JSON and returns 
     * the JSON Array of the Movies that are found from the query
     * @return
     */
    protected JSONArray getMovies()  {
    	// Get the query String from the function
    	String movieQuery = null;
		movieQuery =  "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
			   "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
			   "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
			   "PREFIX p: <http://www.wikidata.org/prop/>\n" +
			   "PREFIX ps: <http://www.wikidata.org/prop/statement/>\n" +
			   "PREFIX pq: <http://www.wikidata.org/prop/qualifier/>\n" +
			   "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			   "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
			   "SELECT ?Film ?FilmLabel ?Duration ?ComposerLabel ?genreLabel ?dateLabel \n" +
			   "WHERE {\n" +
			   "?Film wdt:P31 wd:Q11424.\n" +
			   "?Film wdt:P2047 ?Duration.\n" +
			   "?Film wdt:P86 ?Composer.\n" +
			   "?Film wdt:P136  ?genre.\n" +
			   "OPTIONAL { ?Film wdt:P577 ?dateLabel. }\n" +
			   "SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
			   "FILTER(?Duration >= " + this.duration +").\n" +
			   "}\n" +
			   "LIMIT " + 50 +"\n";
    	// Import into sparql function
    	Query query= QueryFactory.create(movieQuery);
    	//Process the query
    	QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query);
    	//Get the result data
    	ResultSet movieResults = qexec.execSelect();
    	// write to a ByteArrayOutputStream
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	//Convert the format to JSON
    	ResultSetFormatter.outputAsJSON(outputStream, movieResults);
    	// and turn that into a String
    	String output = new String(outputStream.toByteArray());
    	System.out.println(output);
    	JSONParser parser = new JSONParser();
    	JSONObject data = null;
		try {
			data = (JSONObject) parser.parse(output);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// Collect JSON object and convert it to the JSON Array containing movies
    	JSONArray items =  (JSONArray) ((JSONObject) data.get("results")).get("bindings");
    	
    	
    	return items;	
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //TODO
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		createTable();
		//The outline of the page
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>Movie Analysis for Time Freaks</title>");
		out.println("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\" style=\"margin: 20px auto;\">");
		out.println("<div class=\"jumbotron\" style=\"margin-top: 15px; text-align: center;\">");
		out.println("You can search any type of movie by its duration. Enter the duration value (Non-negative) to the box below to see the current movies");
		out.println("</div>");
		out.println("<div class=\"panel panel-default\">");
		out.println("<div class=\"panel-heading\">");
		out.println("Duration Query ");
		out.println("</div>");
		out.println("<div class=\"panel-body\">");
		out.println("<form action=\"\" class=\"form-inline\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-3\">");
		out.println("<div class=\"input-group\">");
		out.println("<input class=\"form-control\" type=\"number\" name=\"duration\" placeholder=\"duration of movie\">");
		out.println("<span class=\"input-group-addon\">min</span>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"col-sm-3\">");
		out.println("<input class=\"btn btn-default\" type=\"submit\" value=\"Get data\">");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"panel panel-default\">");
		out.println("<div class=\"panel-heading\">");
		out.println("Films");
		out.println("</div>");
		out.println("<form method=\"POST\" action=\"\">");
		out.println("<table class=\"table\">");
		out.println("<tr>");
		out.println("<th>Store</th>");
		out.println("<th>Film</th>");
		out.println("<th>Duration</th>");
		out.println("<th>Composer</th>");
		out.println("<th>Genre</th>");
		out.println("<th>Date</th>");
		out.println("</tr>");
		JSONArray movies = getMovies();
			for (int i = 0; i < movies.size() ; i++) {
				out.println("<tr>");
				JSONObject movie = (JSONObject) movies.get(i);
				
					JSONObject filmL = (JSONObject) movie.get("FilmLabel");
					String filmS = (String) filmL.get("value");
				out.println("<td><input type=\"checkbox\" name=\"data<%= i %>store\"></td>");
				out.println("<input hidden type=\"text\" name=\"data<%= i %>film\" value=\"<%=" + filmS+ "%>\">");
				JSONObject durationL = (JSONObject) movie.get("Duration");
				String durationS = (String) durationL.get("value");
				out.println("<input hidden type=\"text\" name=\"data<%= i %>duration\" value=\"<%=" +durationS+ "%>\">");
				JSONObject composerL = (JSONObject) movie.get("ComposerLabel");
				String composerS = (String) composerL.get("value");
				out.println("<input hidden type=\"text\" name=\"data<%= i %>composer\" value=\"<%=" +composerS+ "%>\">");
				JSONObject genreL = (JSONObject) movie.get("genreLabel");
				String genreS = (String) filmL.get("value");
				out.println("<input hidden type=\"text\" name=\"data<%= i %>genre\" value=\"<%=" +genreS+ "%>\">");
				JSONObject dateL = (JSONObject) movie.get("dateLabel");
				String dateS = (String) dateL.get("value");
				out.println("<input hidden type=\"text\" name=\"data<%= i %>date\" value=\"<%=" +dateS+ "%>\">");
				
				out.println("<td>"+ filmS+ "</td>");
				out.println("<td>"+ durationS+"</td>");
				out.println("<td>"+composerS+"</td>");
				out.println("<td>"+genreS+"</td>");
				out.println("<td>"+ dateS+"</td>");
			}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
//		try{
//			PrintWriter out = response.getWriter();
//			
//			
//			out.println(movies);
//			request.setAttribute("items", movies);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//TODO
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		// TODO Auto-generated method stub
		
		
	}
	}


