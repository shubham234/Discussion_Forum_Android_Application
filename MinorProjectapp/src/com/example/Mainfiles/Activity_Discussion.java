package com.example.Mainfiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidhive.R;

public class Activity_Discussion extends Activity{
Button ask_question;
Button view_forum,answerquestion;
TextView quote;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum);
		ask_question = (Button) findViewById(R.id.btn_ask);
		view_forum = (Button) findViewById(R.id.btn_view);
		quote = (TextView) findViewById(R.id.tv_quote);
		ask_question.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent = new Intent(Activity_Discussion.this,Ask.class);	
			startActivity(intent);
			}
		});
		view_forum.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent inten = new Intent(Activity_Discussion.this,View_answer.class);	
			startActivity(inten);
			}
		});
		/*answerquestion.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(Activity_Discussion.this,Ans_the_ques.class);
				DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
				ArrayList<Columns> log = handler.getAllData();
	        	Columns bean = log.get(0);
	        	String name = bean.getName();
	        	Log.i("name is",name);
	        	String enroll = bean.getEnrollment();
	        	Log.i("enrollment is",enroll);
				inte.putExtra("name", name);
				inte.putExtra("enrollment", enroll);
				startActivity(inte);
			}
		});*/
	}
	

}
