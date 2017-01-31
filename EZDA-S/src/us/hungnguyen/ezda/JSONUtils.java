package us.hungnguyen.ezda;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
	private static final String FLAG_SELF = "self";
	private static final String FLAG_NEW = "new";
	private static final String FLAG_MESSAGE = "message";
	private static final String FLAG_USER = "user";
	private static final String FLAG_EXIT = "exit";
	
	public JSONUtils() {
		
	}
	
	public String getClientDetailsJson(String sessionId, String message) {
		String json = null;
		
		try {
			JSONObject jObj = new JSONObject();
			jObj.put("flag", FLAG_SELF);
			jObj.put("sessionId", sessionId);
			jObj.put("message", message);
			
			json = jObj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	public String getNewClientJson(String sessionId, String name,
            String message, int onlineCount) {
        String json = null;
 
        try {
            JSONObject jObj = new JSONObject();
            jObj.put("flag", FLAG_NEW);
            jObj.put("name", name);
            jObj.put("sessionId", sessionId);
            jObj.put("message", message);
            jObj.put("onlineCount", onlineCount);
 
            json = jObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        return json;
    }
	
	/**
     * Json when the client exits the socket connection
     * */
    public String getClientExitJson(String sessionId, String name,
            String message, int onlineCount) {
        String json = null;
 
        try {
            JSONObject jObj = new JSONObject();
            jObj.put("flag", FLAG_EXIT);
            jObj.put("name", name);
            jObj.put("sessionId", sessionId);
            jObj.put("message", message);
            jObj.put("onlineCount", onlineCount);
 
            json = jObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        return json;
    }
    
    /**
     * JSON when message needs to be sent to all the clients
     * */
    public String getSendAllMessageJson(String sessionId, String fromName,
            String message) {
        String json = null;
 
        try {
            JSONObject jObj = new JSONObject();
            jObj.put("flag", FLAG_MESSAGE);
            jObj.put("sessionId", sessionId);
            jObj.put("name", fromName);
            jObj.put("message", message);
 
            json = jObj.toString();
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        return json;
    }
	
}
