package com.company;

import java.awt.geom.Point2D;

import com.company.utils.Reader;
import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;
import java.util.ArrayList;

import static com.company.utils.Model.getModelX;
import static com.company.utils.Model.getModelY;
import static com.company.utils.myMath.pythagoras;

public class Line {
    public static void main(String[] args) {
        double gamma = 2;
        double step = 0.001;
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(step, 0.2, gamma, 0.08, 0.01);
        ArrayList<Point2D.Double> cycle = LimitCycle.plot(step,0.01, gamma, params);
        ArrayList<Point2D.Double> m = FTS.plot(step, params, cycle);
        ArrayList<Point2D.Double> result1 = new ArrayList<>();
        ArrayList<Point2D.Double> result2 = new ArrayList<>();
        double noiseEpsilon = 0.006;
        double q = 1.821;
        for (int i = 0; i < cycle.size(); i++) {
            double x = cycle.get(i).x;
            double y = cycle.get(i).y;
            double f = getModelX(params, x, y);
            double g = getModelY(params, x, y);
            double divisor = pythagoras(f, g);
            double multiplier = q * noiseEpsilon * Math.sqrt(2 * m.get(i).y);
            double shiftX = multiplier * (-g / divisor);
            double shiftY = multiplier * f / divisor;
            result1.add(new Point2D.Double(x + shiftX, y + shiftY));
            result2.add(new Point2D.Double(x - shiftX, y - shiftY));
        }
        Writer.write(result1, "out1.txt");
        Writer.write(result2, "out2.txt");

        Writer.write(CycleWithNoise.plot(params, cycle, noiseEpsilon), "out.txt");
    }
}
