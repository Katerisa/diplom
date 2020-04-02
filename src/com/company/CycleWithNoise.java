package com.company;

import com.company.utils.Model;
import com.company.utils.Reader;
import com.company.utils.Writer;
import com.company.utils.RungeKuttaMethod;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CycleWithNoise {
    public static void main(String[] args) {
        double gamma = 1;
        ArrayList<Point2D.Double> result = new ArrayList<>();
        ArrayList<Point2D.Double> cycle = Reader.reader("cycle.txt");
        Point2D.Double current = cycle.get(0);
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(0.001, 0.2, gamma, 0.08, 0.01);
        for (int j = 0; j < 500000; j++) {
            result.add(current);
            current = RungeKuttaMethod.getNewPointWithNoise(current, params);
        }
        Writer.write(result, "out.txt");
    }
}
