package ca.odeen.raytracer;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author james
 */
public class Main {

    public static Vec3 color(Ray r, Hittable world) {
        HitRecord rec = world.hit(r, 0f, Float.MAX_VALUE);
        if (rec != null) {
            return new Vec3(rec.normal().x() + 1f, rec.normal().y() + 1f, rec.normal().z() + 1f).mult(0.5f);
        } else {
            Vec3 unitDirection = r.direction().unitVector();
            float t = 0.5f * (unitDirection.y() + 1f);
            return new Vec3(1f, 1f, 1f).mult(1f - t).add(new Vec3(0.5f, 0.7f, 1f).mult(t));
        }
    }

    public static void main(String[] args) throws Exception {
        int nx = 200;
        int ny = 100;
        try (BufferedWriter os = new BufferedWriter(new FileWriter("img.ppm"))) {
            os.write("P3\n" + nx + " " + ny + "\n255\n");

            Vec3 lowerLeftCorner = new Vec3(-2f, -1f, -1f);
            Vec3 horizontal = new Vec3(4f, 0f, 0f);
            Vec3 vertical = new Vec3(0f, 2f, 0f);
            Vec3 origin = new Vec3(0f, 0f, 0f);
            Hittable world = new HittableList(
                    new Sphere(new Vec3(0f, 0f, -1f), 0.5f),
                    new Sphere(new Vec3(0f, -100.5f, -1f), 100f));
//            world = new Sphere(new Vec3(0f, 0f, -1f), 0.5f);

            for (int j = ny - 1; j >= 0; j--) {
                for (int i = 0; i < nx; i++) {

                    float u = (float) i / (float) nx;
                    float v = (float) j / (float) ny;

                    Ray r = new Ray(origin,
                            lowerLeftCorner.add(horizontal.mult(u)).add(vertical.mult(v)));

                    Vec3 p = r.pointAtParameter(2f);

                    Vec3 col = color(r, world);

                    int ir = (int) (255.99 * col.a());
                    int ig = (int) (255.99 * col.b());
                    int ib = (int) (255.99 * col.c());
                    os.write(ir + " " + ig + " " + ib + "\n");
                }
            }
        }
    }
}
