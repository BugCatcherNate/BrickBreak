package toolbox;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry,
													  float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(translation);
		return matrix;
	}

	public static float lerp(float a, float b, float f)
	{
		return a + f * (b - a);
	}


}
