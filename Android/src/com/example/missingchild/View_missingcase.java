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

public class View_missingcase extends Activity {
	ImageView pic;
	TextView name;
//	String im;
	String imgg,nm,phto; 
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_missingcase);
		pic=(ImageView) findViewById(R.id.imageView1);
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
		
		
		imgg = getIntent().getStringExtra("img");
		nm = getIntent().getStringExtra("name");
	
		
		name.setText(nm);
		
		Toast.makeText(getApplicationContext(), imgg, 3).show();
		
		pic.setImageBitmap(downloadBitmap(Login.url+"static/"+imgg));
	}



	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_missingcase, menu);
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
