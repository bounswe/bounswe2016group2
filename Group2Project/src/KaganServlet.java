

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.jena.query.Dataset;
//import org.apache.jena.query.DatasetFactory;
//import org.apache.jena.query.Query;
//import org.apache.jena.query.QueryExecution;
//import org.apache.jena.query.QueryExecutionFactory;
//import org.apache.jena.query.QueryFactory;
//import org.apache.jena.query.QuerySolution;
//import org.apache.jena.query.ResultSet;
//import org.apache.jena.sparql.function.library.print;
//import org.json.simple.JSONArray;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class KaganServlet
 */
@WebServlet("/KaganServlet")
public class KaganServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int radius = 1;
	public int xDiff = 0;
	public int yDiff = 0;
	protected double x = 29.051115;
	protected double y = 41.084458;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KaganServlet() {
        super();
    }

    protected Connection getDBConnection() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
    	Connection conn;
		try {
			conn = DriverManager.getConnection(MainPageServlet.DB_URL, MainPageServlet.DB_USER, MainPageServlet.DB_PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    protected void assignParameters(HttpServletRequest request) {
		if (request.getParameterMap().containsKey("radius")) {
			try {
				this.radius = Integer.parseInt(request.getParameter("radius"));				
			} catch (Exception e) {
				this.radius = 1;
			}
		}
		if (request.getParameterMap().containsKey("x")) {
			try {				
				this.xDiff = Integer.parseInt(request.getParameter("x"));
			} catch (Exception e) {
				this.xDiff = 0;
			}
		}
		if (request.getParameterMap().containsKey("y")) {
			try {
				this.yDiff = Integer.parseInt(request.getParameter("y"));				
			} catch (Exception e) {
				this.yDiff = 0;
			}
		}
	}

    protected void adjustCoordinates() {
		double xDiffRad = ((double) this.xDiff) / (111.320 * Math.cos(this.x));
		double yDiffRad = ((double) this.yDiff) / 110.574;
		this.x -= xDiffRad;
		this.y += yDiffRad;
	}

    protected String getQueryString() throws UnsupportedEncodingException {
		String queryStr =
			"PREFIX wd: <http://www.wikidata.org/entity/> " +
			"PREFIX wdt: <http://www.wikidata.org/prop/direct/> " +
			"PREFIX wikibase: <http://wikiba.se/ontology#> " +
			"PREFIX p: <http://www.wikidata.org/prop/> " +
			"PREFIX ps: <http://www.wikidata.org/prop/statement/> " +
			"PREFIX pq: <http://www.wikidata.org/prop/qualifier/> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX bd: <http://www.bigdata.com/rdf#>" +
			"PREFIX ogc: <http://www.opengis.net/ont/geosparql#>" +
			"SELECT DISTINCT ?place ?placeLabel ?placeDescription ?gps " +
			"WHERE " +
			"{ " +
			"  SERVICE wikibase:around {" +
			"    ?place wdt:P625 ?gps . " +
			"    bd:serviceParam wikibase:center \"Point(" + this.x + " " + this.y + ")\"^^ogc:wktLiteral . " +
			"    bd:serviceParam wikibase:radius \"" + this.radius + "\" . " +
			"  }" +
			"  SERVICE wikibase:label {" +
			"    bd:serviceParam wikibase:language \"en\" ." +
			"  }" +
			"}" +
			"LIMIT 10";
		return URLEncoder.encode(queryStr, "UTF-8");
	}
  
	private String createInsertQuery(HttpServletRequest request) {
		boolean insert = false;
		String q = "INSERT INTO kagan (label, description) VALUES ";
		int i = 0;
		String next = request.getParameter("data" + i);
		while (next != null) {
			String save = request.getParameter("data" + i + "save");
			String label = request.getParameter("data" + i + "label");
			String description = request.getParameter("data" + i + "description");
			if (save != null) {
				insert = true;
				q += "(\"" + label + "\", \"" + description + "\"),";
			}
			i++;
			next = request.getParameter("data" + i);
		}
		// remove last comma and return query if insert is gonna happen
		return insert ? q.substring(0, q.length() - 1) : null;
	}

    protected JSONArray getItems() throws IOException, ParseException {
    	String queryStr = getQueryString();
    	URL path = new URL("https://query.wikidata.org/sparql?format=json&query=" + queryStr);
    	URLConnection con = path.openConnection();

    	InputStream in = con.getInputStream();
    	String encoding = con.getContentEncoding();
    	encoding = encoding == null ? "UTF-8" : encoding;
    	String body = IOUtils.toString(in, encoding);

    	JSONParser parser = new JSONParser();
    	JSONObject data = (JSONObject) parser.parse(body);
    	JSONArray items = (JSONArray) ((JSONObject) (((JSONObject) data).get("results"))).get("bindings");
		return items;
    	// Query query = QueryFactory.create(queryStr);
		// Dataset model = DatasetFactory.create();
		// QueryExecution qExe = QueryExecutionFactory.create(query, model);
		// ResultSet resultSet = qExe.execSelect();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.x = 29.051115;
		this.y = 41.084458;
		assignParameters(request);
		adjustCoordinates();
		try {
			PrintWriter out = response.getWriter();
			JSONArray items = getItems();
			out.println(items);
			request.setAttribute("items", items);
			request.getRequestDispatcher("KaganView.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = getDBConnection();
		String query = createInsertQuery(request);
		try {
			Statement stmt = conn.createStatement();
			if (query != null) {
				stmt.executeUpdate(query);
			}
			ResultSet rs = stmt.executeQuery("SELECT * FROM kagan");
			JSONArray items = new JSONArray();
			while (rs.next()) {
				String label = rs.getString("label");
				String description = rs.getString("description");
				JSONObject item = new JSONObject();
				JSONObject placeLabel = new JSONObject();
				JSONObject placeDescription = new JSONObject();
				placeLabel.put("value", label);
				placeDescription.put("value", description);
				item.put("placeLabel", placeLabel);
				item.put("placeDescription", placeDescription);
				items.add(item);
			}
			request.setAttribute("items", items);
			request.getRequestDispatcher("KaganView.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
