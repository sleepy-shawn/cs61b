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
            String im = "images/" + in.readString();
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

    }
}

