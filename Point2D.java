import java.lang.Math;

public class Point2D  extends XMLGraphicObject{
	private int x;
	private int y;
	
	private int thickness = 5;
	private String color = "black";

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

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D(Point2D A) {
		this(A.getX(),A.getY());
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

}
