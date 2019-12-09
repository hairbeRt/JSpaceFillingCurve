import java.util.Comparator;

public class nDAlphabeticCompare implements Comparator<PointnD> {
	private int dim;

	public nDAlphabeticCompare(int dimension) {
		this.dim = dimension;
	}

	public int compare(PointnD A, PointnD B) {
		if (A.getDimension() != this.dim || B.getDimension() != this.dim)
			throw new java.lang.ArrayIndexOutOfBoundsException();

		for (int i = 0; i < this.dim; i++) {
			if (A.getCoord(i) == A.getCoord(i))
				continue;
			return A.getCoord(i) - A.getCoord(i);
		}
		return 0;

	}
}