package com.example.missingchild;


import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnClickListener; 
public class Forgot_password extends Activity {
EditText e1,e2;
String s;
	
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText3);
		b1=(Button)findViewById(R.id.button1);
		  b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals(""))
		        {
		        new savejson().execute();
		        }
		        else
		        {
		          Toast.makeText(getApplicationContext(), "fill", 3).show();
		        }
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgot_password, menu);
		return true;
	}
	
	
	
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
//			String url = "http://192.168.43.84:8000/complaint/android/";
			String url = Login.url+"login/android1/";
			
			JSONObject jobj= new JSONObject();
	        try {
	   
				jobj.put("u_email",e1.getText().toString()); 
				jobj.put("password",e2.getText().toString());  

				
				s=JsonHandler.Postjson(url, jobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			return null; 
		}
	
		@Override
	      protected void onPostExecute(Void result) {
	         super.onPostExecute(result);
	    
	        
	        	 Toast.makeText(getApplicationContext(), s, 3).show();    
//					Intent i= new Intent(getApplicationContext(),Home.class);
//						startActivity(i);
	         
	      }
	}
	
	

}
