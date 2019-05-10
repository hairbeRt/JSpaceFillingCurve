import java.lang.Math;

public class Point2D {
	private int x;
	private int y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
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

	public double abs(){
		return Math.sqrt((x*x) + (y*y));
	}

	public void scale(double l){
		x = (int) Math.round(l*x);
		y = (int) Math.round(l*y);
	}

	public Point2D add(Point2D A){
		return new Point2D(x + A.x, y + A.y);
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


}
