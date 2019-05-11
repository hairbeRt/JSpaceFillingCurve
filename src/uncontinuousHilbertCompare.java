import java.util.Comparator;

public class uncontinuousHilbertCompare implements Comparator<Point2D>{
	private Point2D lowerLeft;
	private Point2D upperRight;

	public uncontinuousHilbertCompare(Point2D lowerLeft, Point2D upperRight) {
		this.lowerLeft = lowerLeft;
		this.upperRight = upperRight;
	}
	
	public uncontinuousHilbertCompare(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY) {
		lowerLeft = new Point2D(lowerLeftX, lowerLeftY);
		upperRight = new Point2D(upperRightX, upperRightY);
	}

	public int compare(Point2D arg0, Point2D arg1) {
		if(arg0.equals(arg1)) return 0;
		Point2D currentLowerLeft = new Point2D(lowerLeft);
		Point2D currentUpperRight = new Point2D(upperRight);
		
		Point2D relativeOrigin = new Point2D(0,0);
		int arg0Quad, arg1Quad;
		
		while(!currentLowerLeft.equals(currentUpperRight)) {
			arg0Quad = 0;
			arg1Quad = 0;
			
			relativeOrigin.setX(
						(currentLowerLeft.getX() + currentUpperRight.getX())/2
					);
			relativeOrigin.setY(
						(currentLowerLeft.getY() + currentUpperRight.getY())/2
					);
			
			if(arg0.isBelow(relativeOrigin)) arg0Quad+=2;
			if(arg0.isRightOf(relativeOrigin)) arg0Quad +=1;
			
			if(arg1.isBelow(relativeOrigin)) arg1Quad+=2;
			if(arg1.isRightOf(relativeOrigin)) arg1Quad +=1;
			
			if(arg0Quad != arg1Quad) return (arg0Quad < arg1Quad)?-1:1;
			
			switch(arg0Quad) {
			case 0:
				currentLowerLeft.setY(relativeOrigin.getY());
				currentUpperRight.setX(relativeOrigin.getX());
				break;
			case 1:
				currentLowerLeft.setX(relativeOrigin.getX());
				currentLowerLeft.setY(relativeOrigin.getY());
				break;
			case 2:
				currentUpperRight.setX(relativeOrigin.getX());
				currentUpperRight.setY(relativeOrigin.getY());
				break;
			case 3:
				currentLowerLeft.setX(relativeOrigin.getX());
				currentUpperRight.setY(relativeOrigin.getY());
				break;
			}
		}
		return 0;
		
		
	}

}
