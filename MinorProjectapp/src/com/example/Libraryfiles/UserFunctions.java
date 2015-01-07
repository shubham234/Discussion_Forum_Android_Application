package com.example.Libraryfiles;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
	private static String loginURL = "http://10.0.2.2/android/";
	private static String registerURL = "http://10.0.2.2/android/";
	private static String discussionURL = "http://10.0.2.2/android/";
	private static String answer_URL = "http://10.0.2.2/android/";
	private static String reply_URL = "http://10.0.2.2/android/";
	
	private static String login_tag = "student_details";
	private static String register_tag = "register";
	private static String discussion_tag = "discussion";
	private static String answer_tag = "discussion_answer";
	private static String updt_reply = "update_reply";
	

	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	

	public JSONObject loginUser(long enroll , String password){

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("enrollment_num",String.valueOf(enroll)));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);

		 Log.e("JSON", json.toString());
		return json;
	}
	

	public JSONObject registerUser(long enroll, String name, String email, String password , long contact , String branch , String year){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("enrollment_num", String.valueOf(enroll)));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("contact", String.valueOf(contact)));
		params.add(new BasicNameValuePair("branch", branch));
		params.add(new BasicNameValuePair("year", year));
		
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		Log.i("done","done");

		return json;
	}
	
	public JSONObject insert_into_forumquestion(String topic,String detail,String name,long enrollment,String datetime,int view,int reply)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", discussion_tag));
		params.add(new BasicNameValuePair("topic", topic));
		params.add(new BasicNameValuePair("detail", detail));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("enroll", String.valueOf(enrollment)));
		params.add(new BasicNameValuePair("datetime", datetime));
		params.add(new BasicNameValuePair("view", Integer.toString(view)));
		params.add(new BasicNameValuePair("reply", Integer.toString(reply)));
		
		JSONObject json = jsonParser.getJSONFromUrl(discussionURL, params);
		return json;
	}
	
	public JSONObject insert_into_forumanswer(int ques_id,String name,long enroll,String answer,String datetime)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", answer_tag));
		params.add(new BasicNameValuePair("question_id", Integer.toString(ques_id)));
		params.add(new BasicNameValuePair("a_name", name));
		params.add(new BasicNameValuePair("a_enroll", Long.toString(enroll)));
		params.add(new BasicNameValuePair("a_answer", answer));
		params.add(new BasicNameValuePair("a_datetime", datetime));
		Log.i("answer in userfunction is", answer);
		
		JSONObject json = jsonParser.getJSONFromUrl(answer_URL, params);
		return json;
	}
	
	public JSONObject update_reply(int count,int q_id)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", updt_reply));
		params.add(new BasicNameValuePair("reply", Integer.toString(count)));
		params.add(new BasicNameValuePair("id", Integer.toString(q_id)));
		JSONObject json = jsonParser.getJSONFromUrl(reply_URL, params);
		return json;
	}
	

	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	

	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
}
