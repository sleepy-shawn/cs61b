public class Planet{
    // public static constant
    public static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
 // planet property constructor
    public Planet(double xP, double yP, double xV,
        double yV, double m, String img){
        xxPos = xP; yyPos = yP; xxVel = xV;
        yyVel = yV; mass = m; imgFileName = img;
        }
 // make a copyplanet
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
 // calculate distance method
    public double calcDistance(Planet b){
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
        }
 // calc Force exerted by
    public double calcForceExertedBy(Planet b){
        double distance = this.calcDistance(b);
        double F = this.mass * b.mass * Planet.G / (distance * distance);
        return F;
    }
 // calc Force exerted by x and y
    public double calcForceExertedByX(Planet b){
        double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;
    }
    public double calcForceExertedByY(Planet b){
        double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }
 // calc Net Force exerted by all planet except it self
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double sum = 0;
        for(int i = 0; i < allPlanets.length; i = i + 1){
            Planet b = allPlanets[i];
            if (! this.equal(b)){
                sum = sum + this.calcForceExertedByX(b);
            }
        }

    }
}