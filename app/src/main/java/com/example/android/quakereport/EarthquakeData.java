package com.example.android.quakereport;

public class EarthquakeData {

	private double mag;
	private String loc;
	private long time;

	public EarthquakeData(double magnituude, String location, long timestamp ){
			mag = magnituude;
			loc = location;
			time = timestamp;
	}

	public double getMag(){return mag;}

	public String getLoc(){return loc;}

	public long getTime(){return time;}
}
