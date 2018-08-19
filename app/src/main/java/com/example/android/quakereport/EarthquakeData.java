package com.example.android.quakereport;

public class EarthquakeData {

	private double mag;
	private String loc;
	private long time;
	private String url;

	public EarthquakeData(double magnitude, String location, long timestamp ){
			mag = magnitude;
			loc = location;
			time = timestamp;
	}

	public double getMag(){return mag;}

	public String getLoc(){return loc;}

	public long getTime(){return time;}

	public void setURL(String URL){url = URL;}

	public String getURL(){return url;}
}
