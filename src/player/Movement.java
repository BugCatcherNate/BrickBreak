package player;

import entities.Camera;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Movement {


    private float speed;
    private Camera camera;
    private long window;

    private int wstate;
    private int astate;
    private int dstate;
    private int sstate;
    private int space;
	private int capsl;

	private float oldx;
	private float oldy;

	private float deltax;
	private float deltay;

    public Movement(float speed, Camera camera, long window){
    	this.speed = speed;
    	this.camera = camera;
    	this.window = window;
	}

	public void movement() {

		wstate = glfwGetKey(window, GLFW_KEY_W);
		if (wstate == GLFW_PRESS) {
			camera.moveForward(-speed);
		}
		astate = glfwGetKey(window, GLFW_KEY_A);
		if (astate == GLFW_PRESS) {
			camera.moveHoriz(-speed);
		}
		dstate = glfwGetKey(window, GLFW_KEY_D);
		if (dstate == GLFW_PRESS) {
			camera.moveHoriz(speed);
		}
		sstate = glfwGetKey(window, GLFW_KEY_S);
		if (sstate == GLFW_PRESS) {
			camera.moveForward(speed);
		}
		space = glfwGetKey(window, GLFW_KEY_SPACE);
		if (space == GLFW_PRESS) {
			camera.moveUp(speed);
		}
		capsl = glfwGetKey(window, GLFW_KEY_CAPS_LOCK);
		if (capsl == GLFW_PRESS) {
			camera.moveUp(-speed);
		}

		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, b1, b2);
		System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));


	}


}
