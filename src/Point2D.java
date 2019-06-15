import java.lang.Math;
import java.util.Comparator;

public class Point2D  extends XMLGraphicObject{
	private int x;
	private int y;
	
	private int thickness = 5;
	private String color = "black";
	
	public Point2D(int x, int y) {
			this.x = x;
			this.y = y;
	}
	
	public Point2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point2D(Point2D A) {
		this(A.getX(),A.getY());
	}
	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoordinates(Point2D A) {
		this.x = A.getX();
		this.y = A.getY();
	}

	public double abs(){
		return Math.sqrt((x*x) + (y*y));
	}

	public Point2D scale(double l){
		return new Point2D((int) Math.round(l*x), (int) Math.round(l*y));
	}

	public Point2D add(Point2D A){
		return new Point2D(x + A.x, y + A.y);
	}
	
	public Point2D subtract(Point2D A) {
		return new Point2D(x-A.x, y-A.y);
	}

	public String toString(){
		return "(" + x + "," + y + ")";
	}

	public boolean equals(Object o){
		if(o instanceof Point2D){
			Point2D P = (Point2D) o;
			return x == P.x && y == P.y;
		}
		return false;

	}
	
	public boolean isLeftOf(Point2D A) {
		return this.x <= A.x;
	}

	public boolean isRightOf(Point2D A) {
		return this.x >= A.x;
	}
	
	public boolean isBelow(Point2D A) {
		return this.y <= A.y;
	}
	
	public boolean isAbove(Point2D A) {
		return this.y >= A.y;
	}
	
	public double distanceTo(Point2D A) {
		return this.subtract(A).abs();
	}
	
	public String XMLSource() {
		return "<circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"" + this.thickness + "px\" fill=\"" + this.color +"\" />\n";
	}
	
	public Point2D getNearestNeighbor(Point2D[] arr) {
		Point2D result = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(this.distanceTo(arr[i]) < this.distanceTo(result)) result = arr[i];
		}
		return result;
	}
	
	public Point2D getBinarySearchNeighbor(Comparator<Point2D> C, Point2D[] arr) {
		int lowerbound = 0, upperbound = arr.length - 1;
		Point2D result;

		while (upperbound - lowerbound > 1) {
			if(this.equals(arr[lowerbound])) return arr[lowerbound];
			if(this.equals(arr[upperbound])) return arr[upperbound];
			if(this.equals(arr[(upperbound + lowerbound) / 2])) return arr[(upperbound + lowerbound) / 2];
			
			if (C.compare(this, arr[(upperbound + lowerbound) / 2]) < 0) {
				upperbound = (upperbound + lowerbound) / 2;
			} else {
				lowerbound = (upperbound + lowerbound) / 2;
			}
		}

		if (this.distanceTo(arr[lowerbound]) > this.distanceTo(arr[upperbound])) {
			result = arr[upperbound];
		} else {
			result = arr[lowerbound];
		}
		return result;
	}

}
