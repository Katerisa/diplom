package com.company.utils;

import java.awt.geom.Point2D;

public class Model {

    public static double getX(double gamma) {
        return 0.46 - gamma/2 + Math.sqrt(gamma*gamma/4 - 0.47 * gamma + 0.2916);
    }

    public static double getY(double x) {
        return x + 0.01;
    }

    public static Point2D.Double getModelPoint(double gamma) {
        double x = getX(gamma);
        double y = getY(x);
        return new Point2D.Double(x, y);
    }
}
