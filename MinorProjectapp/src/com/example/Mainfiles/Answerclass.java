package com.example.Mainfiles;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Libraryfiles.DatabaseHandler;

public class Answerclass extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//ActionBar actionbar = getActionBar();
		try
		{
			Bundle bundle = getIntent().getExtras();
			int id= bundle.getInt("question_id");
			DatabaseHandler database = new DatabaseHandler(getApplicationContext());
			Log.i("question",String.valueOf(id));
			ArrayList<Ans> log = database.getanswer(id);
			if(log.size()!=0)
			{
			
			ArrayList<String> answer = new ArrayList<String>();
			//listdetail = new ArrayList<String>();
			for(int index=0;index<log.size();index++)
			{
				Ans bean=log.get(index);
				Log.i("bean", "called");
				answer.add("Answered By- " + bean.getA_name() + "\nEnrollment No-" + bean.getA_enroll() + "\nAnswer-" + bean.getA_answer());
				//listdetail.add(bean.getDetail());
				String ans = bean.getA_answer();
				Log.i("topic", ans);
				//String detail = bean.getDetail();
				//Log.i("detail", detail);
			}
			String[] answerarray = new String[answer.size()];
		    answerarray = answer.toArray(answerarray);
		    //String[] detailarray = new String[listdetail.size()];
		    //detailarray = listdetail.toArray(detailarray);
		    Log.i("answer", answerarray[0]);
		    //Log.i("detail", detailarray[0]);

			//listview = (ListView)findViewById(R.id.mylist);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answerarray);
			//listview.setAdapter(new ListViewAdapter(this,topicarray,detailarray));
			setListAdapter(adapter);
			}
			else
			{
				Intent intn = new Intent(Answerclass.this,Ans_the_ques.class);
				Toast.makeText(this, "No Answers For this Question", Toast.LENGTH_LONG).show();
				Bundle bundles = getIntent().getExtras();
				int q_id= bundles.getInt("question_id");
				intn.putExtra("q_id", q_id);
				startActivity(intn);
			}
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(Answerclass.this,Ans_the_ques.class);
		Bundle bundle = getIntent().getExtras();
		int q_id= bundle.getInt("question_id");
		Log.i("question id shubham is",String.valueOf(q_id));
		intent.putExtra("q_id", q_id);
		startActivity(intent);
	}
	
	
	}
