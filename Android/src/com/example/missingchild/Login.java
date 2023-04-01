package com.example.missingchild;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText e1,e2;
	
	Button b1,b2,b3;
	public static String url="http://192.168.43.199:8000/";
	public String perm="okk";
	private String TAG = Login.class.getSimpleName();
	public static String ty,uid;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
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
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),U_register.class);
				startActivity(i);
				
			}
		});
      b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),Forgot_password.class);
				startActivity(i);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }



    private class savejson extends AsyncTask<Void, Void, Void>{
  		@Override
  		protected Void doInBackground(Void... params) {
  			// TODO Auto-generated method stub

  			String url = Login.url+"login/android/";
//			String url = "http://192.168.43.199:8000/login/android/";

  			JSONObject jobj= new JSONObject();
  	        try {
  				jobj.put("username", e1.getText().toString());
  				jobj.put("password", e2.getText().toString());
  				String s= JsonHandler.Postjson(url, jobj);
  				JSONArray jdata =JsonHandler.Getjarray(s);               
  				if(jdata!=null)
  				{
  					perm="error";
  					for (int i = 0; i < jdata.length(); i++) {
  						perm="ok";
  						JSONObject ob;
  						ob = jdata.getJSONObject(i);
  						uid=ob.getString("user_id");
  						 ty=ob.getString("type");
  					    	if(ty.equals("user"))
  							{
  					    		
  								Intent ii=new Intent(getApplicationContext(),Home.class);
  								startActivity(ii);
  								
  							}
  					    	
  					   
  					   
					    	
  					} 					
  				}
  				else
  				{
  					perm="error";
  				}
  				Log.d("out", perm);
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}  			
  			return null; 
  		} 		
  		@Override
  	      protected void onPostExecute(Void result) {
  	         super.onPostExecute(result);  	         
  	         if(!perm.equals("error"))
  	         {
  	        	 Log.d("äuth", "ok");
  	        	 Toast.makeText(getApplicationContext(), "Login Successfull", 3).show();  	 
  	         }
  	         else
  	         {
  	        	 Log.d("äuth", "error");
  	        	 Toast.makeText(getApplicationContext(), "Login Failed... Username or Password is incorrect.", 3).show();
  	         }
  	      }
  	}
    
    
   

}
