package com.company;

import com.company.utils.Reader;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FTS {
    public static void main(String[] args) {
        ArrayList<Point2D.Double> cycle = Reader.reader("cycle.txt");
        ArrayList<Point2D.Double> result = new ArrayList<>();
        double step = 0.001;
        int k = cycle.size();
        double gamma = 1;
        ArrayList<Double> r = new ArrayList<>();
        r.add(1.0);
        ArrayList<Double> h = new ArrayList<>();
        h.add(0.0);
        ArrayList<Point2D.Double> aList = new ArrayList<>();
        ArrayList<Double> b = new ArrayList<>();
        for (int i = 1; i < k; i++) {
            double x = cycle.get(i).x;
            double y = cycle.get(i).y;
            double f = x * (1 - x) - gamma * x * y / (x + 0.08);
            double g = 0.2 * y * (1 - y / (x + 0.01));
            double fx = 1 - 2 * x - 0.08 * gamma * y / Math.pow(x + 0.08, 2);
            double fy = - gamma * x / (x + 0.08);
            double gx = 0.02 * y * y / Math.pow(x + 0.01, 2);
            double gy = 0.02 - 0.04 * y / (x + 0.01);
            Point2D.Double p = new Point2D.Double(-g / Math.sqrt(f*f + g*g), f / Math.sqrt(f*f + g*g));
            aList.add(new Point2D.Double(i * step,p.x * (p.x * 2 * fx + p.y * (gx + fy)) + p.y * (p.x * (fy + gx) + p.y * 2 * gy)));
            r.add(r.get(i-1) * Math.pow(Math.E, aList.get(i-1).y * step));
            b.add(p.x * p.x * x * x + p.y * p.y * y * y);
            h.add(h.get(i-1) + b.get(i) / r.get(i) * step);
        }
        double C = r.get(k - 1) * h.get(k-1) / (1 - r.get(k-1));
        for (int i = 0; i < k; i++) {
            result.add(new Point2D.Double((i + 1) * step, r.get(i) * (C + h.get(i))));
        }
        Writer.write(result, "fts.txt");
    }
}
