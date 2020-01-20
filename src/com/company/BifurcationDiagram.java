package com.company;

import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BifurcationDiagram {

    public static void main(String[] args) {
        double n = 100.0;
        double delta;
        double a;
        double x;
        double b = 0.08;
        double c = 0.01;

        ArrayList<Point2D.Double> result = new ArrayList<>();
	    for (int i = 1; i < n+1; i++) {
	        a = (i-1) * (8/n);
	        //the same as Model.getX but a changes
	        x = (-b-a+1+Math.sqrt(b*b+2*a*b-4*a*c-2*a+2*b+a*a+1))/2;
	        delta = 1 - 2*x - (a*b*(x+c)/((x+b)*(x+b)));
            //delta = 0.01;
            Point2D.Double current = new Point2D.Double(a, delta);
            result.add(current);
        }
        Writer.write(result, "out.txt");
    }
}
