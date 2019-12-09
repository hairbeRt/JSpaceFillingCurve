import java.util.Comparator;

public class nDVarCompare implements Comparator<PointnD> {
	private int dim;
	private nDAlphabeticCompare c;

	public nDVarCompare(int dimension) {
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

	private double var(PointnD X) {
		double avg = avg(X);
		double result = 0;
		for (int i = 0; i < this.dim; i++) {
			result += (X.getCoord(i) - avg) * (X.getCoord(i) - avg);
		}
		return result / this.dim;
	}

	public int compare(PointnD A, PointnD B) {
		if (A.getDimension() != this.dim || B.getDimension() != this.dim)
			throw new java.lang.ArrayIndexOutOfBoundsException();

		if (var(A) == var(B))
			return c.compare(A, B);
		return var(A) < var(B) ? -1 : 1;
	}
}