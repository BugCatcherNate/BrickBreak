package engineTester;

import entities.Camera;
import entities.Light;
import models.RawModel;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.TexturedModel;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import physics.utilities;

import entities.Entity;
import textures.ModelTexture;

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

		RawModel model = OBJLoader.loadObjModel("crate", loader);

		ModelTexture texture = new ModelTexture(loader.loadTexture("brown"));
		TexturedModel texturedModel = new TexturedModel(model,texture);

		//Entity entity = new Entity(model, texturedModel, new Vector3f(0f,0,-15),0,0,0,1);
		Light light = new Light(new Vector3f(10,0,-10), new Vector3f(1,1,1));

		Camera camera = new Camera();
		List<Entity> allCubes = new ArrayList<Entity>();

		Random random = new Random();

		for(int i=0; i < 200; i++){
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;

			allCubes.add(new Entity(model, texturedModel, new Vector3f(x, y, z), random.nextFloat() * 180f, random.nextFloat()* 180f, 0f, 1f));
		}

		float movespeed = 0.1f;

		int wstate, astate, dstate, sstate;

		float zoom = 0.1f;
		MasterRenderer renderer = new MasterRenderer();


		while(!glfwWindowShouldClose(window)){
			glfwPollEvents();
			wstate = glfwGetKey(window, GLFW_KEY_W);
			if (wstate == GLFW_PRESS){
				camera.moveForward(-movespeed);
			}
			astate = glfwGetKey(window, GLFW_KEY_A);
			if (astate == GLFW_PRESS){
				camera.moveHoriz(-movespeed);
			}
			dstate = glfwGetKey(window, GLFW_KEY_D);
			if (dstate == GLFW_PRESS){
				camera.moveHoriz(movespeed);
			}
			sstate = glfwGetKey(window, GLFW_KEY_S);
					if (sstate == GLFW_PRESS){
				camera.moveForward(movespeed);
			}

			//entity.increaseRotation(0f, 0.005f,0);
			for (Entity cube: allCubes){

				renderer.processEntity(cube);
			}
			renderer.render(light,camera);
			glfwSwapBuffers(window);
		}

		renderer.cleanUp();
		loader.cleanUp();
		glfwTerminate();

	}


	private static long createWindow() {
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		long window = glfwCreateWindow(800, 600, "EngineN", NULL, NULL);
		glfwMakeContextCurrent(window);
		createCapabilities();
		return window;
	}

}
