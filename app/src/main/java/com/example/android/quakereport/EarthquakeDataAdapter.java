package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
		magnitudeTextView.setText(currentData.getMag());

		TextView locationTextView = (TextView) listItemView.findViewById(R.id.locationView);
		locationTextView.setText(currentData.getLoc());

		TextView timestampTextView = (TextView) listItemView.findViewById(R.id.timestampView);
		timestampTextView.setText(currentData.getTime());


	return listItemView;}
}
