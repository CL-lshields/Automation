package qa.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChannelHelper {
	
	private static final String[] channels = {
		"Banking & Financial Services",
		"Food & Lifestyle",
		"Career & Workplace",
		"Commercial Real Estate",
		"Education",
		"Energy",
		"Government & Regulations",
		"Health Care",
		"Manufacturing",
		"Media & Marketing",
		"Philanthropy & Nonprofits",
		"Professional Services",
		"Residential Real Estate",
		"Retailing",
		"Sports Business",
		"Technology",
		"Transportation",
		"Travel & Tourism"
	};
	
	public static String getRandomChannel() {
		Random rand = new Random();
		
		int index = rand.nextInt(channels.length);
		
		return channels[index];
	}
	
	public static List<String> getRandomChannels(int count) {
		Random rand = new Random();
		List<String> randomChannels = new ArrayList<String>();
		int counter = 0;
		
		while(counter < count) {
			int index = rand.nextInt(channels.length);
			String channel = channels[index];
			
			if(!randomChannels.contains(channel)) {
				randomChannels.add(channel);
				counter++;
			}
			
		}
		
		System.out.println(randomChannels.toString());
		
		return randomChannels;		
	}
	
}