import java.util.Random;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class MainProg {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Save results in: ");
		String filename = sc.next();
		sc.close();
		Point2D[] arr = new Point2D[100];
		Random r = new Random();
		
		Point2D queryPoint = new Point2D(r.nextInt(1000), r.nextInt(1000));
		queryPoint.setColor("blue");
		
		Point2D approxNearestNeighbor;
		Point2D actualNearestNeighbor;

		for(int i = 0; i < arr.length; i++){
			arr[i] = new Point2D(r.nextInt(1000), r.nextInt(1000));
		}

		Comparator<Point2D> C = new uncontinuousHilbertCompare(0,0,1000,1000);
		Arrays.sort(arr, C);
		
		XMLGraphicBuilder graphic = new XMLGraphicBuilder(1000, 1000);
		graphic.addArray(arr);
		graphic.addArray(Line2D.getChain(arr));
		
		int lowerbound = 0, upperbound = arr.length - 1;
		boolean equal = false;
		
		while(upperbound - lowerbound > 1) {
			if(C.compare(queryPoint, arr[(upperbound+lowerbound)/2]) < 0) {
				upperbound = (upperbound+lowerbound)/2;
			} else {
				lowerbound = (upperbound+lowerbound)/2;
			}
		}
		
		if(queryPoint.distanceTo(arr[lowerbound]) > queryPoint.distanceTo(arr[upperbound])) {
			approxNearestNeighbor = arr[upperbound];
		} else {
			approxNearestNeighbor = arr[lowerbound];
		}
	
		actualNearestNeighbor = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(queryPoint.distanceTo(arr[i]) < queryPoint.distanceTo(actualNearestNeighbor)) actualNearestNeighbor = arr[i];
		}
		
		Line2D approxLine = new Line2D(queryPoint, approxNearestNeighbor);
		approxLine.setThickness(8);
		approxLine.setColor("red");
		
		
		Line2D actualLine = new Line2D(queryPoint, actualNearestNeighbor);
		actualLine.setThickness(8);
		actualLine.setColor("green");
		
		graphic.addObject(approxLine);
		graphic.addObject(actualLine);
		graphic.addObject(queryPoint);
		
		try {
			PrintWriter outputFile = new PrintWriter(filename);

			outputFile.print(graphic.build());

			outputFile.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println(e);
		}
		
		System.out.println("Approximate nearest neighbor: " + approxNearestNeighbor);
		System.out.println("Distance: " + queryPoint.distanceTo(approxNearestNeighbor));
		System.out.println("Actual nearest neighbor: " + actualNearestNeighbor);
		System.out.println("Distance: " + queryPoint.distanceTo(actualNearestNeighbor));
	}

}
