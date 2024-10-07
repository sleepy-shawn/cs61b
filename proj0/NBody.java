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
        Planet [] b = new Planet[5];
        In in = new In(file);
        int total = in.readInt();
        double radius = in.readDouble();
        for (int i = 0; i < 5; i = i + 1){
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
        Planet[] b = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
        // draw the planet at its position using the stdDraw api
        for (Planet a : b){
            a.draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t <= T; t = t + dt){
            double [] xForces = new double[5];
            double [] yForces = new double[5];
            for (int i = 0; i < 5; i = i + 1){
                xForces[i] = b[i].calcNetForceExertedByX(b);
                yForces[i] = b[i].calcNetForceExertedByY(b);
                b[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
            for (int i = 0; i < 5; i = i + 1){
                b[i].update(dt, xForces[i], yForces[i]);
                b[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", b.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < b.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            b[i].xxPos, b[i].yyPos, b[i].xxVel,
            b[i].yyVel, b[i].mass, b[i].imgFileName);   
        }
    }
}

