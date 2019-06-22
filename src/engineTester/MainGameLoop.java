package engineTester;

import models.RawModel;
import java.lang.Math.*;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import physics.utilities;

import entities.Entity;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MainGameLoop {

	public static void main(String[] args) {

		glfwInit();
		long window = createWindow();

		Loader loader = new Loader();
		StaticShader shader = new StaticShader();

		Renderer renderer = new Renderer(shader);

		float[] vertices = {
				-0.10f, 0.4f, 0f,
				-0.10f,-0.4f, 0f,
				0.10f,-0.4f, 0f,
				0.10f, 0.4f, 0f

		};



		int[] indices = {
				0,1,3,
				3,1,2,


		};


		float[] pongVertices  = {
				-0.05f, 0.05f, 0f,
				-0.05f,-0.05f, 0f,
				0.05f,-0.05f, 0f,
				0.05f, 0.05f, 0f

		};



		int[] pongIndices = {
				0,1,3,
				3,1,2,


		};

		RawModel pongModel = loader.loadToVAO(pongVertices,pongIndices);
		RawModel model = loader.loadToVAO(vertices ,indices);



		Entity entity = new Entity(model, new Vector3f(-1f,0,0),0,0,0,1);
		Entity pong = new Entity(pongModel, new Vector3f(0,0,0),0,0,0,1);


		float ballDirectionx = -.01f;
		float ballDirectiony = 0f;

		System.out.println(entity.getPosition().toString());
		pong.accelerate(-.04f,0,0);


		while(!glfwWindowShouldClose(window)){
			glfwPollEvents();


			if(pong.getPosition().x > 1){
			pong.accelerate(-.04f, pong.getVelocity().y, 0);
							}
			else if(pong.getPosition().x < -1){
				pong.accelerate(.04f, pong.getVelocity().y, 0);
			}
			if(pong.getPosition().y > 1){
				pong.accelerate(pong.getVelocity().x, -.05f, 0);

			}else if(pong.getPosition().y < -1){
				pong.accelerate(pong.getVelocity().x, .05f,0);
			}
			entity.normalizeAcceleration(.2f);
			pong.normalizeAcceleration(0);
			int upstate = glfwGetKey(window, GLFW_KEY_A);
			if (upstate == GLFW_PRESS) {
				entity.accelerate(0,0.05f,0);
			}
			int downstate = glfwGetKey(window, GLFW_KEY_D);
			if (downstate == GLFW_PRESS) {
				entity.accelerate(0, -0.05f, 0);
			}

			if(utilities.collisionDetection(entity,pong)){
				System.out.println("Collided");
				pong.accelerate(0.05f, entity.getVelocity().y, 0);


			}

			renderer.prepare();
			shader.start();
			renderer.render(entity,shader);
			renderer.render(pong, shader);
			shader.stop();
			glfwSwapBuffers(window);
		}

		shader.cleanUp();
		loader.cleanUp();
		glfwTerminate();

	}


	private static long createWindow() {
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		long window = glfwCreateWindow(800, 600, "Pong", NULL, NULL);
		glfwMakeContextCurrent(window);
		createCapabilities();
		return window;
	}

}
