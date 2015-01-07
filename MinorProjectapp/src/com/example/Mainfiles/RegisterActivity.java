package com.example.Mainfiles;
 
import org.json.JSONException;
import org.json.JSONObject;
 
import com.example.Libraryfiles.DatabaseHandler;
import com.example.Libraryfiles.UserFunctions;
import com.example.androidhive.R;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class RegisterActivity extends Activity {
    Button btnRegister;
    Button btnLinkToLogin;
    EditText inputEnroll;
    EditText inputName;
    EditText inputEmail;
    EditText inputPassword;
    EditText inputYear;
    EditText inputBranch;
    EditText inputContact;
    TextView registerErrorMsg;
     
    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ENROLL = "enrollment_num";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CONTACT = "contact";
    private static String KEY_BRANCH = "branch";
    private static String KEY_YEAR = "year";
    		
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
 
        // Importing all assets like buttons, text fields
        
        inputEnroll = (EditText) findViewById(R.id.etenroll);
        inputName = (EditText) findViewById(R.id.etname);
        inputEmail = (EditText) findViewById(R.id.etemail);
        inputContact = (EditText) findViewById(R.id.etcontact);
        inputBranch = (EditText) findViewById(R.id.etbranch);
        inputYear = (EditText) findViewById(R.id.etyear);
        inputPassword = (EditText) findViewById(R.id.etpassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        registerErrorMsg = (TextView) findViewById(R.id.register_error);
         
        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {         
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String branch = inputBranch.getText().toString();
                String year = inputYear.getText().toString();
                long enrollment_num = Long.valueOf(inputEnroll.getText().toString());
                long contact = Long.valueOf(inputContact.getText().toString());
                Log.i("shubham", "gupta");
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.registerUser(enrollment_num, name, email, password, contact, branch, year);
                Log.i("mani", "gupta");
                // check for login response
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        registerErrorMsg.setText("");
                        String res = json.getString(KEY_SUCCESS); 
                        if(Integer.parseInt(res) == 1){
                            // user successfully registred
                            // Store user details in SQLite Database
                            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");
                             
                            // Clear all previous data in database
                            userFunction.logoutUser(getApplicationContext());
                            db.addUser(json_user.getLong(KEY_ENROLL),json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json_user.getLong(KEY_CONTACT),json_user.getString(KEY_BRANCH), json_user.getString(KEY_YEAR));                        
                            // Launch Dashboard Screen
                            Intent dashboard = new Intent(getApplicationContext(), LoginActivity.class);
                            // Close all views before launching Dashboard
                            dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(dashboard);
                            // Close Registration Screen
                            finish();
                        }else{
                            // Error in registration
                            registerErrorMsg.setText("Error occured in registration");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
 
        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                // Close Registration View
                finish();
            }
        });
    }
}