package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;

public class ShowSpeed extends EasyGraphics {
	
	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;
		
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t
	
	private static GPSComputer gpscomputer; 
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);
		 
		gpscomputer = new GPSComputer(gpsdata);
		
		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = times.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		// hent hastigheter på de ulike deler av ruten
		double[] speeds = gpscomputer.speeds();
		
		// TODO:
		
		// OPPGAVE - START		
		
		setColor(0, 0, 255);
		int gjennomsnitt = 0;
		for(int i=0; i<speeds.length; i++) {
			int x1,x2,y1,y2;
			
			int bredde = 1;
			int hoyde = ((int) speeds[i]);
			gjennomsnitt = gjennomsnitt + hoyde;
			if(hoyde<0) {
				hoyde = 0;
			}
			y1 = ybase - hoyde;
			x1 = 10 +((bredde*2) * i);
			x2 = bredde;
			y2 = hoyde;
			fillRectangle(x1,y1,x2,y2);
		}
		gjennomsnitt = gjennomsnitt/speeds.length;
		setColor(0, 255, 0);
		fillRectangle(10, ybase-gjennomsnitt, 2*speeds.length, 1);
		
		// OPPGAVE - SLUTT
	}
}
