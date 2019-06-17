import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class MainProg {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Save nearest neighbor probability results in: ");
		String neighborFileName = sc.next();
		System.out.print("Save error results in: ");
		String errorFileName = sc.next();
		sc.close();
		PrintWriter neighborFile = new PrintWriter(neighborFileName);
		PrintWriter errorFile = new PrintWriter(errorFileName);
		Point2D[] arr;
		Point2D queryPoint;
		Point2D nearestNeighbor;
		Point2D generatedNeighbor;
		Random r = new Random();
		double bestNeighborCount;
		double errorCount;
		double avgRelativeError;
		
		
		
		Comparator<Point2D> C = new continuousHilbertCompare(0,0,512,512);
		
		for(int length = 100; length <= 20000; length += 100) {
			System.out.println(length);
			bestNeighborCount = 0;
			errorCount = 0;
			avgRelativeError = 0;
			arr = new Point2D[length];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = new Point2D(r.nextInt(512), r.nextInt(512));
			}
			Arrays.sort(arr, C);
			
			for(int i = 0; i < 100; i++) {
				queryPoint = new Point2D(r.nextInt(512), r.nextInt(512));
				nearestNeighbor = queryPoint.getNearestNeighbor(arr);
				generatedNeighbor = queryPoint.getBinarySearchNeighbor(C, arr);
				if(queryPoint.distanceTo(generatedNeighbor) - queryPoint.distanceTo(nearestNeighbor) > 0.5) {
					errorCount += 1;
					avgRelativeError += (queryPoint.distanceTo(generatedNeighbor)/queryPoint.distanceTo(nearestNeighbor)) - 1;
				} else {
					bestNeighborCount += 1;
				}
			}
			avgRelativeError /= errorCount;
			neighborFile.println(length + " " + bestNeighborCount/100);
			errorFile.println(length + " " + avgRelativeError);
		}
		neighborFile.close();
		errorFile.close();
		
	}

}
