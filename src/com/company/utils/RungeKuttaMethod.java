package com.company.utils;

import java.awt.geom.Point2D;

public class RungeKuttaMethod {

    public class Params {
        double step;
        double delta;
        double gamma;
        double b;
        double c;

        public Params(double step, double delta, double gamma, double b, double c) {
            this.step = step;
            this.delta = delta;
            this.gamma = gamma;
            this.b = b;
            this.c = c;
        }

        public void setGamma(double gamma) {
            this.gamma = gamma;
        }

        public double getGamma() {
            return gamma;
        }
    }

    public static Point2D.Double getNewPointWithNoise(Point2D.Double prev, RungeKuttaMethod.Params params) {
        double random1 = Math.random();
        double random2 = Math.random();
        double epsilon = 0.01;
        double r1 = Math.sqrt(-2 * Math.log(random1)) * Math.cos(2 * Math.PI * random2);
        double r2 = Math.sqrt(-2 * Math.log(random1)) * Math.sin(2 * Math.PI * random2);

        Point2D.Double newPoint = RungeKuttaMethod.getNewPoint(prev, params);

        double x = newPoint.x + epsilon * Math.sqrt(params.step) * r1 * prev.x;
        double y = newPoint.y + epsilon * Math.sqrt(params.step) * r2 * prev.y;

        return new Point2D.Double(Math.max(x, x), Math.max(y, y));
    }

    public static Point2D.Double getNewPoint(Point2D.Double prev, RungeKuttaMethod.Params params) {
        double f = prev.x * (1 - prev.x) - params.gamma * prev.x * prev.y / (prev.x + params.b);
        double g = params.delta * prev.y * (1 - prev.y / (prev.x + params.c));
        double K1 = params.step * f;
        double L1 = params.step * g;

        f = (prev.x + K1/2) * (1 - prev.x - K1/2) - params.gamma * (prev.x + K1/2) * (prev.y + L1/2) / (prev.x + K1/2 + params.b);
        g = params.delta * (prev.y + L1/2) * (1 - (prev.y + L1/2) / (prev.x + K1/2 + params.c));
        double K2 = params.step * f;
        double L2 = params.step * g;

        f = (prev.x + K2/2) * (1 - prev.x - K2/2) - params.gamma * (prev.x + K2/2) * (prev.y + L2/2) / (prev.x + K2/2 + params.b);
        g = params.delta * (prev.y + L2/2) * (1 - (prev.y + L2/2) / (prev.x + K2/2 + params.c));
        double K3 = params.step * f;
        double L3 = params.step * g;

        f = (prev.x + K3) * (1 - prev.x - K3) - params.gamma * (prev.x + K3) * (prev.y + L3) / (prev.x + K3 + params.b);
        g = params.delta * (prev.y + L3) * (1 - (prev.y + L3) / (prev.x + K3 + params.c));
        double K4 = params.step * f;
        double L4 = params.step * g;

        double x = prev.x + 1/6f * (K1 + 2*K2 + 2*K3 + K4);
        double y = prev.y + 1/6f * (L1 + 2*L2 + 2*L3 + L4);
        return new Point2D.Double(x, y);
    }
}
