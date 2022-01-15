public class NBody {
    /**
     * The class that will actually run your simulation.
     * No construction
     * @param args
     * @return
     */

    public static void main(String[] args){
        /** 
         * The main method that launch the whole project.
         */
        if(args.length < 1){
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java NBody <T> <dt> <filename>");
        }
        /**
         * Read the commands line input and convert them to the param.
         * @param T, dt, filename 
         *           T: times of the drawing  process.
         *           dt: intevel of each calculating.
         *           filename: the location of the img file.
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        In in = new In(filename);
        int number = in.readInt();

        /**
         * Draw amd simulate the Planets 
         */
        String starfield = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        int time = 0;
        for(time = 0; time <= T; time++){
            /**
             * calculate and upgade the param of all planets.
             */
            double xForce[] = new double[number]; 
            double yForce[] = new double[number]; 
            for(int i = 0; i < number; i++){
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < number; i++){
                planets[i].update(dt, xForce[i], yForce[i]);
            }

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, starfield);  // set the background.
        for(int i = 0; i < planets.length; i++){
            /**
             * draw all of the planets .
             */
            planets[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(10); //set last time.
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }


    }

    public static double readRadius(String args){
        /**
         * A method that return a double corresponding to the radius of the universe in that file.
         * @param args that the location of the txt file.
         * @return the planet's radius which recode in the txt file.
         * 
         * Attention: We call the readRadius method, so we don't need the non-static nethod.
         */
        In in = new In(args);   // call in.java source file to find the param in the locate file.
        int number = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String args){
        /**
         * A method that return an array of Planets corresponding to the planets in the files.
         * @param args that the location of the txt file.
         * @return the planets whose param recoded in the txt file.
         * 
         */
        In in = new In(args);
        int number = in.readInt();
        double Radius = in.readDouble();
        Planet[] planets = new Planet[number] ;
            for(int i = 0; i < number; i++){
                double xxPos = in.readDouble();
                double yyPos = in.readDouble();
                double xxVel = in.readDouble();
                double yyVel = in.readDouble();
                double mass  = in.readDouble();    
                String imgFileName = in.readString();
                planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName); // Given the param for Planet
            }
        return planets;
    }
    
}
