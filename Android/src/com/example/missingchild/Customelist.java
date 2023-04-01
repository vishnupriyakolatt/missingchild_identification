package com.example.missingchild;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Customelist extends ArrayAdapter<String>{
	
	private final Activity context;

	private final String[] web;
//	private final String[] mp_name;
//	private final String[] web;
//	private final String[] web;

	private final Bitmap[] imgtmp;
	
	public Customelist(Activity context,String[] web,Bitmap imgtmp[]) {
		super(context, R.layout.view_missingdetails, web);
		this.context = context;
		this.web = web;
		this.imgtmp=imgtmp;
//		this.mp_name=mp_name;
	}
	
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		 
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.view_missingdetails, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.editText1);

	ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
	txtTitle.setText(web[position]);
	imageView.setImageBitmap(imgtmp[position]);
//	TextView mp_name=(TextView)rowView.findViewById(R.id.textView1);
//	mp_name=setText(mp_name[position]);
		return rowView;
	}

}
