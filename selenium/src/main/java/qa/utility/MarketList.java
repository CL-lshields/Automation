package qa.utility;

/**
 * @author ahagge
 */

import java.sql.*;
import java.util.*;

import qa.utility.DatabaseUtilities;

public class MarketList {
	
	private DatabaseUtilities db;
	
	private final String marketSelect = "SELECT M.market_id, M.market_code, M.city, M.market_name, MD.tout_enabled, MD.virtual_market	" +
										"FROM bizj.journal_info I " +
										"LEFT JOIN bizj.market M ON (M.market_id = I.market_id) " +
										"LEFT JOIN ( " +
										"SELECT object_id, " +
										"MIN(IF(object_id IS NOT NULL AND meta_name = \"tout:enabled\", meta_value, NULL)) as tout_enabled, " +
										"MIN(IF(object_id IS NOT NULL AND meta_name = \"virtual_market\", meta_value, NULL)) as virtual_market " +
										"FROM bizj.metadata " +
										"WHERE object_type = \"market\" AND meta_name IN (\"virtual_market\",\"tout:enabled\") " +
										"AND object_id IS NOT NULL " +
										"group by object_id " +
										") MD ON (MD.object_id = M.market_id)";
	
	private List<Map<String, Object>> markets;
	
	public MarketList() {
		db = new DatabaseUtilities();
	}
	
	public void loadMarkets() {
		try {
			db.createConnection();			
			markets = db.query(marketSelect);
			db.closeConnection();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMarkets(HashMap<String, String> metaData) {
		// build where clause
		String whereClause = " WHERE 1 ";
		for(Map.Entry<String, String> md : metaData.entrySet()) {
			whereClause += " AND " + md.getKey() + " = \"" + md.getValue() + "\" ";
		}
		
		System.out.println(marketSelect + whereClause);
		
		// fetch data into markets
		try {
			db.createConnection();			
			markets = db.query(marketSelect + whereClause);
			db.closeConnection();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns a list containing all markets
	 * @return - market list
	 */
	public List<Map<String, Object>> getmarkets(){
		return this.markets;
	}
	
	/**
	 * Returns a map containing a random market from the markets list.
	 * @return random market.
	 */
	public Map<String, Object> getRandomMarket() {
		Random rand = new Random();
		int index = rand.nextInt(markets.size());
		return markets.get(index);
	}
	
	/**
	 * Returns a map containing a random digital market from the markets list.
	 * @param wantDigital - a boolean determing weather digital markets are included.
	 * @return - a random market.
	 */
	public Map<String, Object> getRandomMarket(Boolean wantDigital) {
		
		Map<String, Object> market = getRandomMarket();
		Boolean marketIsDigital = market.get("virtual_market") != null && market.get("virtual_market").equals(1L);
		
		while((marketIsDigital && !wantDigital) || (!marketIsDigital && wantDigital)) {
			market = getRandomMarket();
			marketIsDigital = market.get("virtual_market") != null && market.get("virtual_market").equals(1L);
		}
		
		return market;
	}
	
	public List<Map<String, Object>> getRandomMarkets(int count) {
		List<Map<String, Object>> returnMarkets = new ArrayList<Map<String, Object>>();
		
		// fetch the correct count of markets from all markets that meet the metadata criteria
		Random random = new Random();
		if(markets.size() <= count) {
			return markets;
		} else {
			while(returnMarkets.size() < count) {
				int index = random.nextInt(markets.size());
				if(!returnMarkets.contains(markets.get(index))) {
					returnMarkets.add(markets.get(index));
				}
			}
		}
		
		return returnMarkets;
	}	
}