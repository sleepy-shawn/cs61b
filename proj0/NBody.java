/** run my simulation without constructor 
 * specified in one of the data files
 * @author Ge Shuai
*/
public class NBody{
 // read the radius of universe and planets
    public static double readRadius(String file){
        In in = new In(file); // make a instant of the file
        int total = in.readInt();
        double radius = in.readDouble(); // the aim of readradius
        return radius;
    }

 // create an array of planets
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int total = in.readInt();
        Planet[] b = new Planet[total];
        double radius = in.readDouble();
        for (int i = 0; i < total; i = i + 1){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String im = in.readString();
            b[i] = new Planet(xp, yp, xv, yv, m, im);
            
        }
        return b;
    }
 // simulate and draw the whole picture
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
        // draw the planet at its position using the stdDraw api
        for (Planet a : planets){
            a.draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t <= T; t = t + dt){
            double [] xForces = new double[planets.length];
            double [] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i = i + 1){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i = i + 1){
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}

