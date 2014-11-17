package main.java.com.storehive.application.utilities;

import main.java.com.storehive.application.utilities.gps_algorithm.Ellipsoid;
import main.java.com.storehive.application.utilities.gps_algorithm.GeodeticCalculator;
import main.java.com.storehive.application.utilities.gps_algorithm.GlobalPosition;




public class Test {
	
	public static void main(String[] args) throws Exception {
		
		//home -33.9309020 lat long 18.7046930
		double homeLat = -33.9309020;
		double homelong = 18.7046930;
		//51.5033630 lat
		//-0.1276250 long
		double pointLat = -33.9248690;
		double pointlong = 18.4240550;
		GeodeticCalculator geoCalc = new GeodeticCalculator();

		Ellipsoid reference = Ellipsoid.WGS84;  

		GlobalPosition pointA = new GlobalPosition(pointLat, pointlong, 0.0); // Point A

		GlobalPosition userPos = new GlobalPosition(homeLat, homelong, 0.0); // Point B

		double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); // Distance between Point A and Point B
		
		System.out.println(distance/1000);
	}
}
