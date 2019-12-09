
public abstract class XMLGraphicObject {
	private boolean show;

	public XMLGraphicObject() {
		this.show = true;
	}

	public void show() {
		this.show = true;
	}

	public void hide() {
		this.show = false;
	}

	public String getXMLSource() {
		if (this.show) {
			return this.XMLSource();
		}
		return "";
	}

	public abstract String XMLSource();
}