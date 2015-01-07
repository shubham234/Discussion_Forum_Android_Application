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

public class View_answer extends ListActivity{
//ListView listview;
ArrayList<String> listtopic;
ArrayList<String> listdetail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.list_layout);
		try{
			Log.i("Button", "Login");
			DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
			ArrayList<Answers> log = handler.getallques();
			Log.i("all ques get", "done");
			Log.i("log size is",String.valueOf(log.size()));
			if(log.size()!=0)
			{
			/*final ArrayList<String> list = new ArrayList<String>();
		    for (int i = 0; i < values.length; ++i) {
		      list.add(values[i]);
		    }*/
			listtopic = new ArrayList<String>();
			//listdetail = new ArrayList<String>();
			for(int index=0;index<log.size();index++)
			{
				Answers bean=log.get(index);
				Log.i("bean", "called");
				listtopic.add("Topic-" + bean.getTopic() + "       " + "Ques Id-"+ bean.getId() +"\n" + "Question-" + bean.getDetail() + "\nAsked By- " + bean.getName() + "\nEnrollment No-" + bean.getEnrollment() + "\nReplies-" + bean.getReply());
				//listdetail.add(bean.getDetail());
				String topic = bean.getTopic();
				Log.i("topic", topic);
				String detail = bean.getDetail();
				Log.i("detail", detail);
			}
			String[] topicarray = new String[listtopic.size()];
		    topicarray = listtopic.toArray(topicarray);
		    //String[] detailarray = new String[listdetail.size()];
		    //detailarray = listdetail.toArray(detailarray);
		    Log.i("topic", topicarray[0]);
		    //Log.i("detail", detailarray[0]);

			//listview = (ListView)findViewById(R.id.mylist);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topicarray);
			//listview.setAdapter(new ListViewAdapter(this,topicarray,detailarray));
			setListAdapter(adapter);
			}
			else
			{
				Intent in = new Intent(View_answer.this,Ask.class);
				Toast.makeText(this, "No Question Currently", Toast.LENGTH_LONG).show();
				startActivity(in);
				finish();
			}
		}
			catch(Exception e){
				e.printStackTrace();
			}
		
		
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//Toast.makeText(this, String.valueOf(position) + "\n" + String.valueOf(id), Toast.LENGTH_LONG).show();
		//Log.i("topic", String.valueOf(id+1));
		
		Intent intent = new Intent(View_answer.this,Answerclass.class);
		String shubh = String.valueOf(id+1);
		int ids = Integer.parseInt(shubh);
		intent.putExtra("question_id",ids);
		startActivity(intent);
	}
	

}
