package ca.odeen.raytracer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author james
 */
public class HittableList extends Hittable {
    private List<Hittable> hittables= new ArrayList<>();

    public HittableList(Hittable... hittables) {
        this.hittables.addAll(Arrays.asList(hittables));
    }

    public HitRecord hit(Ray ray, float tMin, float tMax) {
        HitRecord currentRec = null;
        float closestSoFar = tMax;
        for (Hittable hittable : hittables) {
            HitRecord hitRecord = hittable.hit(ray, tMin, closestSoFar);
            if (hitRecord != null) {
                closestSoFar = hitRecord.t();
                currentRec = hitRecord;
            }
        }
        return currentRec;
    }
}
