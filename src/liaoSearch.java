import java.util.Arrays;
import java.util.Comparator;

public class liaoSearch {
	private int dim;
	private PointnD[][] sortedArrs;
	private Comparator[] arrComps;
	private PointnD[] neighbors;

	// Only for standard hilbert curve at the moment
	// Todo: Generalize for all Comparators
	public liaoSearch(int dimension, PointnD lowerLeft, PointnD upperRight, PointnD[] arr) {
		if (lowerLeft.getDimension() != dimension || upperRight.getDimension() != dimension)
			throw new java.lang.ArrayIndexOutOfBoundsException();

		neighbors = new PointnD[dimension + 1];
		PointnD newLowerLeft = lowerLeft.scale(2).subtract(upperRight);
		PointnD newUpperRight = new PointnD(upperRight);
		PointnD translator = upperRight.subtract(lowerLeft);
		this.dim = dimension;

		sortedArrs = new PointnD[dimension + 1][arr.length];
		arrComps = new Comparator[dimension + 1];

		for (int i = 0; i <= dimension; i++) {
			// Generiere verschobene Rahmenfenster
			arrComps[i] = new hyperorthogonalWellFoldedHilbertCompare(
					newLowerLeft.add(translator.scale((double) (i) / (dimension + 1))),
					newUpperRight.add(translator.scale((double) (i) / (dimension + 1))), dimension);
			for (int j = 0; j < arr.length; j++) {
				sortedArrs[i][j] = new PointnD(arr[j]);
			}
			Arrays.sort(sortedArrs[i], (Comparator<PointnD>) arrComps[i]);
		}
	}

	public PointnD liaoNearestNeighborSearch(PointnD queryPoint) {
		// Einfache Idee: Mit allen Rahmenfenstern approximative Suche machen, mit der neuen Datenstruktur exakte Suche machen
		for (int i = 0; i <= this.dim; i++) {
			neighbors[i] = queryPoint.getBinarySearchNeighbor(arrComps[i], sortedArrs[i]);
		}
		return queryPoint.getNearestNeighbor(neighbors);
	}

}