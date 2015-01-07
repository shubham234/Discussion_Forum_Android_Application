package com.example.Mainfiles;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.os.StrictMode;

import com.example.Libraryfiles.DatabaseHandler;
import com.example.Libraryfiles.UserFunctions;
import com.example.androidhive.R;

public class LoginActivity extends Activity implements OnItemSelectedListener {
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	EditText inputEnroll;
	TextView loginErrorMsg;
	Spinner spinner;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ID = "enrollment_num";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CONTACT = "contact";
	private static String KEY_BRANCH = "branch";
	private static String KEY_YEAR = "year";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		

		// Importing all assets like buttons, text fields
		//inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);
		spinner = (Spinner) findViewById(R.id.spinner1);
		inputEnroll = (EditText) findViewById(R.id.loginEmail);
		//btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

		spinner.setOnItemSelectedListener(this);
		List<String> categories = new ArrayList<String>();
		categories.add("Student");
		categories.add("Admin");
		ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);
		dataadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataadapter);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				//String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				long enrollment = Long.valueOf(inputEnroll.getText().toString());
				Log.i("login",password + enrollment);
				if(spinner.getSelectedItem().toString()== "Student" && enrollment!=1234567890)
				{
				UserFunctions userFunction = new UserFunctions();
				Log.d("Button", "Login");
				JSONObject json = userFunction.loginUser(enrollment , password);
				Log.i("json","got");

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						Log.i("key_success","true");
						loginErrorMsg.setText("");
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
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getLong(KEY_ID),json_user.getString(KEY_NAME),
									json_user.getString(KEY_EMAIL),
									json_user.getLong(KEY_CONTACT),
									json_user.getString(KEY_BRANCH),json_user.getString(KEY_YEAR));
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
							loginErrorMsg
									.setText("Incorrect username/password");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
				else if(spinner.getSelectedItem().toString() == "Admin" && enrollment == 1234567890)
				{
					UserFunctions userFunction = new UserFunctions();
					Log.d("Button", "Login");
					JSONObject json = userFunction.loginUser(enrollment , password);
					Log.i("json","got");

					// check for login response
					try {
						if (json.getString(KEY_SUCCESS) != null) {
							Log.i("key_success","true");
							loginErrorMsg.setText("");
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
								userFunction.logoutUser(getApplicationContext());
								db.addUser(json_user.getLong(KEY_ID),json_user.getString(KEY_NAME),
										json_user.getString(KEY_EMAIL),
										json_user.getLong(KEY_CONTACT),
										json_user.getString(KEY_BRANCH),json_user.getString(KEY_YEAR));
								Log.i("sqlite","added");

								// Launch Dashboard Screen
								Intent dashboard = new Intent(
										getApplicationContext(),
										RegisterActivity.class);

								// Close all views before launching Dashboard
								dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(dashboard);

								// Close Login Screen
								finish();
							} else {
								// Error in login
								loginErrorMsg
										.setText("Incorrect username/password");
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				else
				{
					loginErrorMsg.setText("Please enter a valid username/password/usertype");
				}
			}
		});
		
		/*btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
				
			}
		});*/

	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

		String item = arg0.getItemAtPosition(arg2).toString();
		Toast.makeText(arg0.getContext(), " UserType " + item + " Selected",
				Toast.LENGTH_LONG).show();

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	
	
}
