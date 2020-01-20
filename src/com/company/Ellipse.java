package com.company;

import com.company.utils.Model;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Ellipse {
    public static void main(String[] args) {
        double gamma = 3.40;
        double x = Model.getX(gamma);
        double y = Model.getY(x);
        System.out.println(x);
        System.out.println(y);
        System.out.println(sq(x) + " " + sq(y));

        double a = 1 - 2 * x - 0.08 * gamma * y / ((x + 0.08) * (x + 0.08));
        double b = -gamma * x / (x + 0.08);
        double c = 0.2;
        double d = -0.2;

        double denominator = 2 * (a + d) * (a * d - b * c);
        double w1 = (-b * c * d * sq(x) - a * sq(b) * sq(y)) / (a * denominator) - sq(x)/(2 * a);
        double w2 = (a * b * sq(y) + c * d * sq(x)) / denominator;
        double w3 = (-sq(c) * d * sq(x) - a * b * c * sq(y)) / (d * denominator) - sq(y)/(2 * d);
        System.out.println("w1: " + w1 + " w2: " + w2 + " w3: " + w3);

//        w1 = 0.00229432026383291;
//        w2 = 0.00450567973616709;
//        w3 = 0.0477406548568146;
//
//        x = 0.1;
//        y = 1.4285714285714285;

        double discr = sq(w1 + w3) - 4 * (w1 * w3 - sq(w2));
        double lambda1 = (w1 + w3 - Math.sqrt(discr)) / 2;
        double lambda2 = (w1 + w3 + Math.sqrt(discr)) / 2;
        System.out.println("lambda1: " + lambda1 + " lambda2: " + lambda2);

        double v1Divisor = (Math.sqrt(sq(w2) + sq(lambda1 - w1)));
        double v11 = w2 / v1Divisor;
        double v12 = (lambda1 - w1) / v1Divisor;
        System.out.println("v11: " + v11 + " v12: " + v12);

        double v2Divisor = (Math.sqrt(sq(w2) + sq(lambda2 - w1)));
        double v21 = w2 / v2Divisor;
        double v22 = (lambda2 - w1) / v2Divisor;
        System.out.println("v21: " + v21 + " v22: " + v22);

        double epsilon = 0.0001;
        double q = Math.sqrt(-Math.log(1 - 0.95));

        double z1NoAngle = epsilon * q * Math.sqrt(2 * lambda1);
        double z2NoAngle = epsilon * q * Math.sqrt(2 * lambda2);
        double delta_divisor = v11 * v22 - v12 * v21;
        ArrayList<Point2D.Double> result = new ArrayList<>();
        for (int i = 0; i < 360; i++) {
            double radians = Math.toRadians(i);
            double z1 = z1NoAngle * Math.cos(radians);
            double z2 = z2NoAngle * Math.sin(radians);
            double eex = x + (z1 * v22 - z2 * v12) / delta_divisor;
            double eey = y + (z2 * v11 - z1 * v21) / delta_divisor;
            result.add(new Point2D.Double(
                    x + (z1 * v22 - z2 * v12) / delta_divisor,
                    y + (z2 * v11 - z1 * v21) / delta_divisor));
        }
        Writer.write(result, "out.txt");
    }

    private static double sq(double x) {
        return Math.pow(x, 2);
    }
}