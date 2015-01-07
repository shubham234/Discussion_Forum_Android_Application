package com.example.Mainfiles;

public class Ans {
	int question_id;
	int a_id;
	String a_name;
	long a_enroll;
	String a_answer;
	String datetime;
	public Ans(int ques,int a_id,String name,long enroll,String answer,String datetime)
	{
		this.question_id=ques;
		this.a_id=a_id;
		this.a_name=name;
		this.a_enroll=enroll;
		this.a_answer=answer;
		this.datetime=datetime;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public int getA_id() {
		return a_id;
	}
	public String getA_name() {
		return a_name;
	}
	public long getA_enroll() {
		return a_enroll;
	}
	public String getA_answer() {
		return a_answer;
	}
	public String getDatetime() {
		return datetime;
	}
	

}
