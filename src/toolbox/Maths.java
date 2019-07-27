package toolbox;

import entities.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry,
													  float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(translation);
		matrix.rotateY(ry);
		matrix.rotateX(rx);
		matrix.rotateZ(rz);

		return matrix;
	}

	public static float lerp(float a, float b, float f)
	{
		return a + f * (b - a);
	}

	public static Matrix4f createViewMatrix(Camera camera){

		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotateX(camera.getPitch());
		viewMatrix.rotateY(camera.getYaw());
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		viewMatrix.translate(negativeCameraPos);
		return viewMatrix;
	}

}
