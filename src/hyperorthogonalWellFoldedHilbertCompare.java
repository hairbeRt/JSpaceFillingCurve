import java.util.Comparator;
import java.util.Arrays;

public class hyperorthogonalWellFoldedHilbertCompare implements Comparator<PointnD> {

	// Window parameters
	public PointnD lowerLeft;
	public PointnD upperRight;
	public int dimension;

	// Algorithm variables
	double[] p;
	double[] q;
	int direction;
	int[] unsgnedPrm;
	int[] unsgnedChildPrm;

	int[] sgnsInvPrm;
	int[] sgnsInvChildPrm;

	int axis;
	int entrAxs;
	int extAxs;
	int quartAxs;
	int sbcubeId;
	int pInTheBack;
	int qInTheBack;
	int orientation;
	long D;

	private boolean pInTheBack() {
		return pInTheBack == 1;
	}

	/*
	 * private boolean qInTheBack() { return qInTheBack == 1; }
	 */

	private static final void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	/*
	 * private static final void swap(double[] arr, int a, int b) { double temp
	 * = arr[a]; arr[a] = arr[b]; arr[b] = temp; }
	 */

	private static final int sign(int n) {
		if (n == 0)
			return 0;
		return n > 0 ? 1 : -1;
	}

	private boolean isneg(int n) {
		return n < 0;
	}

	public hyperorthogonalWellFoldedHilbertCompare(PointnD lowerLeft, PointnD upperRight, int dim) {
		if (lowerLeft.getDimension() != dim || upperRight.getDimension() != dim)
			throw new java.lang.ArrayIndexOutOfBoundsException();

		this.lowerLeft = lowerLeft;
		this.upperRight = upperRight;
		this.dimension = dim;
		this.D = 1 << dim;

		p = new double[dimension + 1];
		q = new double[dimension + 1];

		unsgnedPrm = new int[dimension + 1];
		sgnsInvPrm = new int[dimension + 1];

		unsgnedChildPrm = new int[dimension + 1];
		sgnsInvChildPrm = new int[dimension + 1];
	}

	public int compare(PointnD A, PointnD B) {
		if (A.getDimension() != this.dimension || B.getDimension() != this.dimension)
			throw new ArrayIndexOutOfBoundsException();

		/*
		 * Maybe for funny use I thought you could make p[0] and q[0] the
		 * absolute values could be useful
		 */

		/*
		 * Define p and q as the scaling of A and B to the unit cube
		 */

		// Sanity check
		p[0] = 0;
		q[0] = 0;
		for (int i = 1; i <= dimension; i++) {
			p[i] = (A.getCoord(i - 1) - lowerLeft.getCoord(i - 1))
					/ (double) (upperRight.getCoord(i - 1) - lowerLeft.getCoord(i - 1));
			q[i] = (B.getCoord(i - 1) - lowerLeft.getCoord(i - 1))
					/ (double) (upperRight.getCoord(i - 1) - lowerLeft.getCoord(i - 1));
			unsgnedPrm[i] = i;
			sgnsInvPrm[i] = 1;
		}
		unsgnedPrm[dimension] = dimension;
		sgnsInvPrm[dimension] = 1;
		direction = 1;

		/*
		 * Comments from here on by H. Haverkort
		 */
		while (!Arrays.equals(p, q)) {

			entrAxs = unsgnedPrm[dimension];
			extAxs = unsgnedPrm[dimension - 1];
			quartAxs = unsgnedPrm[dimension];
			sbcubeId = 0;

			for (int i = 1; i <= dimension; i++) {
				axis = quartAxs;
				quartAxs = unsgnedPrm[dimension - i];
				sbcubeId *= 2;

				// figure out in which half of the cube p and q are
				p[axis] *= 2;
				pInTheBack = (int) p[axis];
				p[axis] = p[axis] - pInTheBack;

				q[axis] *= 2;
				qInTheBack = (int) q[axis];
				q[axis] = q[axis] - qInTheBack;

				if (pInTheBack != qInTheBack) {
					return direction * sgnsInvPrm[axis] * sign(qInTheBack - pInTheBack);
				}

				// determine sign such that entry point lies on outside
				sgnsInvChildPrm[axis] = 1 - 2 * pInTheBack;

				if (pInTheBack() == isneg(sgnsInvPrm[axis])) {
					// p and q in 1st half
					if (i > 2) {
						unsgnedChildPrm[i - 2] = extAxs;
					}
					extAxs = axis;
				} else {
					// p and q in 2nd half
					if (i > 2) {
						unsgnedChildPrm[i - 2] = entrAxs;

					}
					entrAxs = axis;
					sbcubeId++;
					// Should this maybe be sgnsInvChildPrm[quartAxs]=...?
					sgnsInvPrm[quartAxs] = -sgnsInvPrm[quartAxs];
				}
			}

			// fill in last two elements of unsigned permutation
			unsgnedChildPrm[dimension - 1] = unsgnedPrm[1];
			unsgnedChildPrm[dimension] = entrAxs + extAxs - unsgnedPrm[1];

			// in first and last subcube it is the other way around
			if (sbcubeId == 0 || sbcubeId == D - 1) {
				swap(unsgnedChildPrm, dimension - 1, dimension);
			}
			// correct first element of permutation in last quarter
			if (sbcubeId >= D * 3 / 4) {
				unsgnedChildPrm[1] = unsgnedPrm[dimension];
			}

			// correct entry point to be on inside w.r.t. unsgnedPrm[1]
			sgnsInvChildPrm[unsgnedPrm[1]] = -sgnsInvChildPrm[unsgnedPrm[1]];
			// correct entry point to be on inside w.r.t. orientation of subcube
			orientation = unsgnedChildPrm[dimension];
			if (sbcubeId != 0 && sbcubeId != D - 1) {
				sgnsInvChildPrm[orientation] = -sgnsInvChildPrm[orientation];
			}

			for (int i = 1; i <= dimension; i++) {
				unsgnedPrm[i] = unsgnedChildPrm[i];
				sgnsInvPrm[i] = sgnsInvChildPrm[i];
			}

			// if type 1, reverse direction
			if (extAxs == orientation) {
				direction = -direction;
			}

		}
		return 0; // p and q are equal
	}

}