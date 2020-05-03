package com.company;

import com.company.utils.Model;
import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Dinamica {
    public static void main(String[] args) {
        double gamma = 3.7;
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(0.001, 0.2, gamma, 0.08, 0.01);
        Point2D.Double current = Model.getModelPoint(gamma);
        ArrayList<Point2D.Double> result = new ArrayList<>();
        double noise = 0.006;
        for (int i = 0; i < 500000; i++) {
            current = RungeKuttaMethod.getNewPointWithNoise(current, params, noise);
            Point2D.Double current2 = new Point2D.Double(0.001*i, current.x);
            result.add(current);
        }
        Writer.write(result, "out.txt");
    }
}
