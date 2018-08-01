package com.example.android.quakereport;

public class EarthquakeData {

	private String mag;
	private String loc;
	private String time;

	public EarthquakeData(String magnituude, String location, String timestamp ){
			mag = magnituude;
			loc = location;
			time = timestamp;
	}

	public String getMag(){return mag;}

	public String getLoc(){return loc;}

	public String getTime(){return time;}
}
