package ca.odeen.raytracer;

/**
 *
 * @author james
 */
public abstract class Hittable {

    public abstract HitRecord hit(Ray ray, float tMin, float tMax);

}
