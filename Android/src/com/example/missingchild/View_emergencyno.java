package com.example.missingchild;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
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
public class View_emergencyno extends Activity {
ListView lv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_emergencyno);
		lv1=(ListView)findViewById(R.id.listView1);
		new getjson2().execute();
		lv1.setOnItemClickListener(new OnItemClickListener() {

			  

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String,String> hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
			    String num=hmap.get("number");
			    
			    Intent callIntent = new Intent(Intent.ACTION_CALL);
			    callIntent.setData(Uri.parse("tel:"+num));
			    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			    startActivity(callIntent);
			              
			}
			  });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_emergencyno, menu);
		return true;
	}
	private class getjson2 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
//					String url = "http://192.168.43.84:8000/emergency/android/";
					String url = Login.url+"emergency/android/";

//					String url = Registration.url+"doctor_reg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String e_name= c.getString("e_name");
							String e_no= c.getString("e_no");
							
							
							HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("e_name", e_name);
		                    contact.put("e_no", e_no);
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
			         
			         ListAdapter adapter = new SimpleAdapter(View_emergencyno.this, al,
			            R.layout.view_emergencyno, new String[]{ "e_name","e_no"}, 
			               new int[]{R.id.textView4,R.id.textView6});
			         lv1.setAdapter(adapter);
			      }
			}

}
