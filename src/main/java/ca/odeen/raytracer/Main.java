package ca.odeen.raytracer;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author james
 */
public class Main {

    public static boolean hitSphere(Vec3 center, float radius, Ray r) {
        Vec3 oc = r.origin().sub(center);
        float a = r.direction().dot(r.direction());
        float b = 2f * oc.dot(r.direction());
        float c = oc.dot(oc) - radius * radius;
        float discriminant = b * b - 4 * a * c;
        return discriminant > 0;
    }

    public static Vec3 color(Ray r) {
        if (hitSphere(new Vec3(0f, 0f, -1f), 0.5f, r)) {
            return new Vec3(1f, 0f, 0f);
        }
        Vec3 unitDirection = Vec3.unitVector(r.direction());
        float t = 0.5f * (unitDirection.y() + 1.0f);
        return (new Vec3(1f, 1f, 1f)).mult(1f - t).add(new Vec3(0.5f, 0.7f, 1f).mult(t));
    }

    public static void main(String[] args) throws Exception {
        int nx = 200;
        int ny = 100;
        try (BufferedWriter os = new BufferedWriter(new FileWriter("img.ppm"))) {
            os.write("P3\n" + nx + " " + ny + "\n255\n");

            Vec3 lowerLeftCorner = new Vec3(-2.0f, -1.0f, -1.0f);
            Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
            Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
            Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);

            for (int j = ny - 1; j >= 0; j--) {
                for (int i = 0; i < nx; i++) {

                    float u = (float) i / (float) nx;
                    float v = (float) j / (float) ny;

                    Ray r = new Ray(origin,
                            lowerLeftCorner.add(horizontal.mult(u)).add(vertical.mult(v)));

                    Vec3 col = color(r);

                    int ir = (int) (255.99 * col.a());
                    int ig = (int) (255.99 * col.b());
                    int ib = (int) (255.99 * col.c());
                    os.write(ir + " " + ig + " " + ib + "\n");
                }
            }
        }
    }
}
