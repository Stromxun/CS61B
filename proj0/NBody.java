public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();in.readDouble();
        Planet[] planets = new Planet[N];
        for(int i = 0; i < N; i++) {
            double xxPos = in.readDouble(); double yyPos = in.readDouble();
            double xxVel = in.readDouble(); double yyVel = in.readDouble();
            double mass = in.readDouble(); String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double t = 0;

        while (t < T) {

            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            // calculate all force of planets
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // update these planets
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planet.xxPos, planet.yyPos, planet.xxVel,
                planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}
