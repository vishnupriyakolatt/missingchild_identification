package com.example.missingchild;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class View_criminal extends Activity {
ListView l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_criminal);
		l1=(ListView)findViewById(R.id.listView1);
		new getjson2().execute();
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),View_criminalimg.class);
				
				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);				
				i.putExtra("name", hmap.get("name"));
				i.putExtra("img", hmap.get("cr_img"));
				
				Toast.makeText(getApplicationContext(), hmap.get("cr_img"), 3).show();
				
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_criminal, menu);      
		return true;
	}
	private class getjson2 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
//					String url = "http://192.168.43.84:8000/criminaldetails/android/";
					String url = Login.url+"criminaldetails/android/";

//					String url = Registration.url+"doctor_reg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String date = c.getString("date");
							String name= c.getString("name");
							String age= c.getString("age");
							String personal= c.getString("personal");
							String case_details= c.getString("case_details");
							String img= c.getString("cr_img");
							String station= c.getString("station");
							
							
							
							HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("date", date);
		                    contact.put("name", name);
		                    contact.put("age", age);
		                    contact.put("personal", personal);
		                    contact.put("case_details", case_details);
		                    contact.put("cr_img", img);
		                    contact.put("station", station);
		                    
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
			         
			         Toast.makeText(getApplicationContext(), "SUCCESSFUL", 3).show();
			         
			         ListAdapter adapter = new SimpleAdapter(View_criminal.this, al,
			            R.layout.view_criminal, new String[]{ "date","name","age","personal","case_details","station","img"}, 
			               new int[]{R.id.textView4,R.id.textView6,R.id.textView8,R.id.textView10,R.id.textView12,R.id.textView14,});
			         l1.setAdapter(adapter);
			      }
			}

}
