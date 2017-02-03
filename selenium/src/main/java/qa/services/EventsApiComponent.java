package qa.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.amazonaws.util.json.JSONException;
import qa.SeleniumTest;
import qa.utility.bizjdb.DatabaseTool;
import qa.utility.bizjdb.QueryBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EventsApiComponent {

	private DatabaseTool dbTool;
	private MessageDigest md5;
	private String token;
	private String contentType;
	private String accept;
	private String signature;
	private String body;

	public EventsApiComponent(HashMap<String, String> data) {
		super();
		this.token = data.get("auth");
		this.contentType = data.get("accept");
		this.accept = data.get("accept");
		JSONObject event = new JSONObject(data.get("body"));
		try {
			this.body = updateEvent(event);// data.get("body");//testString
			// TODO - data.get("body")
			byte[] bytesOfMessage = (data.get("secret") + ":" + this.body).getBytes("UTF-8");
			md5 = MessageDigest.getInstance("MD5");
			this.signature = toHexString(md5.digest(bytesOfMessage));
		} catch (Exception ex) {
			SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
			Assert.fail(ex.getMessage());
		}
	}	

	/**
	 * Updates event json with unique id's
	 * 
	 * @param json
	 * @throws Exception
	 */
	private String updateEvent(JSONObject json) throws Exception {
		// create new db tool and query
		dbTool = new DatabaseTool();
		QueryBuilder eventId = new QueryBuilder();

		// set colums and query
		eventId.setColumns(new String[] { "MAX(event_id)" });
		eventId.setQuery("select MAX(event_id) from bizj.ev_api_log ");
		dbTool.execute(eventId);
		int max = Integer.parseInt(dbTool.getResultSet().get(0).get(eventId.getColumns()[0]));
		max++;

		// iterate through json & update event id's
		json = keyReplace(json, "id", max);
		json = keyReplace(json, "event_id", max);

		// json.put("id", max);
		return json.toString();
	}

	/**
	 * The keyReplace method replace current json key values with new json key values.
	 * 
	 * @param obj - json object.
	 * @param keyMain - the key to replace.
	 * @param newValue - the new value of the key to be replaced.
	 * @return
	 * @throws Exception
	 */
	private JSONObject keyReplace(JSONObject obj, String keyMain, int newValue) throws Exception {
		// We need to know keys of Jsonobject
		JSONObject json = new JSONObject();
		Iterator<String> iterator = obj.keys();
		String key = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			// if object is just string we change value in key
			if ((obj.optJSONArray(key) == null) && (obj.optJSONObject(key) == null)) {
				if ((key.equals(keyMain))) {
					// put new value
					obj.put(key, newValue);
					return obj;
				}
			}

			// if it's jsonobject
			if (obj.optJSONObject(key) != null) {
				keyReplace(obj.getJSONObject(key), keyMain, newValue);
			}

			// if it's jsonarray
			if (obj.optJSONArray(key) != null) {
				JSONArray jArray = obj.getJSONArray(key);
				for (int i = 0; i < jArray.length(); i++) {
					keyReplace(jArray.getJSONObject(i), keyMain, newValue);
				}
			}
		}
		return obj;
	}

	/**
	 * Private method for returning a proper MD5 hex string
	 * 
	 * @param bytes
	 * @return - returns the string format of the MD5 hash
	 */
	private String toHexString(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * This method sends a post request to events API to verify the status code
	 * that signifies a proper squadup event has been created.
	 * 
	 * @param restURL - events api URL
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public void testPostStatusCode(String restURL) throws ClientProtocolException, IOException, JSONException {
		// Create Object and pass the url
		HttpPost post = new HttpPost(restURL);
		StringEntity entity = new StringEntity(this.body);
		entity.setContentType(this.contentType);
		post.setEntity(entity);
		HttpUriRequest request = post;

		// add headers to the request
		request.addHeader("Authorization", this.token);
		request.addHeader("Accept", this.accept);
		request.addHeader("Content-Type", this.contentType);
		request.addHeader("Signature", this.signature);

		// send the response or execute the request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());

		// Verify content
		SeleniumTest.logger.info(result + System.lineSeparator());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
		Assert.assertTrue("cannot confirm success!", result.contains("success"));
		SeleniumTest.logger.info("Request succesfully received " + restURL + System.lineSeparator());
	}

	/**
	 * This method will delete the last squadup event created. This method should be used in conjunction
	 * with testPostStatusCode to ensure that only events created by automated scripts get deleted.
	 * 
	 * @param restURL - events api URL
	 * @throws SQLException
	 * @throws IOException
	 */
	public void testDeleteStatusCode(String restURL) throws SQLException, IOException {

		// create new db tool and query
		dbTool = new DatabaseTool();
		QueryBuilder eventId = new QueryBuilder();

		// set colums and query
		eventId.setColumns(new String[] { "MAX(event_id)" });
		eventId.setQuery("select MAX(event_id) from bizj.ev_api_log ");
		dbTool.execute(eventId);
		int max = Integer.parseInt(dbTool.getResultSet().get(0).get(eventId.getColumns()[0]));
		restURL += "/" + max;
		EventsHttpDelete delete = new EventsHttpDelete(restURL);
		StringEntity entity = new StringEntity(this.body);
		entity.setContentType(this.contentType);
		delete.setEntity(entity);
		HttpUriRequest request = delete;

		// add headers to the request
		request.addHeader("Authorization", this.token);
		request.addHeader("Accept", this.accept);
		request.addHeader("Content-Type", this.contentType);
		request.addHeader("Signature", this.signature);

		// send the response or execute the request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());

		System.out.println(result);

		// Verify content
		SeleniumTest.logger.info(result + System.lineSeparator());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
		Assert.assertTrue("cannot confirm success!", result.contains("success"));
		SeleniumTest.logger.info("Request succesfully received: " + restURL + System.lineSeparator());
	}

	/**
	 * 
	 * This method will update the last squadup event created. This method should be used in conjunction
	 * with testPostStatusCode to ensure that only events created by automated scripts get updated.
	 
	 * 
	 * @param restURL - events api URL
	 * @param data - the services hashmap is passed because the signature for the api request will need to be recreated.
	 * @throws Exception
	 */
	public void testUpdateStatusCode(String restURL, HashMap<String,String> data) throws Exception {
		// create new db tool and query
		dbTool = new DatabaseTool();
		QueryBuilder eventId = new QueryBuilder();

		// set colums and query
		eventId.setColumns(new String[] {"MAX(event_id)"});
		eventId.setQuery("select MAX(event_id) from bizj.ev_api_log ");
		dbTool.execute(eventId);
		int max = Integer.parseInt(dbTool.getResultSet().get(0).get(eventId.getColumns()[0]));
		restURL += "/" + max;
		JSONObject event = new JSONObject(this.body);
		event.getJSONObject("event").put("title", max+" updated");
		
		//update signature
		byte[] bytesOfMessage = (data.get("secret") + ":" + event.toString()).getBytes("UTF-8");
		md5 = MessageDigest.getInstance("MD5");
		this.signature = toHexString(md5.digest(bytesOfMessage));

		// Create Object and pass the url
		HttpPut put = new HttpPut(restURL);
		StringEntity entity = new StringEntity(event.toString());
		entity.setContentType(this.contentType);
		put.setEntity(entity);
		HttpUriRequest request = put;

		// add headers to the request
		request.addHeader("Authorization", this.token);
		request.addHeader("Accept", this.accept);
		request.addHeader("Content-Type", this.contentType);
		request.addHeader("Signature", this.signature);

		// send the response or execute the request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());

		// Verify content
		SeleniumTest.logger.info(result + System.lineSeparator());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
		Assert.assertTrue("cannot confirm success!", result.contains("success"));
		SeleniumTest.logger.info("Request succesfully received " + restURL + System.lineSeparator());
	}
	
}
