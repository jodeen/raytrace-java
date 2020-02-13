package ca.odeen.raytracer;

/**
 *
 * @author james
 */
public class HitRecord {
    
    private float t;
    private Vec3 p;
    private Vec3 normal;

    public HitRecord(float t, Vec3 p, Vec3 normal) {
        this.t = t;
        this.p = p;
        this.normal = normal;
    }

    public float t() {
        return t;
    }

    public Vec3 p() {
        return p;
    }

    public Vec3 normal() {
        return normal;
    }
    
}
