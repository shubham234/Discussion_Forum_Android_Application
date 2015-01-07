package com.example.Mainfiles;

public class Answers {
	int id;
	String topic;
	String detail;
	String name;
	long enrollment;
	String datetime;
	int view;
	int reply;
	public Answers(int id,String topic,String detail,String name,long enroll,String datetime,int view,int reply)
	{
		this.id=id;
		this.topic=topic;
		this.detail=detail;
		this.name=name;
		this.enrollment=enroll;
		this.datetime=datetime;
		this.view=view;
		this.reply=reply;
	}
	public int getId() {
		return id;
	}
	public String getTopic() {
		return topic;
	}
	public String getDetail() {
		return detail;
	}
	public String getName() {
		return name;
	}
	public long getEnrollment() {
		return enrollment;
	}
	public String getDatetime() {
		return datetime;
	}
	public int getView() {
		return view;
	}
	public int getReply() {
		return reply;
	}
	
	

}
