public class PointnD {
	private int dimension;
	private int[] coords;
	private int thickness = 3;
	private String color = "black";
	
	public PointnD(int... nums) {
		this.dimension = nums.length;
		this.coords = nums.clone();
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
		if(pos < this.dimension) this.coords[pos] = value;
	}
	
	public int getCoord(int pos) {
		return this.coords[pos];
	}
	
	public double absValue() {
		double result = 0;
		for(int i = 0; i < this.dimension; i++) {
			result += this.coords[i]*this.coords[i];
		}
		return Math.sqrt(result);
	}
	
	public static Point2D[] shearProjectArray(PointnD[] arr) {
		Point2D[] result = new Point2D[arr.length];
		for(int i = 0; i < arr.length; i++) {
			result[i] = arr[i].shearProject();
		}
		return result;
	}
	
	public Point2D shearProject() {
		if(dimension != 3) return null;
		return new Point2D((int) Math.round(coords[0]+0.5*coords[2]), (int) Math.round(coords[1]+0.2*coords[2]));
	}
}