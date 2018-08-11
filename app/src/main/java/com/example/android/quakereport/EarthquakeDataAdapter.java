package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeDataAdapter extends ArrayAdapter {

	public EarthquakeDataAdapter(Activity context, ArrayList<EarthquakeData> earthquakeData){
		super(context,0,earthquakeData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View listItemView = convertView;
		if (listItemView == null) {
			listItemView = LayoutInflater.from(getContext()).inflate(
					R.layout.list_item, parent, false);
		}

		EarthquakeData currentData = (EarthquakeData) getItem(position);

		TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitudeView);
		DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
		String magnitude = magnitudeFormat.format(currentData.getMag());
		magnitudeTextView.setText(magnitude);

		String location = currentData.getLoc();
		String offsetLoc;
		if(location.contains("of"))
			offsetLoc = location.substring(0,location.indexOf("of")+2);
		else
			offsetLoc = "Near the";

		String primaryLoc = location.substring(location.indexOf("of")+2,location.length());

		TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offsetView);
		offsetTextView.setText(offsetLoc);


		TextView primaryLocTextView = (TextView) listItemView.findViewById(R.id.primaryLocView);
		primaryLocTextView.setText(primaryLoc);


		Date dateObject = new Date(currentData.getTime());

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy h:ma");
		String date = dateFormatter.format(dateObject);
		TextView timestampTextView = (TextView) listItemView.findViewById(R.id.timestampView);
		timestampTextView.setText(date);


	return listItemView;}
}
