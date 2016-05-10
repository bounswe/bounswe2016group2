package deneme;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;

/**
 * @author Enes
 * 
 * This class is for querying wikidata and taking results.
 */
public class Sparql {
	private ResultSet results;
	private String squery = "";
				
	/**
	 * Constructor of the class.
	 */
	public Sparql() {
		// Sparql query to be used without taking parameters.
		this.squery = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
	            "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
	            "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
	            "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
	            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
	            "PREFIX bd: <http://www.bigdata.com/rdf#>\n"+
				"SELECT DISTINCT ?cityLabel ?population ?gps\n"+
				"WHERE\n"+
				"{\n"+
					"?city wdt:P31/wdt:P279* wd:Q515 .\n"+
					"?city wdt:P1082 ?population .\n"+
					"?city wdt:P625 ?gps .\n"+
					"SERVICE wikibase:label {\n"+
					"bd:serviceParam wikibase:language \"en\" \n"+
					"}\n"+
				"}\n ORDER BY DESC(?population) LIMIT 100";
        Query query = QueryFactory.create(squery); 
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        this.results = qExe.execSelect();
	}
	/**
	 * @param text : parameter for population to be used in sparql query.
	 * Makes the query and sets results field
	 */
	public Sparql(String text){
		this.squery = "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
	            "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
	            "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
	            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
	            "PREFIX bd: <http://www.bigdata.com/rdf#>\n"+
				"SELECT DISTINCT ?cityLabel ?population ?gps\n"+
				"WHERE\n"+
				"{\n"+
					"?city wdt:P31/wdt:P279* wd:Q515 .\n"+
					"?city wdt:P1082 ?population .\n"+
					"?city wdt:P625 ?gps .\n"+
					"FILTER(?population >= "+text+").\n"+
					"SERVICE wikibase:label {\n"+
					"bd:serviceParam wikibase:language \"en\" .\n"+
					"}\n"+
				"}\n ORDER BY DESC(?population) LIMIT 100";
		
		// Create query.
        Query query = QueryFactory.create(squery); 
        // Execute query in given domain.
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        // Result of the query.
        this.results = qExe.execSelect();
	}
	/**
	 * @return query result.
	 */
	public ResultSet getQueryResults() {
		return this.results;
	}
}