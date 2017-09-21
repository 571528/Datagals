package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;

import java.util.Locale;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr = "";
		String TIMESEP = ":";
		
		// TODO
		// OPPGAVE - START
		 
		int hr = secs/3600;
		int min = (secs%3600)/60;
		int sec = (secs%3600)%60;
		
		timestr = ((hr<10 ? "0":"")+hr+TIMESEP+(min<10 ? "0":"")+min+TIMESEP+(sec<10 ? "0":"")+sec);
		// OPPGAVE - SLUTT
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum
		double min = da[0]; 

		// TODO
		// OPPGAVE - START
		

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		
		
		// OPPGAVE - SLUT
		return min;
	}

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d; // fjern = 1.0
		
		// TODO:
		// OPPGAVE - START
		double dLat = toRadians(latitude2-latitude1);
		double dLon = toRadians(longitude2 - longitude1);
		latitude1 = toRadians(latitude1);
		latitude2 = toRadians(latitude2);
		
		a = pow((sin((dLat)/2)),2) + cos(latitude1)*cos(latitude2)*pow((sin(dLon)/2),2);
        c = 2 * atan2(sqrt(a), sqrt(1-a));
        d = R*c;
		// OPPGAVE - SLUTT

		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed = 0.0;

		// TODO:
		// OPPGAVE - START
		double snitt = distance(latitude1, longitude1, latitude2, longitude2)/secs;
		speed = snitt*3.6;
		
		// OPPGAVE - SLUTT

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
	
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden
	
	public static String printDouble(double d) {
		
		String str = "";
		
		// TODO
		// OPPGAVE - START
		String fmt = "%"+Integer.toString(TEXTWIDTH)+".2f";
		str = String.format(Locale.ENGLISH,fmt,d);
		// OPPGAVE - SLUTT
		
		return str;
	}
}
