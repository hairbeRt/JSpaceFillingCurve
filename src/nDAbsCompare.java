import java.util.Comparator;

public class nDAbsCompare implements Comparator<PointnD> {
	private int dim;
	private nDAlphabeticCompare c;

	public nDAbsCompare(int dimension) {
		this.dim = dimension;
		this.c = new nDAlphabeticCompare(dimension);
	}

	public int compare(PointnD A, PointnD B) {
		if (A.getDimension() != this.dim || B.getDimension() != this.dim)
			throw new java.lang.ArrayIndexOutOfBoundsException();

		if (A.absValue() == B.absValue())
			return c.compare(A, B);
		return A.absValue() < B.absValue() ? -1 : 1;
	}
}