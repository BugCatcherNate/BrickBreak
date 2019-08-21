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
	private int shift;

	private double oldx;
	private double oldy;

	private double deltax;
	private double deltay;

	DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
	DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);

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
		shift = glfwGetKey(window, GLFW_KEY_LEFT_SHIFT);
		if (shift == GLFW_PRESS) {
			camera.moveUp(-speed);
		}


		glfwGetCursorPos(window, b1, b2);

		deltax = oldx - b1.get(0);
		deltay = oldy - b2.get(0);

		oldx = b1.get(0);
		oldy = b2.get(0);

		camera.rotateY(-.1*  deltay);
		camera.rotateX(-.1* deltax);

	}


}
