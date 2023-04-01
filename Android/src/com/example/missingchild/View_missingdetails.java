package com.example.missingchild;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class View_missingdetails extends Activity {
	ListView lv1;
	String[]txt1;
	Bitmap imgtmp[];
	Context cn;
//	ImageView image;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_missingdetails);
		lv1=(ListView)findViewById(R.id.listView1);
		new getjson2().execute();

		try
    	{
    		if (android.os.Build.VERSION.SDK_INT > 9) 
    		{
    			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy);
    		}
    	}
    	catch(Exception e)
    	{
    		
    	}
		
		
		//show images
		
	    ImageView image = (ImageView) findViewById(R.id.imageView1);
	   
	   	   
		
//	    ListView lst_view=(ListView)findViewById(R.id.listView1);
		 
		 /*
		ImageView imv=(ImageView)findViewById(R.id.imageView1);
		imv.setImageBitmap(downloadBitmap("http://192.168.1.16/meafairupload/images/1.jpg" ));
		Toast.makeText(getApplicationContext(), "hello", 5).show();
		*/
	    cn=this;
	}

		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.view_missingdetails, menu);
			return true;
		}
		
		private class getjson2 extends AsyncTask<Void, Void, Void>{
			ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
//						String url = "http://192.168.43.84:8000/missingcase/android/";
						String url = Login.url+"missingcase/android/";

//						String url = Registration.url+"doctor_reg/android/";
						JSONArray jdata=JsonHandler.GetJson(url);
						if(jdata!=null)
						{
							
							int len_ar=jdata.length();
							txt1=new String[len_ar];
						    imgtmp=new Bitmap[len_ar];
							
							try {
								
								
							for (int i = 0; i < jdata.length(); i++) {
			                    JSONObject c;
								c = jdata.getJSONObject(i);
								
								String mp_name = c.getString("mp_name");
//								
//								String m_age= c.getString("m_age");
//								String m_datemis= c.getString("m_datemis");
//								String m_datereport= c.getString("m_datereport");
//								String m_height= c.getString("m_height");
//								String m_complexion= c.getString("m_complexion");
//								String m_identimark= c.getString("m_identimark");
//								String m_detail= c.getString("m_detail");
//								String m_address= c.getString("m_address");
								String m_img= c.getString("m_img");
								
								txt1[i]=mp_name;
								imgtmp[i]= downloadBitmap("http://192.168.43.199:8000/static/"+m_img );;
//								String type= c.getString("type");
								
								
								
//								HashMap<String, String> contact =  new HashMap<String, String>();
//			                    contact.put("mp_name", mp_name);
//			                    contact.put("m_age", m_age);
//			                    contact.put("m_datemis", m_datemis);
//			                    contact.put("m_datereport",m_datereport);
//			                    contact.put("m_height",m_height);
//			                    contact.put("m_complexion",m_complexion);
//			                    contact.put("m_identimark",m_identimark);
//			                    contact.put("m_detail",m_detail);
//			                    contact.put("m_address", m_address);
//			                    contact.put("m_img",m_img);
//			                    contact.put("type",type);
			                    
//					
			                    

//			                if(hospid_id.equals(id))
//			                {
//			                	al.add(contact);
//			                }
			              
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
				         
				         Customelist adapter = new Customelist((Activity) cn, txt1, imgtmp);
						 lv1.setAdapter(adapter);	
						
				      }
				}

		
		

//		lv1.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				Intent i=new Intent(getApplicationContext(),View_missingcase.class);
//				
//				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);				
//				i.putExtra("name", hmap.get("mp_name"));
//				i.putExtra("img", hmap.get("m_img"));
//				
//				startActivity(i);
//			}
//		});
//	}
	
	
	

	
	private Bitmap downloadBitmap(String url) {
	    HttpURLConnection urlConnection = null;
	    try {
	        URL uri = new URL(url);
	        urlConnection = (HttpURLConnection) uri.openConnection();
	        int statusCode = urlConnection.getResponseCode();
	        if (statusCode != HttpStatus.SC_OK) {
	        	return null;
	        }

	        InputStream inputStream = urlConnection.getInputStream();
	        if (inputStream != null) {
	            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
	            return bitmap;
	        }
	    } catch (Exception e) {
	        urlConnection.disconnect();
	        Log.w("ImageDownloader", "Error downloading image from " + url);
	        Toast.makeText(getApplicationContext(), "eero",5).show();
	    } finally {
	        if (urlConnection != null) {	
	            urlConnection.disconnect();
	        }
	    }
	    return null;
	}
	
	}
//	}
