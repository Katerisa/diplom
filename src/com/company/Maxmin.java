package com.company;

import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Maxmin {
    public static void main(String[] args) {
        double n = 200.0;
        Point2D.Double current = new Point2D.Double(2.2,0.5);
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(0.01, 0.2, 0.0, 0.08, 0.01);
        ArrayList<Point2D.Double> result = new ArrayList<>();
        for(int k = 1; k < n+1; k++) {
            double max = 1000;
            params.setGamma((k-1) * (5/n));
            for (int i = 0; i < 100000; i++) {
                Point2D.Double newPoint = RungeKuttaMethod.getNewPoint(current, params);
                current = new Point2D.Double(newPoint.x, newPoint.y);
            }
            for (int j = 0; j < 100000; j++) {
                Point2D.Double newPoint = RungeKuttaMethod.getNewPoint(current, params);
                current = new Point2D.Double(newPoint.x, newPoint.y);
                if (current.y < max)
                    max = current.y;
            }
            result.add(new Point2D.Double(params.getGamma(), max));
        }
        Writer.write(result, "out.txt");
    }
}
