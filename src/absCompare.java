import java.util.Comparator;


public class absCompare implements Comparator<Point2D> {
	//Sort the points by absolute value. If the points have the same absolute value, sort by complex argument.
	public int compare(Point2D arg0, Point2D arg1) {
		if(arg0.equals(arg1)) return 0;
		if(arg0.abs() != arg1.abs()) return arg0.abs() < arg1.abs()?-1:1;
		return Math.atan2(arg0.getX(), arg0.getY()) < Math.atan2(arg1.getX(), arg1.getY())?-1:1;


	}
}
