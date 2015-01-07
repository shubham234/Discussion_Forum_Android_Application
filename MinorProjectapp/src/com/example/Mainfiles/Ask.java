package com.example.Mainfiles;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.Libraryfiles.DatabaseHandler;
import com.example.Libraryfiles.UserFunctions;
import com.example.androidhive.R;

public class Ask extends Activity{
UserFunctions userfunctions;
EditText topic;
EditText detail;
Button btnsubmit;
DatabaseHandler handler;
private static String KEY_SUCCESS = "success";
private static String KEY_TOPIC = "topic";
private static String KEY_DETAIL = "detail";
private static String KEY_NAME = "name";
private static String KEY_ENROLL = "enroll";
private static String KEY_DATETIME = "datetime";
private static String KEY_VIEW = "view";
private static String KEY_REPLY = "reply";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask_question);
		topic = (EditText) findViewById(R.id.ask_et_topic);
		detail = (EditText) findViewById(R.id.ask_et_question);
		btnsubmit = (Button) findViewById(R.id.ask_submit);
		btnsubmit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler = new DatabaseHandler(getApplicationContext());
				ArrayList<Columns> log = handler.getAllData();
		    	Columns bean = log.get(0);
		    	String name = bean.getName();
		    	Log.i("name is",name);
		    	long enrollment = bean.getEnrollment();
		    	int view =0;
		    	int reply = 0;
		    	String datetime = DateFormat.getDateTimeInstance().format(new Date());
		    	userfunctions = new UserFunctions();
		    	JSONObject json = userfunctions.insert_into_forumquestion(topic.getText().toString(), detail.getText().toString(), name, enrollment, datetime, view, reply);
		    	
		    	try {
					if (json.getString(KEY_SUCCESS) != null) {
						Log.i("key_success","true");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							// user successfully logged in
							// Store user details in SQLite Database
							Log.i("parse int","done");
							DatabaseHandler db = new DatabaseHandler(
									getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							Log.i("got json user","done");

							// Clear all previous data in database
							db.addquestion(json_user.getString(KEY_TOPIC),json_user.getString(KEY_DETAIL),
									json_user.getString(KEY_NAME),
									json_user.getLong(KEY_ENROLL),
									json_user.getString(KEY_DATETIME),json_user.getInt(KEY_VIEW),json_user.getInt(KEY_REPLY));
							Log.i("sqlite","added");

							// Launch Dashboard Screen
							Intent dashboard = new Intent(
									getApplicationContext(),
									DashboardActivity.class);

							// Close all views before launching Dashboard
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);

							// Close Login Screen
							finish();
						} else {
							// Error in login
							Log.i("error","occured");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
	
			}
		});
			}
	

}
