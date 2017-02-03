package qa.utility.bizjdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import qa.SeleniumTest;

public class DatabaseTool {

	// JDBC driver name and database URL
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://stagingdb.bizj-internal.com/?serverTimezone=UTC";

	// Database credentials
	final String USER = "web";
	final String PASS = "";	
	
	//results
	ArrayList<HashMap<String, String>> resultSet = new ArrayList<>();

	//constructor
	public DatabaseTool() throws SQLException {		
		
	}	
	
	
	/**
	 * Set query results. Can only be used by getSqlResponse for data integrity purposes
	 * @param resultSet - an ArrayList of query results
	 */
	private void setResultSet(ArrayList<HashMap<String, String>> resultSet) {
		this.resultSet = resultSet;
	}

	/**
	 * Retrieves a list of results from an SQL query and formats the data into a usable ArrayList.
	 * The ArrayList contains HashMaps, each of which corresponds to a row in the database.
	 * @param query - a query builder object is passed in order to examine the query in more detail if needed.
	 * @param rs - the result set to traverse is passed.
	 * @return - returns an ArrayList of query results.
	 * @throws SQLException
	 */
	private ArrayList<HashMap<String, String>>  getSqlResponse(QueryBuilder query, ResultSet rs) throws SQLException {
		HashMap<String, String> columnMap = new HashMap<>();
		ArrayList<HashMap<String, String>> resultContainer = new ArrayList<>();

		for (int i = 0; i < query.getColumns().length; i++) {

		}
		// Extract data from result set
		while (rs.next()) {
			HashMap<String, String> tempMap = new HashMap<>();
			
			//Create hashMap of column data
			for (int i = 0; i < query.getColumns().length; i++) {				
				columnMap.put(query.getColumns()[i], rs.getString(query.getColumns()[i]));
				SeleniumTest.logger
						.info("<" + query.getColumns()[i] + "," + rs.getString(query.getColumns()[i])
								+ "> put into hashmap..." + System.lineSeparator());
			}
			
			//insert HashMap into ArrayList
			tempMap.putAll(columnMap);
			resultContainer.add(tempMap);	
			columnMap.clear();
			SeleniumTest.logger
			.info("hashmap added to arraylist and cleared..." + System.lineSeparator());
		}
		return resultContainer;
	}

	/**
	 * Executes an SQL query and calls the getSqlResponse method to format the results into a usable arrayList.
	 * @param query - the query builder object allows for precise manipulation of the query if needed.
	 * @throws SQLException
	 */
	public void execute(QueryBuilder query) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);

			// establish DB connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// create query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			// String sql;
			// sql = "select id, api_user_id, event_id from bizj.ev_api_log";
			ResultSet rs = stmt.executeQuery(query.sqlBuildQuery());

			setResultSet(getSqlResponse(query, rs));

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

			// select id, api_user_id, event_id from bizj.ev_api_log
		} catch (Exception ex) {
			//System.out.println(ex.getMessage());
			SeleniumTest.logger.severe("Error: "+ex.getMessage()+System.lineSeparator());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
//	public void execute(String query) throws SQLException {
//		Connection conn = null;
//		Statement stmt = null;
//
//		try {
//			Class.forName(JDBC_DRIVER);
//
//			// establish DB connection
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//			// create query
//			System.out.println("Creating statement...");
//			stmt = conn.createStatement();
//			// String sql;
//			// sql = "select id, api_user_id, event_id from bizj.ev_api_log";
//			ResultSet rs = stmt.executeQuery(query);
//
//			setResultSet(getSqlResponse(query, rs));
//
//			// Clean-up environment
//			rs.close();
//			stmt.close();
//			conn.close();
//
//			// select id, api_user_id, event_id from bizj.ev_api_log
//		} catch (Exception ex) {
//			//System.out.println(ex.getMessage());
//			SeleniumTest.logger.severe("Error: "+ex.getMessage()+System.lineSeparator());
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			} // nothing we can do
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
//	}
	
	/**
	 * returns ArrayList of query results after query is executed.
	 * @return - resultSet - an ArrayList of query results
	 */
	public ArrayList<HashMap<String, String>> getResultSet() {
		if(!resultSet.isEmpty()){
			return resultSet;
		}else{
			SeleniumTest.logger.warning("No query has been executed!"+ System.lineSeparator());
			return null;
		}		
	}
}
