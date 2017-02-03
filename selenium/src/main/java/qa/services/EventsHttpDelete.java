package qa.services;

import org.apache.http.client.methods.HttpPost;

public class EventsHttpDelete extends HttpPost {
	
	EventsHttpDelete(String string){
		super(string);
	}
	
	@Override
    public String getMethod() {
        return "DELETE";
    }
}
