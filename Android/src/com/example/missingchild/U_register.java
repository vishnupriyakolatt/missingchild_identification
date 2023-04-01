package com.example.missingchild;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class U_register extends Activity {
	EditText e1,e2,e3,e4,e5,e6,e7;
	Button b1,b2;
	RadioButton m,f,o;
	String gend="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_register);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);
		e7=(EditText)findViewById(R.id.editText7);
		b1=(Button)findViewById(R.id.button1);
		m=(RadioButton)findViewById(R.id.radio0);
		f=(RadioButton)findViewById(R.id.radio1);
		o=(RadioButton)findViewById(R.id.radio2);
//		rg1=(RadioGroup)findViewById(R.id.radioGroup1);
		b1.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals("")&&!e3.getText().toString().equals("")&&!e4.getText().toString().equals("")&&!e5.getText().toString().equals("")&&!e6.getText().toString().equals("")&&!e7.getText().toString().equals(""))
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
		getMenuInflater().inflate(R.menu.u_register, menu);
		return true;
	}
	
	
	
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
//			String url = "http://192.168.43.199:8000/user/android/";
			String url = Login.url+"user/android/";
			if(m.isChecked())
			{
				gend="male";
			}
			else if (f.isChecked())
			{
				gend="female";
			}
			else if (o.isChecked())
			{
				gend="others";
			}		
			
			JSONObject jobj= new JSONObject();
	        try {
	   
				jobj.put("username",e1.getText().toString());     
				jobj.put("u_email",e2.getText().toString());
				jobj.put("u_address",e3.getText().toString());
			   	jobj.put("u_state",e4.getText().toString());
				jobj.put("u_country",e5.getText().toString());
				jobj.put("u_phno",e6.getText().toString());
				jobj.put("password",e7.getText().toString());
				jobj.put("u_pin","");
				jobj.put("u_gender",gend);
				

					
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
					Intent i= new Intent(getApplicationContext(),Login.class);
						startActivity(i);
	         
	      }
	}
	

}
