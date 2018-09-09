/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {


	public static final String LOG_TAG = EarthquakeActivity.class.getName();
	public static final String USGS_RESPONSE_STR = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10&starttime=2017-01-01";

	private EarthquakeDataAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.earthquake_activity);

		// Find a reference to the {@link ListView} in the layout
		ListView earthquakeListView = (ListView) findViewById(R.id.list);

		adapter = new EarthquakeDataAdapter(this, new ArrayList<EarthquakeData>());


		// Set the adapter on the {@link ListView}
		// so the list can be populated in the user interface
		earthquakeListView.setAdapter(adapter);



		earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				EarthquakeData currentEarthquake = (EarthquakeData) adapter.getItem(position);

				String url = currentEarthquake.getURL();
				Intent browserIntent = new Intent();
				browserIntent.setAction(Intent.ACTION_VIEW);
				browserIntent.setData(Uri.parse(url));

				String title = getResources().getString(R.string.chooser_title);

				Intent chooser = Intent.createChooser(browserIntent, title);

				if (browserIntent.resolveActivity(getPackageManager()) != null) {
					startActivity(chooser);
				}

			}
		});
		// Start the AsyncTask to fetch the earthquake data
		EarthquakeAsyncTask task = new EarthquakeAsyncTask();
		task.execute(USGS_RESPONSE_STR);
	}


	private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthquakeData>> {
		@Override
		protected void onPostExecute(List<EarthquakeData> earthquakeData) {
			// Clear the adapter of previous earthquake data
			adapter.clear();

			// If there is a valid list of {@link Earthquake}s, then add them to the adapter's
			// data set. This will trigger the ListView to update.
			if (earthquakeData != null && !earthquakeData.isEmpty()) {
				adapter.addAll(earthquakeData);
			}
		}

		@Override
		protected List<EarthquakeData> doInBackground(String... urls) {
			// Don't perform the request if there are no URLs, or the first URL is null.
			if (urls.length < 1 || urls[0] == null) {
				return null;
			}

			List<EarthquakeData> result = QueryUtils.fetchEarthquakeData(urls[0]);
			return result;
		}
	}

}

