package com.company;

import com.company.utils.Model;
import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LimitCycle {
    public static void main(String[] args) {
        double step = 0.001;
        double intersectEpsilon = 0.01;
        double gamma = 2;
        RungeKuttaMethod.Params params = new RungeKuttaMethod().new Params(step, 0.2, gamma, 0.08, 0.01);
        Writer.write(plot(step, intersectEpsilon, gamma, params), "cycle.txt");
    }

    static ArrayList<Point2D.Double> plot(double step, double intersectEpsilon, double gamma, RungeKuttaMethod.Params params) {
        Point2D.Double current = new Point2D.Double(1, 1);
        ArrayList<Point2D.Double> result = new ArrayList<>();
        double x0 = Model.getStationaryX(gamma);
        double y0 = Model.getStationaryY(x0);
        double curIntersect = 7;
        double newIntersect;
        for (int i = 0; i < 1000000; i++) {
            Point2D.Double newPoint = RungeKuttaMethod.getNewPoint(current, params);
            if ((current.y - y0) * (newPoint.y - y0) < 0 && (current.x > x0) && (newPoint.x > x0)) {
                newIntersect = (-(newPoint.x - current.x) * y0 - (current.x * newPoint.y - newPoint.x * current.y)) / (current.y - newPoint.y);
                if (Math.abs(newIntersect - curIntersect) < intersectEpsilon) {
                    current.x = newIntersect;
                    current.y = y0;
                    newPoint = RungeKuttaMethod.getNewPoint(current, params);
                    while (!((current.y - y0) * (newPoint.y - y0) < 0 &&
                            (current.x > x0) && (newPoint.x > x0))) {
                        result.add(newPoint);
                        current = new Point2D.Double(newPoint.x, newPoint.y);
                        newPoint = RungeKuttaMethod.getNewPoint(current, params);
                    }
                    break;
                }
                curIntersect = newIntersect;
            }
            current = new Point2D.Double(newPoint.x, newPoint.y);
        }
        return result;
    }
}

