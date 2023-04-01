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

public class View_casestaus extends Activity {
	ListView lv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_casestaus);
		lv1=(ListView)findViewById(R.id.listView1);
		new getjson2().execute();
		
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),View_missingcase.class);
				
				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);				
				i.putExtra("name", hmap.get("mp_name"));
				i.putExtra("img", hmap.get("m_img"));
				
//				Toast.makeText(getApplicationContext(), hmap.get("cr_img"), 3).show();
				
				startActivity(i);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_casestaus, menu);
		return true;
	}
	private class getjson2 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
//					String url = "http://192.168.43.84:8000/missingcase/android/";
					String url = Login.url+"missingcase/android/";

//					String url = Registration.url+"doctor_reg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String mp_name = c.getString("mp_name");
							String m_age= c.getString("m_age");
							String m_datemis= c.getString("m_datemis");
							String m_datereport= c.getString("m_datereport");
							String m_address= c.getString("m_address");
							String m_status= c.getString("m_status");
							String m_img= c.getString("m_img");
							
							String user_id= c.getString("user_id");
							
							
							HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("mp_name", mp_name);
		                    contact.put("m_age", m_age);
		                    contact.put("m_datemis", m_datemis);
		                    contact.put("m_datereport",m_datereport);
		                    contact.put("m_address", m_address);
		                    contact.put("m_status", m_status);
		                    contact.put("m_img",m_img);
		                    
//				
		                    

		                if(user_id.equals(Login.uid))
		                {
		                	al.add(contact);
		                }
		              
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
			         
			         ListAdapter adapter = new SimpleAdapter(View_casestaus.this, al,
			            R.layout.view_casestaus, new String[]{ "mp_name","m_age","m_datemis","m_datereport","m_address","m_status"}, 
			               new int[]{R.id.textView4,R.id.textView6,R.id.textView8,R.id.textView10,R.id.textView12,R.id.textView14});
			         lv1.setAdapter(adapter);
			      }
			}

}
