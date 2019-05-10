
public class XMLGraphicBuilder {
	private StringBuffer XMLSource;
	
	
	public XMLGraphicBuilder(int width, int height){
		
		XMLSource = new StringBuffer();
		
		XMLSource.append(
				"<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
				"<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width +"\" height=\"" + height + "\">\n" +
				"	<defs>\n" +
				"		<marker id=\"arrow\" markerWidth=\"5\" markerHeight=\"5\" refX=\"0\" refY=\"3\" orient=\"auto\" markerUnits=\"strokeWidth\" >\n" +
				"			<path d=\"M0,0 L0,6 L9,3 z\" fill=\"#000\" />\n" +
				"		</marker>\n" +
				"	</defs>\n" +
				"\n" +
				"	<line x1=\"0\" y1=\"0\" x2=\"" + width + "\" y2=\"0\" style=\"fill:none;stroke:black;stroke-width:3\" marker-end=\"url(#arrow)\" />\n" +
				"	<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"" + height + "\" style=\"fill:none;stroke:black;stroke-width:3\" marker-end=\"url(#arrow)\" />\n"
					);

	}
	
	public void drawPoint(Point2D A, int thickness) {
		XMLSource.append(
				"	<circle cx=\"" + A.getX() + "\" cy=\"" + A.getY() + "\" r=\"" + thickness + "px\" />\n"
				);
	}
	
	public void drawPoint(Point2D A) {
		drawPoint(A, 5);
	}
	
	public void drawPoints(Point2D[] arr, int thickness) {
		for(Point2D A : arr) {
			drawPoint(A, thickness);
		}
	}
	
	public void drawPoints(Point2D[] arr) {
		drawPoints(arr, 5);
	}
	
	public void drawLine(Point2D A, Point2D B, String color, int thickness) {
		XMLSource.append(
				"	<line x1=\"" + A.getX() + "\" y1=\"" + A.getY() + "\" x2=\"" + B.getX() + "\" y2=\"" + B.getY() + "\" style=\"fill:none;stroke:" + color + ";stroke-width:" + thickness + "\" />\n"
				);
	}
	
	public void drawLine(Point2D A, Point2D B, String color) {
		drawLine(A, B, color, 1);
	}
	
	public void drawLine(Point2D A, Point2D B, int thickness) {
		drawLine(A, B, "black", thickness);
	}
	
	public void drawLine(Point2D A, Point2D B) {
		drawLine(A, B, "black", 1);
	}
	
	public void drawChain(Point2D[] arr, String color, int thickness) {
		for(int i = 0; i < arr.length - 1; i++) {
			drawLine(arr[i], arr[i+1], color, thickness);
		}
	}
	
	public void drawChain(Point2D[] arr, int thickness) {
		drawChain(arr, "black", thickness);
	}
	
	public void drawChain(Point2D[] arr, String color) {
		drawChain(arr, color, 1);
	}
	
	public void drawChain(Point2D[] arr) {
		drawChain(arr, "black", 1);
	}
	
	public String build() {
		XMLSource.append("</svg>");
		return XMLSource.toString();
	}
	
}
