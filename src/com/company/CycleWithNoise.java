package com.company;

import com.company.utils.Model;
import com.company.utils.Reader;
import com.company.utils.Writer;
import com.company.utils.RungeKuttaMethod;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CycleWithNoise {
    public static void main(String[] args) {
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(0.001, 0.2, 2, 0.08, 0.01);
        ArrayList<Point2D.Double> cycle = Reader.reader("cycle.txt");
        double noise = 0.006;
        Writer.write(plot(params, cycle, noise), "out.txt");
    }

    static ArrayList<Point2D.Double> plot(RungeKuttaMethod.Params params, ArrayList<Point2D.Double> cycle, double noise) {
        ArrayList<Point2D.Double> result = new ArrayList<>();
        Point2D.Double current = cycle.get(0);
        for (int j = 0; j < 100000; j++) {
            current = RungeKuttaMethod.getNewPointWithNoise(current, params, noise);
        }
        for (int j = 0; j < 500000; j++) {
            result.add(current);
            current = RungeKuttaMethod.getNewPointWithNoise(current, params, noise);
        }
        return result;
    }
}
