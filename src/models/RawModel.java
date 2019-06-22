package models;

public class RawModel {
	
	private int vaoID;
	private int vertexCount;
	private float height;
	private float width;
	
	public RawModel(int vaoID, int vertexCount, float height, float width){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.height = height;
		this.width = width;
	}

	public float getHeight() {
		return height;
	}


	public float getWidth() {
		return width;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
	

}
