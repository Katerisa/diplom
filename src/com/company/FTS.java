package com.company;

import com.company.utils.Reader;
import com.company.utils.RungeKuttaMethod;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static com.company.utils.Model.getModelX;
import static com.company.utils.Model.getModelY;
import static com.company.utils.myMath.pythagoras;
import static com.company.utils.myMath.sq;

public class FTS {
    public static void main(String[] args) {
        ArrayList<Point2D.Double> cycle = Reader.reader("cycle.txt");
        double step = 0.001;
        double gamma = 2;
        RungeKuttaMethod.Params params = new RungeKuttaMethod(). new Params(step, 0.2, gamma, 0.08, 0.01);
        Writer.write(plot(step, params, cycle), "fts.txt");
    }

    static ArrayList<Point2D.Double> plot(double step, RungeKuttaMethod.Params params, ArrayList<Point2D.Double> cycle) {
        ArrayList<Point2D.Double> result = new ArrayList<>();
        int k = cycle.size();
        ArrayList<Double> r = new ArrayList<>();
        r.add(1.0);
        ArrayList<Double> h = new ArrayList<>();
        h.add(0.0);
        for (int i = 1; i < k; i++) {
            double x = cycle.get(i).x;
            double y = cycle.get(i).y;
            double f = getModelX(params, x, y);
            double g = getModelY(params, x, y);
            double fx = 1 - 2 * x - 0.08 * params.getGamma() * y / Math.pow(x + 0.08, 2);
            double fy = - params.getGamma() * x / (x + 0.08);
            double gx = 0.2 * y * y / Math.pow(x + 0.01, 2);
            double gy = 0.2 - 0.4 * y / (x + 0.01);
            double divisor = pythagoras(f, g);
            Point2D.Double p = new Point2D.Double(-g / divisor, f / divisor);
            double a = p.x * (p.x * 2 * fx + p.y * (gx + fy)) +
                    p.y * (p.y * 2 * gy + p.x * (gx + fy));
            r.add(last(r) * Math.pow(Math.E, a * step));
            double b = sq(p.x) * sq(x) + sq(p.y) * sq(y);
            h.add(last(h) + b / last(r) * step);
        }
        double C = last(r) * last(h) / (1 - last(r));
        for (int i = 0; i < k; i++) {
            result.add(new Point2D.Double((i + 1) * step, r.get(i) * (C + h.get(i))));
        }
        return result;
    }

    private static <E> E last(List<E> list) {
        return list.get(list.size() - 1);
    }
}
