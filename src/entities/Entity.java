package entities;

import models.RawModel;
import models.TexturedModel;
import org.joml.Vector3f;
import toolbox.Maths;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Entity {

	private RawModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	private Vector3f velocity;
	private Vector3f acceleration;


	private class Tuple<X, Y> {
		public final X x;
		public final Y y;
		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}
	}
	public Entity(RawModel model, Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		this.model = model;
		this.position = position;
		this.velocity = new Vector3f(0,0,0);
		this.acceleration = new Vector3f(0,0,0);
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public void increasePosition() {
		this.position.x += velocity.x;
		this.position.y += velocity.y;
		this.position.z += velocity.z;

	}

	public void normalizeAcceleration(float drag){

		this.velocity.x = Maths.lerp(this.velocity.x, 0, drag);
		this.velocity.y = Maths.lerp(this.velocity.y, 0, drag);
		this.velocity.z = Maths.lerp(this.velocity.z, 0, drag);


		increasePosition();
	}

	public float[] getMax(float width, float height){

		float x = position.x + width;
		float y = position.y + height;

		float[] mx = {x,y};
		return mx;
	}
	public float[] getMin(float width, float height){

		float x = position.x - width;
		float y = position.y - height;

		float[] mn = {x,y};
		return mn;

	}

	public Vector3f getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector3f acceleration) {
		this.acceleration = acceleration;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public RawModel getModel() {
		return model;
	}

	public void setModel(RawModel model) {
		this.model = model;
	}

	public void accelerate(float dx, float dy, float dz){

		if(abs(this.velocity.x) < 1){
		this.velocity.x = dx;}
		if(abs(this.velocity.y) < 1){
			this.velocity.y = dy;}
		if(abs(this.velocity.z) < 1){
			this.velocity.z = dx;}


	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

}
