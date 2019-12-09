import java.util.Comparator;

public class DaunCompare implements Comparator<Point2D> {
	public Point2D origin;
	// Window is always a rectangle oriented at the coordinate axes from the
	// origin with a specifiec height and width
	/*
	 * 
	 * 
	 * Width:Height MUST be 3:2 ratio, recommended lengths 3*2^n and 2^n-1
	 * 
	 * 
	 */
	int width;
	int height;

	int a, b; // Just makes some of the equations nicer

	// Variables used in the comparison operator
	Point2D aPoint, bPoint;
	public Point2D[][] tilingTriangles;
	Point2D[] windowTriangle;
	int aTile, bTile;
	AffineMap2D[] tileMaps;
	int relativeX, relativeY;

	public DaunCompare(Point2D origin, int width, int height) {
		super();
		this.origin = new Point2D(origin);
		this.width = width;
		this.height = height;
		aPoint = new Point2D();
		bPoint = new Point2D();
		tilingTriangles = new Point2D[16][3];
		windowTriangle = new Point2D[] { new Point2D(origin), origin.add(width - 1, 0), origin.add(0, height - 1) };
		tileMaps = new AffineMap2D[16];

		// Prepare to hand-define the lookup table for the tilings!
		a = height / 4;
		b = width / 4;
		tilingTriangles[0] = new Point2D[] { origin.add(0, 0), origin.add(b, 0), origin.add(0, a) };
		tilingTriangles[1] = new Point2D[] { origin.add(2 * b, a), origin.add(b, a), origin.add(2 * b, 0) };
		tilingTriangles[2] = new Point2D[] { origin.add(2 * b, 0), origin.add(3 * b, 0), origin.add(2 * b, a) };
		tilingTriangles[3] = new Point2D[] { origin.add(4 * b, a), origin.add(3 * b, a), origin.add(4 * b, 0) };

		tilingTriangles[4] = new Point2D[] { origin.add(a, a), origin.add(a, a + b), origin.add(0, a) };
		tilingTriangles[5] = new Point2D[] { origin.add(0, 4 * a), origin.add(0, a + b), origin.add(a, 4 * a) };

		tilingTriangles[6] = new Point2D[] { origin.add(a, 3 * a), origin.add(a + b, 3 * a), origin.add(a, 4 * a) };
		tilingTriangles[7] = new Point2D[] { origin.add(a, 2 * a), origin.add(a + b, 2 * a), origin.add(a, 3 * a) };
		tilingTriangles[8] = new Point2D[] { origin.add(a, a), origin.add(a + b, a), origin.add(a, 2 * a) };

		tilingTriangles[9] = new Point2D[] { origin.add(a + b, a + b), origin.add(a + b, a),
				origin.add((2 * a) + b, a + b) };
		tilingTriangles[10] = new Point2D[] { origin.add((2 * a) + b, a + b), origin.add((2 * a) + b, 4 * a),
				origin.add(a + b, a + b) };

		tilingTriangles[11] = new Point2D[] { origin.add(2 * (a + b), 4 * a), origin.add((2 * a) + b, 4 * a),
				origin.add(2 * (a + b), 3 * a) };
		tilingTriangles[12] = new Point2D[] { origin.add(2 * (a + b), 3 * a), origin.add((2 * a) + b, 3 * a),
				origin.add(2 * (a + b), 2 * a) };
		tilingTriangles[13] = new Point2D[] { origin.add(2 * (a + b), 2 * a), origin.add((2 * a) + b, 2 * a),
				origin.add(2 * (a + b), a) };

		tilingTriangles[14] = new Point2D[] { origin.add(4 * b, a), origin.add(4 * b, a + b),
				origin.add(2 * (a + b), a) };
		tilingTriangles[15] = new Point2D[] { origin.add(2 * (a + b), 4 * a), origin.add(2 * (a + b), a + b),
				origin.add(4 * b, 4 * a) };

		/*
		 * For nonstandard traversals of the Daun tiling, you can permute both
		 * the entries of tilingTriangles for the order of tilings as well as
		 * each tiling Triangle to modify the orientation
		 * 
		 */

		for (int i = 0; i < 16; i++) {
			tileMaps[i] = AffineMap2D.getMapFromTriangle(tilingTriangles[i], windowTriangle);
		}

	}

	public int determineTile(Point2D A) {
		relativeX = A.getX() - origin.getX();
		relativeY = A.getY() - origin.getY();

		if (relativeY <= a)
			return relativeX / b;
		if (relativeX <= a)
			return 4 + (relativeY - a) / b;
		if (relativeX <= a + b)
			return 6 + (height - relativeY) / a;
		if (relativeX <= 2 * a + b)
			return 9 + (relativeY - a) / b;
		if (relativeX <= 2 * (a + b))
			return 11 + (height - relativeY) / a;
		return 14 + (relativeY - a - 1) / b;
	}

	public int compare(Point2D A, Point2D B) {
		if (A.equals(B))
			return 0;

		aPoint.setCoordinates(A);
		bPoint.setCoordinates(B);

		while (true) {
			if (aPoint.equals(bPoint))
				System.out.print("Big oops");

			aTile = determineTile(aPoint);
			bTile = determineTile(bPoint);

			if (aTile != bTile)
				return bTile - aTile;
			tileMaps[aTile].applyTo(aPoint);
			tileMaps[bTile].applyTo(bPoint);
		}
	}

}