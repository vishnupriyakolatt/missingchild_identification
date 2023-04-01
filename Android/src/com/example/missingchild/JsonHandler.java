package com.example.missingchild;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class JsonHandler  {
	
	private static String TAG = Login.class.getSimpleName();
	
	public static JSONArray GetJson(String url)
	{
		HttpHandler sh = new HttpHandler();
		final String jsonStr = sh.makeServiceCall(url);
		JSONArray jdata = null;
		if (jsonStr != null) 
		{
			try {
				jdata=new JSONArray(jsonStr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jdata;
	}
	
	public static JSONArray Getjarray(String jsonStr)
	{
		JSONArray jdata = null;
		if (jsonStr != null) 
		{
			try {
				jdata=new JSONArray(jsonStr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jdata;
	}
	
	
	
	
	public static String Postjson(String jurl, JSONObject jsonObject)
	{
		String content="none";
		try {
			
			HttpPost httpPost = new HttpPost(jurl);
			
			StringEntity entity = new StringEntity(jsonObject.toString(), HTTP.UTF_8);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity1 = response.getEntity();
            // Read the contents of an entity and return it as a String.
			content = EntityUtils.toString(entity1);
            
            //Log.e(TAG, content);

        } 
		catch (MalformedURLException e)
        {
            e.printStackTrace();
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        } catch (IOException e)
        {
            e.printStackTrace();
            Log.e(TAG, "Json parsing error1: " + e.getMessage());
        }
		catch (Exception e)
		{
			Log.e(TAG, "Json parsing error2: " + e.getMessage());
        }
		
		return content;
		
		
	}
	
	
	
	

			
}


