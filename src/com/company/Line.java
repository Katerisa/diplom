package com.company;

import java.awt.geom.Point2D;

import com.company.utils.Reader;
import com.company.utils.Writer;
import java.util.ArrayList;

public class Line {
    public static void main(String[] args) {
        ArrayList<Point2D.Double> cycle = Reader.reader("cycle.txt");
        ArrayList<Point2D.Double> m = Reader.reader("fts.txt");
        ArrayList<Point2D.Double> result = new ArrayList<>();
        int k = cycle.size();
        double gamma = 1;
        double epsilon = 0.0003;
        double q = 1.386;
        for (int i = 0; i < k; i++) {
            double x = cycle.get(i).x;
            double y = cycle.get(i).y;
            double f = x * (1 - x) - gamma * x * y / (x + 0.08);
            double g = 0.2 * y * (1 - y / (x + 0.01));
            double newX = x + q * epsilon * Math.sqrt(2 * m.get(i).y) * (-g / Math.sqrt(f*f + g*g)) * x;
            double newY = y + q * epsilon * Math.sqrt(2 * m.get(i).y) * f / Math.sqrt(f*f + g*g) * y;
            result.add(new Point2D.Double(newX, newY));
        }
        Writer.write(result, "out.txt");

    }
}
