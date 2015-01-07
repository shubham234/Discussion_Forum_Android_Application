package com.example.Libraryfiles;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Mainfiles.Ans;
import com.example.Mainfiles.Answers;
import com.example.Mainfiles.Columns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "android.db";

	// Login table name
	private static final String TABLE_LOGIN = "student_details";
	private static final String TABLE_QUESTION = "forum_question";
	private static final String TABLE_ANSWER = "forum_answer";

	private static final String KEY_ID = "enrollment_num";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_CONTACT = "contact";
	private static final String KEY_BRANCH = "branch";
	private static final String KEY_YEAR = "year";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("database", "created");
		/*
		 * String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "(" //+
		 * KEY_UID + "INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ID +
		 * " TEXT UNIQUE," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT UNIQUE," +
		 * KEY_CONTACT + " TEXT UNIQUE, " + KEY_BRANCH + " TEXT," + KEY_YEAR +
		 * " TEXT" + ")";
		 */
		String CREATE_TABLE = "CREATE TABLE " + TABLE_LOGIN + "(" + KEY_ID
				+ " INTEGER UNIQUE," + KEY_NAME + " TEXT," + KEY_EMAIL
				+ " TEXT UNIQUE," + KEY_CONTACT + " INTEGER UNIQUE, " + KEY_BRANCH
				+ " TEXT," + KEY_YEAR + " TEXT" + ")";

		String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + "("
				+ " id	INTEGER PRIMARY KEY AUTOINCREMENT," + " topic	TEXT,"
				+ " detail	TEXT," + " name	TEXT," + " enrollment	INTEGER,"
				+ " datetime	TEXT," + " view	INTEGER," + " reply	INTEGER" + ");";
		String CREATE_ANSWER_TABLE = "CREATE TABLE " + TABLE_ANSWER + "("
				+ " question_id	INTEGER," + " a_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " a_name	TEXT," + " a_enrollment	INTEGER," + " a_answer	TEXT,"
				+ " a_datetime	TEXT" + ");";
		db.execSQL(CREATE_TABLE);
		db.execSQL(CREATE_QUESTION_TABLE);
		db.execSQL(CREATE_ANSWER_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(long enroll, String name, String email,
			long contact, String branch, String year) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, enroll); // Enrollment_num
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_CONTACT, contact); // Contact
		values.put(KEY_BRANCH, branch); // Branch
		values.put(KEY_YEAR, year); // Year

		// Inserting Row
		db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection
	}
	
	public void addanswer(int q_id,String name,long enroll,String answer,String datetime)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("question_id",q_id);
		values.put("a_name",name);
		values.put("a_enrollment",enroll);
		values.put("a_answer",answer);
		values.put("a_datetime",datetime);
		
		db.insert(TABLE_ANSWER, null, values);
		db.close();
	}

	public void addquestion(String topic, String detail, String name,
			long enroll, String datetime, int view, int reply) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("topic", topic);
		values.put("detail", detail);
		values.put("name", name);
		values.put("enrollment", enroll);
		values.put("datetime", datetime);
		values.put("view", view);
		values.put("reply", reply);

		db.insert(TABLE_QUESTION, null, values);
		db.close();
	}
	
	/*public void addanswer(int ques_id , int a_id , String name , long enroll , String a_answer , String datetime)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("question_id", ques_id);
		values.put("a_id", a_id);
		values.put("a_name", name);
		values.put("a_enroll", enroll);
		values.put("a_answer", a_answer);
		values.put("a_datetime", datetime);
		
		db.insert(TABLE_ANSWER, null, values);
		db.close();
	}*/

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("enrollment_num", String.valueOf(cursor.getLong(0)));
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("contact", String.valueOf(cursor.getLong(3)));
			user.put("branch", cursor.getString(4));
			user.put("year", cursor.getString(5));
		}
		cursor.close();
		db.close();
		// return user
		return user;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}
	
	public int getanswercount(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM forum_answer WHERE question_id = ? ", new String []{String.valueOf(id)});
		int rowcount = cursor.getCount();
		db.close();
		cursor.close();
		return rowcount;
	}
	
	public void update_reply_count(int replycount,int ques_id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("reply", replycount);
		db.update(TABLE_QUESTION, values, "id = ?", new String []{String.valueOf(ques_id)});
		db.close();
		
		
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
		db.close();
	}

	public ArrayList<Columns> getAllData() {
		ArrayList<Columns> arraylist = new ArrayList<Columns>();
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database
				.rawQuery("select * from student_details", null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			arraylist.add(new Columns(cursor.getLong(0), cursor.getString(1),
					cursor.getString(2), cursor.getLong(3), cursor
							.getString(4), cursor.getString(5)));

			//Log.i("values come from data base is",
				//	"--------" + cursor.getString(0) + " "
					//		+ cursor.getString(1));
		}
		database.close();
		return arraylist;
	}
	
	public ArrayList<Answers> getallques()
	{
		ArrayList<Answers> arraylist = new ArrayList<Answers>();
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery("select * from forum_question", null);
		Log.i("cursor,count",String.valueOf(cursor.getCount()));
		cursor.moveToFirst();
		//Log.i("value come from data base is", "--------"+ cursor.getString(2));
		if(cursor.getCount() > 0){
			do{
				//int i=cursor.getInt(0);
				arraylist.add(new Answers(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getLong(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7)));
				//Log.i("value come from data base is", "--------"+cursor.getString(1) + cursor.getString(2));
			}while(cursor.moveToNext());
	}
		database.close();
		return arraylist;
	}
	
	public ArrayList<Ans> getanswer(int id)
	{
		ArrayList<Ans> arraylist = new ArrayList<Ans>();
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery("select * from forum_answer where question_id = ?", new String[] {String.valueOf(id)});
		cursor.moveToFirst();
		if(cursor.getCount() > 0){
			do{
				arraylist.add(new Ans(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getLong(3),cursor.getString(4),cursor.getString(5)));				
			}while(cursor.moveToNext());
		}
		database.close();
		return arraylist;
	}

}