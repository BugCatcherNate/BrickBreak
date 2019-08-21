package engineTester;

import entities.Camera;
import entities.Light;
import models.RawModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.TexturedModel;
import org.joml.Vector3f;
import player.Movement;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;

import entities.Entity;
import terrain.Terrain;
import textures.ModelTexture;

import static java.lang.Math.abs;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MainGameLoop {

	public static void main(String[] args) {

		glfwInit();
		long window = createWindow();

		Loader loader = new Loader();

		RawModel model = OBJLoader.loadObjModel("fern", loader);

		ModelTexture texture = new ModelTexture(loader.loadTexture("fern"));
		TexturedModel texturedModel = new TexturedModel(model,texture);

		texture.setShineDamper(10);
		texture.setReflectivity(1);
		//Entity entity = new Entity(model, texturedModel, new Vector3f(0f,0,-15),0,0,0,1);
		Light light = new Light(new Vector3f(2000,2000,2000), new Vector3f(1,1,1));

		Terrain terrain = new Terrain(0,-1,loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1,-1,loader, new ModelTexture(loader.loadTexture("grass")));

		Camera camera = new Camera();
		List<Entity> allCubes = new ArrayList<Entity>();

		Random random = new Random();

		for(int i=0; i < 200; i++){
			float x = random.nextFloat() * 100 - 50;
			float y = -0.1f;
			float z = random.nextFloat() * 100 - 50;

			allCubes.add(new Entity(model, texturedModel, new Vector3f(x, y, z), 0f, 0f, 0f, 1f));
		}

		Movement controller = new Movement(0.1f, camera, window);

		float zoom = 0.1f;
		MasterRenderer renderer = new MasterRenderer();


		while(!glfwWindowShouldClose(window)){
			glfwPollEvents();
			controller.movement();
			//entity.increaseRotation(0f, 0.005f,0);
			for (Entity cube: allCubes){

				renderer.processEntity(cube);
			}



			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
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
