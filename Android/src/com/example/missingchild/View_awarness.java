package com.example.missingchild;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class View_awarness extends Activity {
	ListView lv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_awarness);
		lv1=(ListView)findViewById(R.id.listView1);
		new getjson2().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_awarness, menu);
		return true;
	}
	private class getjson2 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
//					String url = "http://192.168.43.84:8000/awareness/android/";
					String url = Login.url+"awareness/android/";

//					String url = Registration.url+"doctor_reg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String a_date = c.getString("a_date");
							String a_title= c.getString("a_title");
							String a_details= c.getString("a_details");
							
							
							HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("a_date", a_date);
		                    contact.put("a_title", a_title);
		                    contact.put("a_details", a_details);
		                    
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
			         
			         ListAdapter adapter = new SimpleAdapter(View_awarness.this, al,
			            R.layout.view_awarness, new String[]{ "a_date","a_title","a_details"}, 
			               new int[]{R.id.textView4,R.id.textView6,R.id.textView8});
			         lv1.setAdapter(adapter);
			      }
			}

}
