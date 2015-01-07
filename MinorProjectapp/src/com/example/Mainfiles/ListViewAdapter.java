package com.example.Mainfiles;

import com.example.androidhive.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	public Context context;
	   private LayoutInflater inflater;
	   public String[] topic;
	   public String[] detail;
		public ListViewAdapter(Context context,String [] topic,String [] detail) {
			// TODO Auto-generated constructor stub
			//super(context,R.layout.list_layout,topic,detail);
			this.context = context;
			this.topic=topic;
			this.detail=detail;
			inflater =LayoutInflater.from(context);
			Log.i("listviewadapter constructor", "called successfully");
		}

	public int getCount() {
		// TODO Auto-generated method stub
		Log.i("topic length", String.valueOf(topic.length));
		return topic.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


	


	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Log.i("getview function", "called");
		View convertView = arg1;
		
	    if(convertView == null)
           convertView =inflater.inflate(R.layout.list_layout, arg2, false);//late(R.layout.list_layout, null);
	    Log.i("inflating", "done");
		//Holder holder = new Holder();
		//Answers userData = Activity_Discussion.activity.userdata.get(arg0);
		//TextView top = (TextView) convertView.findViewById(R.id.tv_topic);
        //top.setText("shubham");
        Log.i("topic[arg0] is : ", "gupta");
	    //TextView det = (TextView)convertView.findViewById(R.id.tv_detail);
	    //det.setText("mani");
	    Log.i("detail[arg0] is : ","detail");
	    //ImageView backImage = (ImageView) convertView.findViewById(R.id.tv_image);
	    //holder.backImage.setOnClickListener(new clickBackImage(/*userData*/));
	    
	return convertView;
	}
	/*class Holder {
		   TextView topic,detail;
		   ImageView backImage;
	   }*/
	/*class clickBackImage implements OnClickListener {
	    private Answers userData;
		/*public clickBackImage(Answers userData) {
			this.userData = userData;
		}*/

		/*public void onClick(View arg0) {
			// TODO Auto-generated method stub
		Toast.makeText(context, "On clic Name is ", Toast.LENGTH_LONG).show();	
		Log.e("Data come Bean Object is","On clic Name is "+userData.getName());
		}
		   
	   }*/

}