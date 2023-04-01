package com.example.missingchild;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.missingchild.U_register.savejson;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Post_complaint extends Activity {
 EditText e1;
 Button b1,b2;
 ListView lv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_complaint);
		e1=(EditText)findViewById(R.id.editText3);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		lv1=(ListView)findViewById(R.id.listView1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals(""))
		        {
		        new savejson().execute();
		        }
		        else
		        {
		          Toast.makeText(getApplicationContext(), "fill", 3).show();
		        }
			}	
			
		});
         b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new getjson2().execute();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_complaint, menu);
		return true;
	}
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
//			String url = "http://192.168.43.84:8000/complaint/android/";
			String url = Login.url+"complaint/android/";
			
			JSONObject jobj= new JSONObject();
	        try {
	   
				jobj.put("complaint",e1.getText().toString()); 
				jobj.put("user_id","1");  

				
				JsonHandler.Postjson(url, jobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null; 
		}
	
		@Override
	      protected void onPostExecute(Void result) {
	         super.onPostExecute(result);
	    
	        
	        	 Toast.makeText(getApplicationContext(), "SUCCESSFUL", 3).show();    
					Intent i= new Intent(getApplicationContext(),Home.class);
						startActivity(i);
	         
	      }
	}
	
	
	
	private class getjson2 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
//					String url = "http://192.168.43.84:8000/complaint/android/";
					String url = Login.url+"complaint/android/";
					

//					String url = Registration.url+"doctor_reg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String complaint = c.getString("complaint");
							String c_date= c.getString("c_date");
							String c_reply= c.getString("c_reply");
							
							
							HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("complaint", complaint);
		                    contact.put("c_date", c_date);
		                    contact.put("c_reply", c_reply);
		                    
//				
		                    

//		                if(hospid_id.equals(id))
//		                {
		                	al.add(contact);
//		                }
		              
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "No information yet...", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No information yet...", 3).show();
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
			         Toast.makeText(getApplicationContext(), "COMPLAINT", 3).show();
			         
			         ListAdapter adapter = new SimpleAdapter(Post_complaint.this, al,
			            R.layout.post_complaint, new String[]{ "c_date","complaint","c_reply"}, 
			               new int[]{R.id.textView4,R.id.textView6,R.id.textView8});
			         lv1.setAdapter(adapter);
			      }
			}

}
