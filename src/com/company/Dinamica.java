package com.company;

import com.company.utils.Model;
import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Dinamica {
    public static void main(String[] args) {
        double gamma = 3.40;
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(0.01, 0.2, gamma, 0.08, 0.01);
        Point2D.Double current = Model.getModelPoint(gamma);
        ArrayList<Point2D.Double> result = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            if (i % 1000 == 0) {
                result.add(current);
            }
                current = RungeKuttaMethod.getNewPointWithNoise(current, params);

        }
        Writer.write(result, "out.txt");
    }
}
