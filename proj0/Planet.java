import javax.xml.xpath.XPath;

public class Planet {
    /** 
     *  The main source file for project 0.
     */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        /** 
         * A constructor which include the parmer for Plant
         */
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;
    }

    public Planet(Planet b){
        /** 
         * A constructor which include the first constructor.
         */
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName); // "this" represent this object that can be called as a non-static method.
    }

    public double calcDistance(Planet p ){
        /**
         * A method that calculates the distance between two Planets.
         */

        double dis;
        dis =Math.sqrt((p.yyPos - this.yyPos) * (p.yyPos - this.yyPos) + (p.xxPos - this.xxPos) * (p.xxPos - this.xxPos));
        return dis;
    }
    
    public double calcForceExertedBy(Planet p){
        /** 
         * A method that  calculates the force between two Planets.
         */

        double force;
        
        force = (6.67e-11 * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
        return force;
    }

    public double calcForceExertedByX(Planet p){
        /** 
         * A method that calculates the Xforce between two Planets.
         */
        double forceX;
        forceX = calcForceExertedBy(p) * ((p.xxPos - this.xxPos) / calcDistance(p));
        return forceX;
    }

    public double calcForceExertedByY(Planet p){
        /** 
         * A method that calculates the Yforce between two Planets.
         */
        double forceY;
        forceY = calcForceExertedBy(p) * ((p.yyPos - this.yyPos) / calcDistance(p));
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] p){
        /**
         * A method that calculates the NetforceX among the array of Planets.
         */
        double netforceX = 0; 
        for(int i = 0; i < p.length; i++){
            if(p[i] == this)
            continue;
            else
            netforceX += this.calcForceExertedByX(p[i]);
        }
        return netforceX;
    }

    public double calcNetForceExertedByY(Planet[] p){
        /**
         * A method that calculates the NetforceY among the array of Planets.
         */
        double netforceY = 0; 
        for(int i = 0; i < p.length; i++){
            if(p[i] == this)
            continue;  // pass it when the planet calculate with itself. 
            else
            netforceY += this.calcForceExertedByY(p[i]);
           }
        return netforceY;
    }

    public void update(double dt, double fX, double fY){
        /**
         * A method that determines how much the forces exerted on the planet will cause that 
         * planet to accelerate, and the resulting change in the planet's velocity and position in 
         * a small period of time dt.
         */
        double aX;
        double aY;
        aX = fX / this.mass;
        aY = fY / this.mass;
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }
    /**
     * uses the StdDraw API mentioned above to draw the Body's image at the BOdy's position.
     */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+ this.imgFileName);
    }

    }

    
