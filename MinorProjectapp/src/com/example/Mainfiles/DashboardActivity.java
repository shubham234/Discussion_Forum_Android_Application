package com.example.Mainfiles;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Libraryfiles.DatabaseHandler;
import com.example.Libraryfiles.UserFunctions;
import com.example.androidhive.R;

public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	DatabaseHandler handler;
	Button btnLogout,btnsite;
	Button btnDiscussion,btnwebkiosk;
	TextView tv_name;
	TextView tv_enroll;
	TextView tv_email;
	TextView tv_contact;
	TextView tv_branch;
	TextView tv_year;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.st_profile);
        // Check login status in database
        userFunctions = new UserFunctions();
        handler = new DatabaseHandler(getApplicationContext());
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	
        	Log.i("dashboard", "open");
        	btnLogout = (Button) findViewById(R.id.st_logout);
        	btnDiscussion = (Button) findViewById(R.id.st_discussion);
        	btnwebkiosk = (Button) findViewById(R.id.st_webkiosk);
        	btnsite = (Button) findViewById(R.id.st_website);
        	tv_name = (TextView) findViewById(R.id.st_name);
        	tv_enroll = (TextView) findViewById(R.id.st_enroll);
        	tv_email = (TextView) findViewById(R.id.st_email);
        	tv_contact = (TextView) findViewById(R.id.st_contact);
        	tv_branch = (TextView) findViewById(R.id.st_branch);
        	tv_year = (TextView) findViewById(R.id.st_year);
        	ArrayList<Columns> log = handler.getAllData();
        	Columns bean = log.get(0);
        	String name = bean.getName();
        	Log.i("name is",name);
        	long enroll = bean.getEnrollment();
        	Log.i("enrollment is",String.valueOf(enroll));
        	String email = bean.getEmail();
        	Log.i("email is",email);
        	long contact = bean.getContact();
        	Log.i("contact is",String.valueOf(contact));
        	String branch = bean.getBranch();
        	Log.i("branch is",branch);
        	String year = bean.getYear();
        	Log.i("year is",year);
        	tv_name.setTextColor(Color.parseColor("#c0362c"));
        	tv_name.setText(name);
        	tv_enroll.setTextColor(Color.parseColor("#01a05f"));
        	tv_enroll.setText("Enrollment No- " +enroll);
        	tv_email.setTextColor(Color.parseColor("#01a05f"));
        	tv_email.setText("Email- " +email);
        	tv_contact.setTextColor(Color.parseColor("#01a05f"));
        	tv_contact.setText("Contact No- " +contact);
        	tv_branch.setTextColor(Color.parseColor("#01a05f"));
        	tv_branch.setText("Branch- " +branch);
        	tv_year.setTextColor(Color.parseColor("#01a05f"));
        	tv_year.setText("Year- " +year);
        	btnDiscussion.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
				Intent intent = new Intent(DashboardActivity.this,Activity_Discussion.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				}
			});
        	btnLogout.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getApplicationContext());
    				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
    	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(login);
    	        	// Closing dashboard screen
    	        	finish();
    			}
    		});
        	btnsite.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
				Intent intent = new Intent(DashboardActivity.this,DashboardActivity.class);
				startActivity(intent);
				}
			});
        	btnwebkiosk.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
        	
        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }
        
        
        
        
    }
}