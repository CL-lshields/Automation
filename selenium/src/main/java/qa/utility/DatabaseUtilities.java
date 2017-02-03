/**
 * Utility class to manage a db connection.  Creates a connection to staging db by default, but
 * we could add a method to provide custom connection info if needed.
 */

package qa.utility;

import java.sql.*;
import java.util.*;

public class DatabaseUtilities {
	
	private static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DEFAULT_URL = "jdbc:mysql://stagingdb.bizj-internal.com/?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private static final String DEFAULT_USER = "web";
	private static final String DEFAULT_PASSWORD = "";
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DEFAULT_DRIVER);
		connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
		
		return connection;
	}
	
	public void closeConnection() {
		try {
			if(connection != null && connection.isValid(1)) {
				connection.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> query(String sql) throws SQLException {
        List<Map<String, Object>> results = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            results = map(rs);
        } finally {
            closeResultSet();
            closeStatement();
        }
        return results;
    }
	
	public List<Map<String, Object>> query(String sql, List<Object> parameters) throws SQLException {
        List<Map<String, Object>> results = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            rs = ps.executeQuery();
            results = map(rs);
        } finally {
            closeResultSet();
            closeStatement();
        }
        return results;
    }
	
	private List<Map<String, Object>> map(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        try {
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int numColumns = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= numColumns; ++i) {
                        String name = meta.getColumnName(i);
                        Object value = rs.getObject(i);
                        row.put(name, value);
                    }
                    //System.out.println("Adding " + row.toString());
                    results.add(row);
                }
            }
        } finally {
            closeResultSet();
        }
        return results;
    }
	
	private void closeStatement() {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
	private void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}