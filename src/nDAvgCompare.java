import java.util.Comparator;

public class nDAvgCompare implements Comparator<PointnD> {
	private int dim;
	private nDAlphabeticCompare c;

	public nDAvgCompare(int dimension) {
		this.dim = dimension;
		this.c = new nDAlphabeticCompare(dimension);
	}

	private double avg(PointnD X) {
		double result = 0;
		for (int i = 0; i < this.dim; i++) {
			result += X.getCoord(i);
		}
		return result / this.dim;
	}

	public int compare(PointnD A, PointnD B) {
		if (A.getDimension() != this.dim || B.getDimension() != this.dim)
			throw new java.lang.ArrayIndexOutOfBoundsException();

		if (avg(A) == avg(B))
			return c.compare(A, B);
		return avg(A) < avg(B) ? -1 : 1;
	}
}