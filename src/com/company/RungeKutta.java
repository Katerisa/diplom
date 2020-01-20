package com.company;

import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class RungeKutta {
    public static void main(String[] args) {
        Point2D.Double current = new Point2D.Double(0.1, 0.03);
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(0.01, 0.2, 4, 0.08, 0.01);
        ArrayList<Point2D.Double> result = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            result.add(current);
            current = RungeKuttaMethod.getNewPoint(current, params);
        }
        Writer.write(result, "out.txt");
    }
}
