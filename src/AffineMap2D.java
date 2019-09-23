import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class AffineMap2D {
	public double [] coefficients;
	
	public Point2D apply(Point2D P) {
		return new Point2D(		(int) Math.round((coefficients[0]*P.getX()) + (coefficients[2]*P.getY()) + coefficients[4]), 
								(int) Math.round((coefficients[1]*P.getX()) + (coefficients[3]*P.getY()) + coefficients[5]));
	}
	
	public Point2D applyTo(Point2D P) {
		P.setCoordinates(		(int) Math.round((coefficients[0]*P.getX()) + (coefficients[2]*P.getY()) + coefficients[4]), 
								(int) Math.round((coefficients[1]*P.getX()) + (coefficients[3]*P.getY()) + coefficients[5]));
		return P; //Return P for convenience
	}
	
	
	
	public AffineMap2D(double[] coefficients) {
		super();
		this.coefficients = coefficients.clone();
	}

	public static AffineMap2D getMapFromTriangle(Point2D[] srcPoints, Point2D[] dstPoints) {
		if(srcPoints.length < 3 || dstPoints.length < 3) return null;
		
		Point2D srcBase = srcPoints[0];
		Point2D dstBase = dstPoints[0];
		
		Point2D srcVec1 = srcPoints[1].subtract(srcBase);
		Point2D srcVec2 = srcPoints[2].subtract(srcBase);
		
		Point2D dstVec1 = dstPoints[1].subtract(dstBase);
		Point2D dstVec2 = dstPoints[2].subtract(dstBase);
		
		AffineTransform UnitCoordsToSource = new AffineTransform(srcVec1.getX(), srcVec1.getY(), srcVec2.getX(), srcVec2.getY(), srcBase.getX(), srcBase.getY());
		
		AffineTransform UnitCoordsToDestination = new AffineTransform(dstVec1.getX(), dstVec1.getY(), dstVec2.getX(), dstVec2.getY(), dstBase.getX(), dstBase.getY());
		
		//It is the responsibility of the programmer to guarantee that we are indeed dealing with actual triangles (such that its defining matrix is invertible) lol
		try {
			UnitCoordsToSource.invert();
		} catch(NoninvertibleTransformException e) {}
		
		UnitCoordsToDestination.concatenate(UnitCoordsToSource);
		
		double[] coeffMatrix = new double[6];
		UnitCoordsToDestination.getMatrix(coeffMatrix);
		
		return new AffineMap2D(coeffMatrix);
	}
}