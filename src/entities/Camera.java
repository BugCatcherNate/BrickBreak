package entities;

import org.joml.Vector3f;

import java.awt.*;
import java.security.Key;

public class Camera {

    private Vector3f position = new Vector3f(0,0,0);

    private float pitch;
    private float yaw;
    private float roll;


    public Camera () {};

    public Vector3f getPosition() {
        return position;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
