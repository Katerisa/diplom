package com.company;

import com.company.utils.Model;
import com.company.utils.Writer;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        double n = 200.0;
        double gamma;
        ArrayList<Point2D.Double> result = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            gamma = (i-1) * (5/n);
            Point2D.Double current = new Point2D.Double(gamma, Model.getStationaryY(Model.getStationaryX(gamma)));
            result.add(current);
        }
        Writer.write(result, "out.txt");
    }
}

