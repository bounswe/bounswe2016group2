package deneme;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import java.sql.Statement;

public class Db_queryTest {
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws SQLException {
		Db dao = new Db("group2Project", "root", "1234");
		Connection connection = dao.getConnection();
		Statement st = connection.createStatement();
		String sqlQuery = "SELECT  FROM LargestCitiesReq WHERE population >= "+14657434;
		boolean test = false;
		if (st.executeQuery(sqlQuery).next()) test = true;
		assertTrue("The population test for the threshold value is completed",test);
	}
}
