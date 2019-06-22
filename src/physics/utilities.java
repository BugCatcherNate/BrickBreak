package physics;
import entities.Entity;

public final class utilities {



    private utilities(){};

    public static boolean collisionDetection(Entity objectA, Entity objectB){

        float[][] objectACords;
        float[][] objectBCords;
        boolean collided;
        objectACords = new float[2][2];

        objectACords[0] = objectA.getMin(objectA.getModel().getWidth(), objectA.getModel().getHeight());
        objectACords[1] = objectA.getMax(objectA.getModel().getWidth(), objectA.getModel().getHeight());

        objectBCords = new float[2][2];
        objectBCords[0] = objectB.getMin(objectA.getModel().getWidth(), objectA.getModel().getHeight());
        objectBCords[1] = objectB.getMax(objectA.getModel().getWidth(), objectA.getModel().getHeight());

        float d1x = objectBCords[0][0] - objectACords[1][0];
        float d1y = objectBCords[0][1] - objectACords[1][1];
        float d2x = objectACords[0][0] - objectBCords[1][0];
        float d2y = objectACords[0][1] - objectBCords[1][1];

        if ( d1x > 0 || d1y > 0){
            collided = false;
        }
        else if (d2x > 0 || d2y > 0){
            collided = false;
        }
        else{
            collided = true;
        }

    return collided;
    }

}
