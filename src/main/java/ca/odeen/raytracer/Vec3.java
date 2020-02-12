package ca.odeen.raytracer;

/**
 *
 * @author james
 */
public class Vec3 {

    private float[] e;

    Vec3(float e0, float e1, float e2) {
        e = new float[]{e0, e1, e2};
    }
    
    
    public float x() {
        return e[0];
    }
        
    public float y() {
        return e[1];
    }
    
    public float z() {
        return e[2];
    }

    public float a() {
        return e[0];
    }

    public float b() {
        return e[1];
    }

    public float c() {
        return e[2];
    }

    public Vec3 add(Vec3 v1) {
        return new Vec3(this.a() + v1.a(), this.b() + v1.b(), this.c() + v1.c());
    }
    
    public Vec3 sub(Vec3 v1) {
        return new Vec3(this.a() - v1.a(), this.b() - v1.b(), this.c() - v1.c());
    }

    public Vec3 mult(float f) {
        return new Vec3(f * this.a(), f * this.b(), f * this.c());
    }
    
    public Vec3 div(float t) {
        return new Vec3(e[0]/t, e[1]/t, e[2]/t);
    }
    
    public float length() {
        return (float) Math.sqrt(e[0] * e[0] + e[1] * e[1] + e[2] * e[2]);
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.div(v.length());
    }
    
    public float dot(Vec3 v1) {
        return a() * v1.a() + b() * v1.b() + c() * v1.c();
    }
}
