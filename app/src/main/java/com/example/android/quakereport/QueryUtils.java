package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

	/** Sample JSON response for a USGS query */
	private static final String USGS_RESPONSE_STR = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1533641429000,\"url\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-07-01&endtime=2018-07-31&minmagnitude=6&limit=10\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.5.8\",\"limit\":10,\"offset\":1,\"count\":9},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":6.4,\"place\":\"5km N of Lelongken, Indonesia\",\"time\":1532818057540,\"updated\":1533597333465,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000ggbs\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000ggbs&format=geojson\",\"felt\":402,\"cdi\":5.4,\"mmi\":7.69,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":867,\"net\":\"us\",\"code\":\"2000ggbs\",\"ids\":\",us2000ggbs,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,ground-failure,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.322,\"rms\":1.11,\"gap\":24,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.4 - 5km N of Lelongken, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[116.4911,-8.2735,6.39]},\"id\":\"us2000ggbs\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"149km N of Palue, Indonesia\",\"time\":1532797643370,\"updated\":1533049628043,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000gg76\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000gg76&format=geojson\",\"felt\":2,\"cdi\":4.1,\"mmi\":2.08,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":555,\"net\":\"us\",\"code\":\"2000gg76\",\"ids\":\",us2000gg76,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,ground-failure,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.586,\"rms\":1.36,\"gap\":19,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 149km N of Palue, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[122.7474,-7.1221,577.48]},\"id\":\"us2000gg76\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"91km W of Kandrian, Papua New Guinea\",\"time\":1532025032710,\"updated\":1532575601682,\"tz\":600,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/usd00090uc\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usd00090uc&format=geojson\",\"felt\":2,\"cdi\":6.4,\"mmi\":4.94,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":555,\"net\":\"us\",\"code\":\"d00090uc\",\"ids\":\",usd00090uc,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":3.62,\"rms\":0.75,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 91km W of Kandrian, Papua New Guinea\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[148.7302,-6.1139,29.62]},\"id\":\"usd00090uc\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"116km SE of Lata, Solomon Islands\",\"time\":1531810973020,\"updated\":1532676113610,\"tz\":660,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000g6uy\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000g6uy&format=geojson\",\"felt\":1,\"cdi\":3.4,\"mmi\":4.82,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":554,\"net\":\"us\",\"code\":\"2000g6uy\",\"ids\":\",us2000g6uy,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,ground-failure,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":6.726,\"rms\":0.88,\"gap\":43,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 116km SE of Lata, Solomon Islands\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[166.432,-11.5936,37.96]},\"id\":\"us2000g6uy\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"159km SSE of Sayhut, Yemen\",\"time\":1531660156470,\"updated\":1532267894192,\"tz\":180,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000g5mh\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000g5mh&format=geojson\",\"felt\":2,\"cdi\":4.8,\"mmi\":3.68,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":555,\"net\":\"us\",\"code\":\"2000g5mh\",\"ids\":\",us2000g5mh,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":19.186,\"rms\":0.74,\"gap\":96,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 159km SSE of Sayhut, Yemen\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[51.717,13.8484,10]},\"id\":\"us2000g5mh\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"137km SSE of Sayhut, Yemen\",\"time\":1531619839410,\"updated\":1533273072804,\"tz\":180,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000g5b4\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000g5b4&format=geojson\",\"felt\":1,\"cdi\":3.6,\"mmi\":3.79,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":554,\"net\":\"us\",\"code\":\"2000g5b4\",\"ids\":\",us2000g5b4,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":9.028,\"rms\":1.21,\"gap\":64,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 137km SSE of Sayhut, Yemen\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[51.7365,14.0625,10]},\"id\":\"us2000g5b4\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6.4,\"place\":\"72km NNW of Isangel, Vanuatu\",\"time\":1531475209640,\"updated\":1533246129783,\"tz\":660,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000g3up\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000g3up&format=geojson\",\"felt\":30,\"cdi\":3.4,\"mmi\":4.23,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":640,\"net\":\"us\",\"code\":\"2000g3up\",\"ids\":\",us2000g3up,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.485,\"rms\":0.76,\"gap\":19,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.4 - 72km NNW of Isangel, Vanuatu\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[169.0224,-18.9332,169.16]},\"id\":\"us2000g3up\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"124km NE of L'Esperance Rock, New Zealand\",\"time\":1530999229630,\"updated\":1532663184196,\"tz\":-720,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000fzb7\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000fzb7&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":3.24,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":554,\"net\":\"us\",\"code\":\"2000fzb7\",\"ids\":\",pt18188001,at00pbimkb,us2000fzb7,\",\"sources\":\",pt,at,us,\",\"types\":\",geoserve,impact-link,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.323,\"rms\":0.66,\"gap\":40,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 124km NE of L'Esperance Rock, New Zealand\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-178.0715,-30.5668,35]},\"id\":\"us2000fzb7\"},\r\n" +
															   "{\"type\":\"Feature\",\"properties\":{\"mag\":6.1,\"place\":\"91km E of Ozernovskiy, Russia\",\"time\":1530841204610,\"updated\":1533412756331,\"tz\":720,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000fxyz\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000fxyz&format=geojson\",\"felt\":6,\"cdi\":6.8,\"mmi\":5.22,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":577,\"net\":\"us\",\"code\":\"2000fxyz\",\"ids\":\",us2000fxyz,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,ground-failure,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.605,\"rms\":0.71,\"gap\":23,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.1 - 91km E of Ozernovskiy, Russia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[157.8404,51.4994,45]},\"id\":\"us2000fxyz\"}],\"bbox\":[-178.0715,-30.5668,6.39,169.0224,51.4994,577.48]}";

	/**
	 * Create a private constructor because no one should ever create a {@link QueryUtils} object.
	 * This class is only meant to hold static variables and methods, which can be accessed
	 * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
	 */
	private QueryUtils() {
	}

	/**
	 * Return a list of {@link EarthquakeData} objects that has been built up from
	 * parsing a JSON response.
	 */
	public static ArrayList<EarthquakeData> extractEarthquakes() {

		// Create an empty ArrayList that we can start adding earthquakes to
		ArrayList<EarthquakeData> earthquakes = new ArrayList<>();

		// Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
		// is formatted, a JSONException exception object will be thrown.
		// Catch the exception so the app doesn't crash, and print the error message to the logs.
		try {

			// TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
			// build up a list of Earthquake objects with the corresponding data.

			JSONObject rootObject = new JSONObject(USGS_RESPONSE_STR);

			JSONArray features = rootObject.getJSONArray("features");

			for(int i=0;i<features.length();i++){
				JSONObject currentEarthquake = features.getJSONObject(i);
				JSONObject properties = currentEarthquake.getJSONObject("properties");

				double magnitude = properties.optDouble("mag");
				String place = properties.optString("place");
				Long time = properties.optLong("time");
				String url = properties.optString("url");

				EarthquakeData earthquake = new EarthquakeData(magnitude,place,time);
				earthquake.setURL(url);
				earthquakes.add(earthquake);
			}


		} catch (JSONException e) {
			// If an error is thrown when executing any of the above statements in the "try" block,
			// catch the exception here, so the app doesn't crash. Print a log message
			// with the message from the exception.
			Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
		}

		// Return the list of earthquakes
		return earthquakes;
	}

}