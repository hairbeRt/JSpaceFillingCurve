import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class MainProg {
	public static void main(String[] args) throws FileNotFoundException {
		Comparator<Point2D> c = new continuousHilbertCompare(0,0, 512, 512);
		Point2D[] arr;
		Random r = new Random();
		Point2D queryPoint;
		Point2D approx;
		Point2D exact;
		int count;
		PrintWriter fileout = new PrintWriter("HilbertData.txt");
		
		/*
		 * Für alle Datengrößen zwischen 100 und 20000 in 100-Schritten:
		 * Führe 100 Experimente mit ApproxSearch vs ExactSearch aus
		 * Vergleiche und speichere die Fehler
		 */
		for(int n = 100; n < 20000; n+= 100) {
			count = 0;
			for(int i = 0; i < 100; i++) {
				arr = new Point2D[n];
				for(int j = 0; j < n; j++) {
					arr[j] = new Point2D(r.nextInt(512), r.nextInt(512));
				}
				queryPoint = new Point2D(r.nextInt(512), r.nextInt(512));
				Arrays.sort(arr,c);
				approx = queryPoint.getBinarySearchNeighbor(c, arr);
				exact = queryPoint.getNearestNeighbor(arr);
				if(queryPoint.distanceTo(approx) - queryPoint.distanceTo(exact) < .005) {
					count++;
				}
			}
			System.out.println(n);
			fileout.println(n + " " + (double)count/100);
			
		}
		fileout.close();
	}

}