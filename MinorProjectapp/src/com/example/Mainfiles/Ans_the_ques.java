package com.example.Mainfiles;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Libraryfiles.DatabaseHandler;
import com.example.Libraryfiles.UserFunctions;
import com.example.androidhive.R;

public class Ans_the_ques extends Activity{
Button btn_sbmt;
EditText ans;
TextView id;
int reply_count;
int que_id; 
private static String KEY_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_question);
		Bundle bundle = getIntent().getExtras();
		final int que_id = bundle.getInt("q_id");
		id = (TextView) findViewById(R.id.tv_ques_id);
		ans = (EditText) findViewById(R.id.et_answer);
		btn_sbmt = (Button) findViewById(R.id.btn_ans_submit);
		id.setText(String.valueOf(que_id), null);
		btn_sbmt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
			UserFunctions userfunctions = new UserFunctions();
			ArrayList<Columns> log = handler.getAllData();
			int reply = handler.getanswercount(que_id);
			Log.i("replies count is",String.valueOf(reply));
			int reply_count = reply+1;
			//handler.update_reply_count(reply_count,que_id);
	    	Columns bean = log.get(0);
	    	String name = bean.getName();
	    	//Log.i("name is",name);
	    	long enroll = bean.getEnrollment();
			String answ = ans.getText().toString();
			Log.i("answer is",answ);
			//int q_id = Integer.parseInt(id.getText().toString());
			String datetime = DateFormat.getDateTimeInstance().format(new Date());
			JSONObject jsons = userfunctions.update_reply(reply_count,que_id);
			try
			{
				if (jsons.getString(KEY_SUCCESS) != null) {
					//Log.i("key_success","true");
					String res = jsons.getString(KEY_SUCCESS);
					if (Integer.parseInt(res) == 1) {
						// user successfully logged in
						// Store user details in SQLite Database
						//Log.i("parse int","done");
						JSONObject json_user = jsons.getJSONObject("user");
						handler.update_reply_count(reply_count,que_id );
			}
					else
					{
						Log.i("error","occured in reply update");
					}
				}
			}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			JSONObject json = userfunctions.insert_into_forumanswer(que_id,name,enroll,ans.getText().toString(),datetime);
			try {
				if (json.getString(KEY_SUCCESS) != null) {
					//Log.i("key_success","true");
					String res = json.getString(KEY_SUCCESS);
					if (Integer.parseInt(res) == 1) {
						// user successfully logged in
						// Store user details in SQLite Database
						//Log.i("parse int","done");
						JSONObject json_user = json.getJSONObject("user");
						//Log.i("got json user","done");
			handler.addanswer(json_user.getInt("question_id"),json_user.getString("a_name"),json_user.getLong("a_enroll"),json_user.getString("a_answer"),json_user.getString("a_datetime"));
			Intent intent = new Intent(Ans_the_ques.this,View_answer.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			}
					else
					{
						Log.i("error","occured");
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			}
		});
	}
	

}
