
public class Line2D extends XMLGraphicObject {
	private Point2D A;
	private Point2D B;

	private int thickness = 1;
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

	public Line2D(Point2D a, Point2D b) {
		A = a;
		B = b;
	}

	public Point2D getA() {
		return A;
	}

	public void setA(Point2D a) {
		A = a;
	}

	public Point2D getB() {
		return B;
	}

	public void setB(Point2D b) {
		B = b;
	}

	public String XMLSource() {
		return "<line x1=\"" + A.getX() + "\" y1=\"" + A.getY() + "\" x2=\"" + B.getX() + "\" y2=\"" + B.getY()
				+ "\" style=\"fill:none;stroke:" + color + ";stroke-width:" + thickness + "\" />\n";
	}

	public static Line2D[] getChain(Point2D[] arr) {
		Line2D[] lines = new Line2D[arr.length - 1];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new Line2D(arr[i + 1], arr[i]);
		}

		return lines;
	}

}