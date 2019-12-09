import java.util.Comparator;

public class PointnD {
	private int dimension;
	private int[] coords;
	private int thickness = 3;
	private String color = "black";

	public PointnD(int... nums) {
		this.dimension = nums.length;
		this.coords = nums.clone();
	}

	public PointnD(PointnD A) {
		this.dimension = A.getDimension();
		this.coords = A.getCoords().clone();
	}

	public PointnD add(double val) {
		int[] result = new int[this.dimension];
		for (int i = 0; i < this.dimension; i++) {
			result[i] = (int) Math.round(this.coords[i] + val);
		}

		return new PointnD(result);
	}

	public PointnD scale(double val) {
		int[] result = new int[this.dimension];
		for (int i = 0; i < this.dimension; i++) {
			result[i] = (int) Math.round(this.coords[i] * val);
		}

		return new PointnD(result);
	}

	public PointnD add(PointnD A) {
		int[] result = new int[this.dimension];
		for (int i = 0; i < this.dimension; i++) {
			result[i] = this.coords[i] + A.getCoord(i);
		}

		return new PointnD(result);
	}

	public PointnD subtract(PointnD A) {
		int[] result = new int[this.dimension];
		for (int i = 0; i < this.dimension; i++) {
			result[i] = this.coords[i] - A.getCoord(i);
		}

		return new PointnD(result);
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCoord(int value, int pos) {
		if (pos < this.dimension)
			this.coords[pos] = value;
	}

	public int getCoord(int pos) {
		return this.coords[pos];
	}

	public double absValue() {
		double result = 0;
		for (int i = 0; i < this.dimension; i++) {
			result += this.coords[i] * this.coords[i];
		}
		return Math.sqrt(result);
	}

	public double distanceTo(PointnD A) {
		double result = 0;
		for (int i = 0; i < this.dimension; i++) {
			result += (this.getCoord(i) - A.getCoord(i)) * (this.getCoord(i) - A.getCoord(i));
		}

		return Math.sqrt(result);
	}

	public PointnD getNearestNeighbor(PointnD[] arr) {
		PointnD result = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (this.distanceTo(arr[i]) < this.distanceTo(result))
				result = arr[i];
		}
		return result;
	}

	public PointnD getBinarySearchNeighbor(Comparator<PointnD> C, PointnD[] arr) {
		int lowerbound = 0, upperbound = arr.length - 1;
		PointnD result;

		while (upperbound - lowerbound > 1) {
			if (this.equals(arr[lowerbound]))
				return arr[lowerbound];
			if (this.equals(arr[upperbound]))
				return arr[upperbound];
			if (this.equals(arr[(upperbound + lowerbound) / 2]))
				return arr[(upperbound + lowerbound) / 2];

			if (C.compare(this, arr[(upperbound + lowerbound) / 2]) < 0) {
				upperbound = (upperbound + lowerbound) / 2;
			} else {
				lowerbound = (upperbound + lowerbound) / 2;
			}
		}

		if (this.distanceTo(arr[lowerbound]) > this.distanceTo(arr[upperbound])) {
			result = arr[upperbound];
		} else {
			result = arr[lowerbound];
		}
		return result;
	}

	public static Point2D[] shearProjectArray(PointnD[] arr) {
		Point2D[] result = new Point2D[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i].shearProject();
		}
		return result;
	}

	public Point2D shearProject() {
		if (dimension != 3)
			return null;
		return new Point2D((int) Math.round(coords[0] + 0.4 * coords[2]),
				(int) Math.round(coords[1] + 0.18 * coords[2]));
	}
}