package ca.odeen.raytracer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author james
 */
public class Main {

        private static Random rand = new Random();
        
    public static Vec3 randomInUnitSphere() {
        Vec3 p;
        do {
            p = new Vec3(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()).mult(2f).sub(new Vec3(1f,1f,1f));
        } while (p.squaredLength() >= 1f);
        return p;
    }
    
    public static Vec3 color(Ray r, Hittable world) {
        HitRecord rec = world.hit(r, 0.001f, Float.MAX_VALUE);
        if (rec != null) {
            Vec3 target = rec.p().add(rec.normal()).add(randomInUnitSphere());
            return color(new Ray(rec.p(), target.sub(rec.p())), world).mult(0.5f);
        } else {
            Vec3 unitDirection = r.direction().unitVector();
            float t = 0.5f * (unitDirection.y() + 1f);
            return new Vec3(1f, 1f, 1f).mult(1f - t).add(new Vec3(0.5f, 0.7f, 1f).mult(t));
        }
    }

    public static void main(String[] args) throws Exception {
        int nx = 200;
        int ny = 100;
        int ns = 100;

        try (BufferedWriter os = new BufferedWriter(new FileWriter("img.ppm"))) {
            os.write("P3\n" + nx + " " + ny + "\n255\n");

            Hittable world = new HittableList(
                    new Sphere(new Vec3(0f, 0f, -1f), 0.5f),
                    new Sphere(new Vec3(0f, -100.5f, -1f), 100f));
            Camera cam = new Camera();

            for (int j = ny - 1; j >= 0; j--) {
                for (int i = 0; i < nx; i++) {
                    Vec3 col = new Vec3(0f, 0f, 0f);
                    for (int s = 0; s < ns; s++) {

                        float u = (float) (i + rand.nextFloat()) / (float) nx;
                        float v = (float) (j + rand.nextFloat()) / (float) ny;
                        Ray r = cam.getRay(u, v);
                        col = col.add(color(r, world));
                    }
                    col = col.div(ns);
                    col = new Vec3((float) Math.sqrt(col.a()), (float) Math.sqrt(col.b()), (float) Math.sqrt(col.c()));

                    int ir = (int) (255.99 * col.a());
                    int ig = (int) (255.99 * col.b());
                    int ib = (int) (255.99 * col.c());
                    os.write(ir + " " + ig + " " + ib + "\n");
                }
            }
        }
    }
}
