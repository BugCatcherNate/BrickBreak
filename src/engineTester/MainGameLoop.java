package engineTester;

import models.RawModel;
import java.lang.Math.*;

import models.TexturedModel;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;
import renderEngine.Loader;
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

		float[] vertices = {
				//-0.5f, 0.5f, 0f,
				//-0.5f,-0.5f, 0f,
				//0.5f,-0.5f, 0f,
				//0.5f, 0.5f, 0f
				0.5f,0.5f,-0.5f,
				-0.5f,0.5f,-0.5f,
				-0.5f,0.5f,0.5f,
				0.5f,0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				-0.5f,0.5f,0.5f,
				-0.5f,-0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,-0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				-0.5f,-0.5f,0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,-0.5f,-0.5f

		};



		int[] indices = {
				//0,1,3,
				//3,1,2
				0,  1,  2,  0,  2,  3,   //front
				4,  5,  6,  4,  6,  7,   //right
				8,  9,  10, 8,  10, 11,  //back
				12, 13, 14, 12, 14, 15,  //left
				16, 17, 18, 16, 18, 19,  //upper
				20, 21, 22, 20, 22, 23


		};

		float[] textureCoords = {

				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

		};



		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);

		ModelTexture texture = new ModelTexture(loader.loadTexture("crate"));
		TexturedModel texturedModel = new TexturedModel(model,texture);

		Entity entity = new Entity(model, texturedModel, new Vector3f(0f,0,-5),0,0,0,1);


		float zoom = 0.0002f;
		while(!glfwWindowShouldClose(window)){
			glfwPollEvents();


			entity.increaseRotation(0.0002f, 0.0002f,0);
			if(entity.getPosition().z < -15 | entity.getPosition().z > -1 ) {
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
