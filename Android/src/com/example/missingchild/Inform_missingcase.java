package com.example.missingchild;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.missingchild.Post_complaint.savejson;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.Toast;

public class Inform_missingcase extends Activity {

	EditText e1,e2;
	Button b1, b2;
	ImageView iv1;
	protected static final int CAMERA_REQUEST = 0;
	protected static final int PICK_IMAGE_REQUEST = 0;

	String img_str;
	Bitmap bitmap;
	String s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform_missingcase);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		
		iv1=(ImageView)findViewById(R.id.imageView1);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
				
				
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
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
		getMenuInflater().inflate(R.menu.inform_missingcase, menu);
		return true;
	}
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
//			String url = "http://192.168.43.84:8000/missingreport/android/";
  			String url = Login.url+"missingreport/android/";

			
			JSONObject jobj= new JSONObject();
	        try {
	   
				jobj.put("report_loc",e1.getText().toString());     
				jobj.put("report_date",e2.getText().toString());
//				jobj.put("report_time","");
				jobj.put("report_img",img_str);
				jobj.put("user",Login.uid);

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
	    
	        
	        	 Toast.makeText(getApplicationContext(), "SUCCESSFULLY REGISTERED", 3).show();    
					Intent i= new Intent(getApplicationContext(),Home.class);
						startActivity(i);
	         
	      }
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		
			if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
					&& data != null && data.getData() != null) {
				Uri uri = data.getData();

				try {
					bitmap = MediaStore.Images.Media.getBitmap(
							getContentResolver(), uri);
					iv1.setImageBitmap(bitmap);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
					byte[] image = stream.toByteArray();
					img_str = Base64.encodeToString(image, 0);

				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
			
	}
}
