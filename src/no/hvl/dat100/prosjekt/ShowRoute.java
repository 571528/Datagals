package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;
import java.util.Locale; 
import easygraphics.EasyGraphics;

public class ShowRoute extends EasyGraphics {

	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private static GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");

		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);

		gpscomputer = new GPSComputer(gpsdata);

		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(longitudes);
		double minlon = GPSUtils.findMin(longitudes);

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// y-pixels per breddegrad
	
	public double ystep() {
	
		double ystep = 1.0;
		
		// TODO
		// OPPGAVE - START
		double maxlat = GPSUtils.findMax(latitudes);
		double minlat = GPSUtils.findMin(latitudes);

		ystep = MAPXSIZE / (Math.abs(maxlat - minlat)); 
		// OPPGAVE SLUTT
		
		return ystep;
	}

	public void showRouteMap(int ybase) {
		
		double xstep = xstep();
		double ystep = ystep();

		double minlon = GPSUtils.findMin(longitudes);
		double minlat = GPSUtils.findMin(latitudes);

		setColor(0, 255, 0); // green

		// draw the locations
		for (int i = 0; i < latitudes.length; i++) {

			int x,y;
			// TODO: OPPGAVE START
			
			int radius = 3; 
			x= MARGIN + (int) ((longitudes[i]-minlon)* xstep);
			y = ybase - (int) ((latitudes[i]-minlat)*ystep);
			
			fillCircle(x,y,radius);
			
			// må finne punkt nr i fra latitues og longitudes tabellene
			// og sette x og y til der de skal tegnes som et punkt i vinduet
			
			// OPPGAVE SLUTT
	}
		

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO:
		// OPPGAVE - START
		double WEIGHT = 80.0;
		int ySpace = 20;
		
		String time = String.format(Locale.US,"%-15s:%10s%n", "Total Time", GPSUtils.printTime(gpscomputer.totalTime()));
		String distance = String.format(Locale.US,"%-15s:%10.2f km%n", "Total distance", gpscomputer.totalDistance()/1000);
		String elevation = String.format(Locale.US,"%-15s:%10.2f m%n", "Total elevation", gpscomputer.totalElevation());
		String maxSpeed = String.format(Locale.US,"%-15s:%10.2f km/t%n", "Max speed", gpscomputer.maxSpeed());
		String avgSpeed = String.format(Locale.US,"%-15s:%10.2f km/t%n", "Average speed", gpscomputer.averageSpeed());
		String totKcal = String.format(Locale.US,"%-15s:%10.2f kcal%n", "Energy", gpscomputer.totalKcal(WEIGHT));
			
		drawString(time, MARGIN, ySpace);
		drawString(distance, MARGIN, ySpace*2);
		drawString(elevation, MARGIN, ySpace*3);
		drawString(maxSpeed, MARGIN, ySpace*4);
		drawString(avgSpeed, MARGIN, ySpace*5);
		drawString(totKcal, MARGIN, ySpace*6);
		// OPPGAVE - SLUTT;
	}

	public void playRoute(int ybase) {
		
		double minlat = GPSUtils.findMin(latitudes);
		double minlon = GPSUtils.findMin(longitudes);

		double xstep = xstep();
		double ystep = ystep();

		setColor(0, 0, 255); // blue;

		// make a circle in the first point
		int x = MARGIN + (int) ((longitudes[0] - minlon) * xstep);
		int y = ybase - (int) ((latitudes[0] - minlat) * ystep);

		int movingcircle = fillCircle(x, y, 7);

		// TODO: 
		// EKSTRAOPPGAVE -- START

		// Få cirklen til å flytte seg mellom punktene i vinduet
		
		// EKSTRAOPPGAVE - SLUTT
	}

}
