package qa.utility;

import java.sql.SQLException;
import java.util.*;

public class ChannelList {
	
	private DatabaseUtilities db;
	
	private final String channelSelect = "SELECT C.channel_id, C.channel_name, C.channel_code " +
										 "FROM bizj.channel C " +
										 "WHERE C.is_active = 1 " +
										 "ORDER BY channel_name";
	
	private List<Map<String, Object>> channels;
	
	public ChannelList() {
		db = new DatabaseUtilities();
		loadChannels();
	}
	
	public void loadChannels() {
		try {
			db.createConnection();
			channels = db.query(channelSelect);
			db.closeConnection();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Map<String, Object>> getAllChannels() {
		return channels;
	}
	
	public Map<String, Object> getRandomChannel() {
		Random rand = new Random();
		int index = rand.nextInt(channels.size());
		return channels.get(index);
	}
	
	public List<Map<String, Object>> getRandomChannels(int count) {
		List<Map<String, Object>> randomChannels = new ArrayList<Map<String, Object>>();
		int counter = 0;
		
		while(counter < count) {
			
			Map<String, Object> channel = getRandomChannel();
			
			if(!randomChannels.contains(channel)) {
				randomChannels.add(channel);
				counter++;
			}			
		}
		
		return randomChannels;		
	}
	
	
}