package com.company.utils;

import java.awt.geom.Point2D;

public class Model {

    public static double getStationaryX(double gamma) {
        return 0.46 - gamma/2 + Math.sqrt(gamma*gamma/4 - 0.47 * gamma + 0.2916);
    }

    public static double getStationaryY(double x) {
        return x + 0.01;
    }

    public static double getModelX(RungeKuttaMethod.Params params, double x, double y) {
        return x * (1 - x) - params.gamma * x * y / (x + params.b);
    }

    public static double getModelY(RungeKuttaMethod.Params params, double x, double y) {
        return params.delta * y * (1 - y / (x + params.c));

    }

    public static Point2D.Double getModelPoint(double gamma) {
        double x = getStationaryX(gamma);
        double y = getStationaryY(x);
        return new Point2D.Double(x, y);
    }
}
