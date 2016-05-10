

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class YigitServlet
 */
@WebServlet("/YigitOzgumus")
public class YigitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int duration = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YigitServlet() {
        super();
        // TODO Auto-generated constructor stub
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
    		conn = DriverManager.getConnection(MainPageServlet.DB_URL,MainPageServlet.DB_USER,MainPageServlet.DB_PASSWORD);
    		return conn;
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
    /**
     *  Store the main query String for modularity
     * @return the query string with the right encoding
     * @throws UnsupportedEncodingException
     */
    protected String getQuery() throws UnsupportedEncodingException{
    	String query = "PREFIX wd: <http://www.wikidata.org/entity/> " +
    				   "PREFIX wdt: <http://www.wikidata.org/prop/direct/> " +
    				   "PREFIX wikibase: <http://wikiba.se/ontology#> " +
    				   "PREFIX p: <http://www.wikidata.org/prop/> " +
    				   "PREFIX ps: <http://www.wikidata.org/prop/statement/> " +
    				   "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> " +
    				   "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
    				   "PREFIX bd: <http://www.bigdata.com/rdf#> " +
    				   "SELECT ?Film ?FilmLabel ?Duration ?ComposerLabel ?genreLabel ?dateLabel " +
    				   "WHERE { " +
    				   "?Film wdt:P31 wd:Q11424. " +
    				   "?Film wdt:P2047 ?Duration. " +
    				   "?Film wdt:P86 ?Composer. " +
    				   "?Film wdt:P136  ?genre. " +
    				   "OPTIONAL { ?Film wdt:P577 ?dateLabel. } " +
    				   "SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } " +
    				   "FILTER(?Duration >= 100). " +
    				   "} " +
    				   "LIMIT 50 " ;
    	return URLEncoder.encode(query,"UTF-8");

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		out.println("This is Yigit Ozgumus's page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
