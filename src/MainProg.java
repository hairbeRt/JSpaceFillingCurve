import java.util.Random;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainProg {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Save results in: ");
		String dataFileName = sc.next();
		System.out.print("Save average data in: ");
		String avgFileName = sc.next();
		sc.close();
		
		Point2D[] arr;

		Comparator<Point2D> C = new uncontinuousHilbertCompare(1,1,1024,1024);
		Point2D queryPoint = new Point2D();
		Random r = new Random();
		
		PrintWriter outputFile = new PrintWriter(dataFileName);
		PrintWriter avgFile = new PrintWriter(avgFileName);
		
		double approxDistance, actualDistance, errorPercentage;
		double averageError;
		
		for(int p = 5; p <= 20000; p += 10) { //Dataset size
			averageError = 0;
			arr = new Point2D[p];
			
			for(int i = 0; i < arr.length; i++){
				arr[i] = new Point2D();
			}
			
			for(int i = 0; i < arr.length; i++){
				arr[i].setCoordinates(r.nextInt(1023)+1, r.nextInt(1023)+1);
			}
			
			Arrays.sort(arr, C);
			
			for(int n = 1; n <= 50; n++) { //Number of experiments
				queryPoint.setCoordinates(r.nextInt(1023)+1, r.nextInt(1023)+1);
				//queryPoint.setColor("blue");
				
				Point2D approxNearestNeighbor = queryPoint.getBinarySearchNeighbor(C, arr);
				Point2D actualNearestNeighbor = queryPoint.getNearestNeighbor(arr);
				actualDistance = queryPoint.distanceTo(actualNearestNeighbor);
				approxDistance = queryPoint.distanceTo(approxNearestNeighbor);
				errorPercentage = ((approxDistance/actualDistance) - 1)*100;
				if(actualDistance == 0) errorPercentage = 0; 
				
				outputFile.println(
									n + " "
									+ p + " "
									+ actualDistance + " "
									+ approxDistance + " "
									+ errorPercentage);
				averageError += errorPercentage;
			}
			averageError/= 50;
			avgFile.println(p + " " + averageError);
		}
		outputFile.close();
		avgFile.close();
		/*
		//Draw results
		XMLGraphicBuilder graphic = new XMLGraphicBuilder(1000, 1000);
		Line2D approxLine = new Line2D(queryPoint, approxNearestNeighbor);
		approxLine.setThickness(8);
		approxLine.setColor("red");
		Line2D actualLine = new Line2D(queryPoint, actualNearestNeighbor);
		actualLine.setThickness(8);
		actualLine.setColor("green");
		graphic.addArray(arr);//Generated points
		graphic.addArray(Line2D.getChain(arr));//Ordered Chain of points
		
		//Result of nearest neighbor search
		graphic.addObject(approxLine);
		graphic.addObject(actualLine);
		graphic.addObject(queryPoint);
		*/
		
		//Draw SVG graphic
		/*try {
			PrintWriter outputFile = new PrintWriter(filename);
			outputFile.print(graphic.build());
			outputFile.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println(e);
		}*/
		/*
		System.out.println("Approximate nearest neighbor: " + approxNearestNeighbor);
		System.out.println("Distance: " + queryPoint.distanceTo(approxNearestNeighbor));
		System.out.println("Actual nearest neighbor: " + actualNearestNeighbor);
		System.out.println("Distance: " + queryPoint.distanceTo(actualNearestNeighbor));
		*/
	}

}
