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
		sc.close();
		
		Point2D[] arr = new Point2D[256*256];
		for(int i = 0; i < 256; i++) {
			for(int j = 0; j < 256; j++) {
				arr[256*i + j] = new Point2D(4*i + 1, 4*j + 1);
				arr[256*i + j].setThickness(1);
			}
		}
		Comparator<Point2D> C = new uncontinuousHilbertCompare(0,0,1024,1024);
		Arrays.sort(arr, C);
		
		//Draw results
		XMLGraphicBuilder graphic = new XMLGraphicBuilder(1000, 1000);
		graphic.addArray(arr);
		graphic.addArray(Line2D.getChain(arr));
		
		//Draw SVG graphic
		try {
			PrintWriter outputFile = new PrintWriter(dataFileName);
			outputFile.print(graphic.build());
			outputFile.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println(e);
		}
		/*
		System.out.println("Approximate nearest neighbor: " + approxNearestNeighbor);
		System.out.println("Distance: " + queryPoint.distanceTo(approxNearestNeighbor));
		System.out.println("Actual nearest neighbor: " + actualNearestNeighbor);
		System.out.println("Distance: " + queryPoint.distanceTo(actualNearestNeighbor));
		*/
	}

}
