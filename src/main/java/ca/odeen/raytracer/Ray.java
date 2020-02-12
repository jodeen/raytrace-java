package ca.odeen.raytracer;

/**
 *
 * @author james
 */
public class Ray {
    private Vec3 a;
    private Vec3 b;

    public Ray() {
        
    }
    
    public Ray(Vec3 a, Vec3 b) {
        this.a = a;
        this.b = b;
    }
    
    public Vec3 origin() {
        return a;
    }
    public Vec3 direction() {
        return b;
    }
    public Vec3 pointAtParameter(float t) {
        return a.add(b.mult(t));
    }
    
}
