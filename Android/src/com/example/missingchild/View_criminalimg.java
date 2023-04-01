package com.example.missingchild;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpStatus;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class View_criminalimg extends Activity {
	ImageView photo;
	TextView name;
	String im;
	String img,nm,phto; 
	@SuppressLint("NewApi")

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_criminalimg);
		
		photo=(ImageView) findViewById(R.id.imageView1);
		name=(TextView) findViewById(R.id.textView1);
		
		
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
		

		
		img = getIntent().getStringExtra("img");
		nm = getIntent().getStringExtra("name");
	
		
		name.setText(nm);
		
		Toast.makeText(getApplicationContext(), img, 3).show();
		
		photo.setImageBitmap(downloadBitmap(Login.url+"static/"+img));
	}
   
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_criminalimg, menu);
		return true;
	}
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
	    } finally {
	        if (urlConnection != null) {
	            urlConnection.disconnect();
	        }
	    }
	    return null;
	}

}
