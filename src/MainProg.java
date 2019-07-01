import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class MainProg {
	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("Save data in: ");
		Scanner sc = new Scanner(System.in);
		String fileName = sc.next();
		sc.close();
		Random r = new Random();
		PrintWriter file = new PrintWriter(fileName);
		Comparator<Point2D> C = new uncontinuousHilbertCompare(0,0,1024,1024);
		double amount;
		Point2D[] arr;
		Point2D queryPoint = new Point2D(0,0);
		Point2D nearestNeighbor;
		Point2D approxNeighbor;
		double actualDistance;
		double approxDistance;
		int intamount;
		int pointsThatMiss;
		double errorSum;
		
		for(amount = 100; amount < 65536; amount *= 1.02){
			intamount = (int) Math.round(amount);
			System.out.println(intamount);
			arr = new Point2D[intamount];
			for(int i = 0; i < intamount; i++){
				arr[i] = new Point2D(r.nextInt(1024), r.nextInt(1024));
			}
			Arrays.sort(arr, C);
			pointsThatMiss = 0;
			errorSum = 0;
			for(int i = 0; i < 1000; i++){
				queryPoint.setCoordinates(r.nextInt(1024), r.nextInt(1024));
				approxNeighbor = queryPoint.getBinarySearchNeighbor(C, arr);
				nearestNeighbor = queryPoint.getNearestNeighbor(arr);
				actualDistance = queryPoint.distanceTo(nearestNeighbor);
				approxDistance = queryPoint.distanceTo(approxNeighbor);
				if(approxDistance > actualDistance + 0.05 && actualDistance > 0.9){
					pointsThatMiss++;
					errorSum += (approxDistance - actualDistance)/actualDistance;
				}
			}
			errorSum /= (double) pointsThatMiss;
			file.println(Math.log(amount)/Math.log(4) + " " + errorSum);			
		}
		file.close();
	}

}
