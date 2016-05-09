package deneme;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;

public class Sparql {
	private ResultSet results;
	private String squery = "";
				
	public Sparql() {
		squery = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
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
        results = qExe.execSelect();
	}
	public Sparql(String text){
		squery = "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
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
        Query query = QueryFactory.create(squery); 
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        results = qExe.execSelect();
	}
	public ResultSet getQueryResults() {
		return results;
	}
}