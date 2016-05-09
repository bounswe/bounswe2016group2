package deneme;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;

public class Sparql {
	private ResultSet results;
	private String squery = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
            "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
            "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
            "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
			"SELECT ?thing ?thingLabel ?image\n"+
			"WHERE\n"+
			"{\n"+
				"?thing wdt:P366 wd:Q132781 .\n"+
				"?thing wdt:P18 ?image .\n"+
				"?s wdt:P50 ?author .\n"+
				"SERVICE wikibase:label {\n"+
				"bd:serviceParam wikibase:language \"en\" \n"+
				"}\n"+
			"}\n LIMIT 10";
				

	public Sparql() {
		squery = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
	            "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
	            "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
	            "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
	            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
				"SELECT ?thing ?thingLabel ?image\n"+
				"WHERE\n"+
				"{\n"+
					"?thing wdt:P366 wd:Q132781 .\n"+
					"?thing wdt:P18 ?image .\n"+
					"?s wdt:P50 ?author .\n"+
					"SERVICE wikibase:label {\n"+
					"bd:serviceParam wikibase:language \"en\" \n"+
					"}\n"+
				"}\n LIMIT 100";
        Query query = QueryFactory.create(squery); 
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        results = qExe.execSelect();
	}

	public ResultSet getQueryResults() {
		return results;
	}
}