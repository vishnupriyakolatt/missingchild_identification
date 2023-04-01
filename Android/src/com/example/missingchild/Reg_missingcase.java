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
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Reg_missingcase extends Activity {
	EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
	Button b1,b2;
//	RadioGroup  m,f,o;
	RadioButton m,f,o;
	String gend="";
	ImageView iv1;
	protected static final int CAMERA_REQUEST = 0;
	protected static final int PICK_IMAGE_REQUEST = 0;

	String img_str;
	Bitmap bitmap;
	String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg_missingcase);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);
		e7=(EditText)findViewById(R.id.editText7);
		e8=(EditText)findViewById(R.id.editText8);
		e9=(EditText)findViewById(R.id.editText9);
		iv1=(ImageView)findViewById(R.id.imageView1);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
//		rg1=(RadioGroup)findViewById(R.id.radioGroup1);
		m=(RadioButton)findViewById(R.id.radio0);
		f=(RadioButton)findViewById(R.id.radio1);
		o=(RadioButton)findViewById(R.id.radio2);

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
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals("")&&!e3.getText().toString().equals("")&&!e4.getText().toString().equals("")&&!e5.getText().toString().equals("")&&!e6.getText().toString().equals("")&&!e7.getText().toString().equals("")&&!e8.getText().toString().equals("")&&!e9.getText().toString().equals(""))
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
		getMenuInflater().inflate(R.menu.reg_missingcase, menu);
		return true;
	}
		public class savejson extends AsyncTask<Void, Void, Void>{
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
//		String url = "http://192.168.43.84:8000/missingcase/android/";
		String url = Login.url+"missingcase/android/";

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

		jobj.put("mp_name",e1.getText().toString());  
		jobj.put("m_name","");     
		jobj.put("m_age",e2.getText().toString());
	   	jobj.put("m_datemis",e3.getText().toString());
		jobj.put("m_datereport",e4.getText().toString());
		jobj.put("m_height",e5.getText().toString());
		jobj.put("m_complexion",e6.getText().toString());
		jobj.put("m_identimark",e7.getText().toString());
		jobj.put("m_detail",e8.getText().toString());
		jobj.put("m_address",e9.getText().toString());
		jobj.put("m_status","pending");   
		jobj.put("m_img",img_str);        
//		jobj.put("type",);   
		jobj.put("m_gender",gend);
		jobj.put("user_id",Login.uid); 
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
