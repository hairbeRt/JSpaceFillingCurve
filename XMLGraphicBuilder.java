import java.util.LinkedList;
import java.util.Collection;

public class XMLGraphicBuilder {
	
	private StringBuffer XMLSource;
	int width;
	int height;
	
	private LinkedList<XMLGraphicObject> graphicObjects;
	private LinkedList<Collection<XMLGraphicObject>> graphicObjectCollections;
	private LinkedList<XMLGraphicObject[]> graphicObjectArrays;
	
	
	public XMLGraphicBuilder(int width, int height){
		this.width = width;
		this.height = height;
		this.XMLSource = new StringBuffer();
		this.graphicObjects = new LinkedList<XMLGraphicObject>();
		this.graphicObjectCollections = new LinkedList<Collection<XMLGraphicObject>>();
		this.graphicObjectArrays = new LinkedList<XMLGraphicObject[]>();

	}
	
	public boolean addObject(XMLGraphicObject o) {
		return graphicObjects.add(o);
	}
	
	public boolean removeObject(XMLGraphicObject o) {
		return graphicObjects.remove(o);
	}
	
	public boolean addCollection(Collection<XMLGraphicObject> s) {
		return graphicObjectCollections.add(s);
	}
	
	public boolean removeCollection(Collection<XMLGraphicObject> s) {
		return graphicObjectCollections.remove(s);
	}
	
	public boolean addArray(XMLGraphicObject[] arr){
		return graphicObjectArrays.add(arr);
	}
	
	public boolean removeArray(XMLGraphicObject[] arr){
		return graphicObjectArrays.remove(arr);
	}
	
	public String build() {
		
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
		
		for(XMLGraphicObject o : graphicObjects) {
			XMLSource.append(o.getXMLSource());
		}
		
		for(Collection<XMLGraphicObject> s : graphicObjectCollections) {
			for(XMLGraphicObject o : s) {
				XMLSource.append(o.getXMLSource());
			}
		}
		
		for(XMLGraphicObject[] arr : graphicObjectArrays) {
			for(XMLGraphicObject o : arr) {
				XMLSource.append(o.getXMLSource());
			}
		}
		XMLSource.append("</svg>");
		return XMLSource.toString();
	}
	
}
