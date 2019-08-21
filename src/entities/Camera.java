package entities;

import org.joml.Vector3f;

import java.awt.*;
import java.security.Key;

public class Camera {

    private Vector3f position = new Vector3f(0,1,0);

    private float pitch;
    private float yaw;
    private float roll;


    public Camera () {};
    public void moveHoriz(float amount) {

    position.x += amount;

    };

    public void moveForward(float amount) {

    position.z += amount;

    };
    public void moveUp(float amount) {

        position.y += amount;

    };

    public Vector3f getPosition() {
        return position;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch(){

        return pitch;
    }

    public float getRoll() {
        return roll;
    }
}
