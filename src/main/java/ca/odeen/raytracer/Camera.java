package ca.odeen.raytracer;

/**
 *
 * @author james
 */
public class Camera {

    private Vec3 lowerLeftCorner;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 origin;

    public Camera() {
        lowerLeftCorner = new Vec3(-2f, -1f, -1f);
        horizontal = new Vec3(4f, 0f, 0f);
        vertical = new Vec3(0f, 2f, 0f);
        origin = new Vec3(0f, 0f, 0f);
    }

    Ray getRay(float u, float v) {
        return new Ray(origin,
                lowerLeftCorner.add(horizontal.mult(u)).add(vertical.mult(v)));
    }

}
