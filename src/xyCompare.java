import java.util.Comparator;

public class xyCompare implements Comparator<Point2D> {
	//Sort points by x-value; if they have the same x-value, then sort by y-value.
	@Override
	public int compare(Point2D arg0, Point2D arg1) {
		if(arg0.equals(arg1)) return 0;
		if(arg0.getX() != arg1.getX()) return arg0.getX() - arg1.getX();
		return arg0.getY() - arg1.getY();
	}

}
