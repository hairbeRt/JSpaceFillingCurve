import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class MainProg {
	public static void main(String[] args) throws FileNotFoundException{
		Comparator<PointnD> c = new hyperorthogonalWellFoldedHilbertCompare(new PointnD(0,0,0), new PointnD(1024,1024,1024), 3);
		
		
		//PointnD A = new PointnD(1,1,1);
		//PointnD B = new PointnD(2,2,2);
		//System.out.print(c.compare(A, B));
		
		PointnD[] arr = new PointnD[8];
		Random r = new Random();
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					arr[k + 2*j + 4*i] = new PointnD(128*i+64, 128*j+64, 128*k+64);
				}
			}
		}
		Arrays.sort(arr, c);
		
		Point2D[] projectedArr = PointnD.shearProjectArray(arr);
		
		PointnD[] box = {new PointnD(0,0,0), new PointnD(0,0,1024), new PointnD(0,1024, 0), new PointnD(1024, 0, 0), new PointnD(1024, 1024, 0), new PointnD(1024, 0, 1024), new PointnD(0,1024,1024), new PointnD(1024, 1024, 1024)};
		Point2D[] projectedBox = PointnD.shearProjectArray(box);
		
		Line2D[] boxLines = {new Line2D(projectedBox[0], projectedBox[1]), new Line2D(projectedBox[0], projectedBox[2]), new Line2D(projectedBox[0], projectedBox[3]),
							new Line2D(projectedBox[1], projectedBox[5]), new Line2D(projectedBox[1], projectedBox[6]), new Line2D(projectedBox[2], projectedBox[4]),
							new Line2D(projectedBox[2], projectedBox[6]),	new Line2D(projectedBox[3], projectedBox[4]), new Line2D(projectedBox[3], projectedBox[5]),
							new Line2D(projectedBox[4], projectedBox[7]), new Line2D(projectedBox[5], projectedBox[7]), new Line2D(projectedBox[6], projectedBox[7])};
		
		for(Line2D L: boxLines) {
			L.setThickness(20);
			L.setColor("black");
		}
		
		XMLGraphicBuilder pic = new XMLGraphicBuilder(2048, 2048);
		pic.addArray(projectedArr);
		pic.addArray(Line2D.getChain(projectedArr));
		pic.addArray(boxLines);
		PrintWriter fileout = new PrintWriter("hyperfirstorder.svg");
		fileout.print(pic.build());
		fileout.close();
	}

}
