public class Planet {
        private static final double G = 6.67e-11;
        public double xxPos; // It's current x position
        public double yyPos; // It's current y position
        public double xxVel; // It's current velocity in the x direction
        public double yyVel; // It's current velocity in the y direction
        public double mass; // It's mass

        // The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif)
        public String imgFileName;

        public Planet(double xP, double yP, double xV,
                      double yV, double m, String img){
            xxPos = xP; yyPos = yP;
            xxVel = xV; yyVel = yV;
            mass = m; imgFileName = img;
        }

        public Planet(Planet p){
            xxPos = p.xxPos; yyPos = p.yyPos;
            xxVel = p.xxVel; yyVel = p.yyVel;
            mass = p.mass; imgFileName = p.imgFileName;
        }

	public double calcDistance(Planet p) {
		return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + 
				 Math.pow(this.yyPos - p.yyPos, 2));
	}

    public double calcForceExertedBy(Planet p) {
            double r = calcDistance(p);
            return G * this.mass * p.mass / (Math.pow(r, 2));
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = calcDistance(p);
        return F * (p.xxPos - this.xxPos) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = calcDistance(p);
        return F * (p.yyPos - this.yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double NetForce = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) continue;
            NetForce += this.calcForceExertedByX(planet);
        }
        return NetForce;
    }
    public double calcNetForceExertedByY(Planet[] planets) {
        double NetForce = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) continue;
            NetForce += this.calcForceExertedByY(planet);
        }
        return NetForce;
    }

    public void update(double dt, double fx, double fy) {
        double aX = fx / mass;
        double aY = fy / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
            StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
