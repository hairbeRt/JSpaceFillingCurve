import java.util.Comparator;

public class continuousHilbertCompare implements Comparator<Point2D>{
	private Point2D lowerLeft;
	private Point2D upperRight;
	
	private Point2D currentLowerLeft;
	private Point2D currentUpperRight;
	private Point2D relativeOrigin;
	
	private int arg0Quad;
	private int arg1Quad;
	private boolean zeroTwoFlip;
	private boolean oneThreeFlip;

	public continuousHilbertCompare(Point2D lowerLeft, Point2D upperRight) {
		this.lowerLeft = lowerLeft;
		this.upperRight = upperRight;
		
		currentLowerLeft = new Point2D(lowerLeft);
		currentUpperRight = new Point2D(upperRight);
		relativeOrigin = new Point2D(0,0);
	}
	
	public continuousHilbertCompare(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY) {
		lowerLeft = new Point2D(lowerLeftX, lowerLeftY);
		upperRight = new Point2D(upperRightX, upperRightY);
		
		currentLowerLeft = new Point2D(lowerLeft);
		currentUpperRight = new Point2D(upperRight);
		relativeOrigin = new Point2D(0,0);
	}

	public int compare(Point2D arg0, Point2D arg1) {
		zeroTwoFlip = false;
		oneThreeFlip = false;
		
		if(arg0.equals(arg1)) return 0;
		currentLowerLeft.setCoordinates(lowerLeft);
		currentUpperRight.setCoordinates(upperRight);
		
		while(!currentLowerLeft.equals(currentUpperRight)) {
			arg0Quad = 0;
			arg1Quad = 0;
			
			relativeOrigin.setCoordinates(
						(currentLowerLeft.getX() + currentUpperRight.getX())/2,
						(currentLowerLeft.getY() + currentUpperRight.getY())/2
					);
			
			if(arg0.isRightOf(relativeOrigin)) arg0Quad+=2;
			if((!arg0.isRightOf(relativeOrigin)) ^ arg0.isBelow(relativeOrigin)) arg0Quad+=1;
			
			if(arg1.isRightOf(relativeOrigin)) arg1Quad+=2;
			if((!arg1.isRightOf(relativeOrigin)) ^ arg1.isBelow(relativeOrigin)) arg1Quad+=1;
			
			switch(arg0Quad) {
			case 0:
				currentUpperRight.setCoordinates(relativeOrigin);
				break;
			case 1:
				currentLowerLeft.setY(relativeOrigin.getY());
				currentUpperRight.setX(relativeOrigin.getX());
				break;
			case 2:
				currentLowerLeft.setCoordinates(relativeOrigin);
				break;
			case 3:
				currentLowerLeft.setX(relativeOrigin.getX());
				currentUpperRight.setY(relativeOrigin.getY());
				break;
			}
			
			if(zeroTwoFlip) {
				if(arg0Quad%2 == 0) arg0Quad = 2-arg0Quad;
				if(arg1Quad%2 == 0) arg1Quad = 2-arg1Quad;
			}
			
			if(oneThreeFlip) {
				if(arg0Quad%2 == 1) arg0Quad = (arg0Quad+2)%4;
				if(arg1Quad%2 == 1) arg1Quad = (arg1Quad+2)%4;
			}
			
			switch(arg0Quad) {
			case 0:
				oneThreeFlip = !oneThreeFlip;
				break;
			case 3:
				zeroTwoFlip = !zeroTwoFlip;
				
			}
			
			if(arg0Quad != arg1Quad) return (arg0Quad<arg1Quad)?-1:1;
		}
		return 0;
		
		
	}

}
