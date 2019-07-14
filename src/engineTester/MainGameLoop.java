package engineTester;

import models.RawModel;
import java.lang.Math.*;

import models.TexturedModel;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;
import renderEngine.Loader;
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
		StaticShader shader = new StaticShader();

		Renderer renderer = new Renderer(shader);



		RawModel model = OBJLoader.loadObjModel("stall", loader);

		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TexturedModel texturedModel = new TexturedModel(model,texture);

		Entity entity = new Entity(model, texturedModel, new Vector3f(0f,0,-5),0,0,0,1);


		float zoom = 0.001f;
		while(!glfwWindowShouldClose(window)){
			glfwPollEvents();


			entity.increaseRotation(0f, 0.0005f,0);
			if(entity.getPosition().z < -150 | entity.getPosition().z > -1 ) {
				zoom *= -1;
			}
			entity.increasePosition(0,0,zoom);
			renderer.prepare();
			shader.start();
			renderer.render(entity,shader);
			shader.stop();
			glfwSwapBuffers(window);
		}

		shader.cleanUp();
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
