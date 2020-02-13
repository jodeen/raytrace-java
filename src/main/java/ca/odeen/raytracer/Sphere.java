package ca.odeen.raytracer;

/**
 *
 * @author james
 */
public class Sphere extends Hittable {

    private Vec3 center;
    private float radius;

    public Sphere(Vec3 center, float r) {
        this.center = center;
        this.radius = r;
    }

    @Override
    public HitRecord hit(Ray ray, float tMin, float tMax) {
        Vec3 oc = ray.origin().sub(center);
        float a = ray.direction().dot(ray.direction());
        float b = oc.dot(ray.direction());
        float c = oc.dot(oc) - radius * radius;
        float discriminant = b * b - a * c;
        if (discriminant > 0) {
            float temp = (-b - (float) Math.sqrt(discriminant)) / a;
            if (temp < tMax && temp > tMin) {
                Vec3 p = ray.pointAtParameter(temp);
                return new HitRecord(temp, p, p.sub(center).div(radius));
            }
            temp = (-b + (float) Math.sqrt(discriminant)) / a;
            if (temp < tMax && temp > tMax) {
                Vec3 p = ray.pointAtParameter(temp);
                return new HitRecord(temp, p, p.sub(center).div(radius));
            }            
        }
        return null;
    }

}
