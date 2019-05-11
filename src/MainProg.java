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
		Point2D[] arr = new Point2D[256];
		Random r = new Random();

		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				arr[16*i + j] = new Point2D(i*1000/16, j*1000/16);
			}
		}
		/*for(int i = 0; i < arr.length; i++){
			arr[i] = new Point2D(r.nextInt(1000), r.nextInt(1000));
		}*/

		Comparator<Point2D> C = new uncontinuousHilbertCompare(0,0,1000,1000);
		Arrays.sort(arr, C);
		
		XMLGraphicBuilder graphic = new XMLGraphicBuilder(1000, 1000);
		graphic.drawPoints(arr);
		graphic.drawChain(arr);
		
		
		try {
			PrintWriter outputFile = new PrintWriter(filename);

			outputFile.print(graphic.build());

			outputFile.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
