package com.company.utils;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<Point2D.Double> reader(String fileName) {
        ArrayList<Point2D.Double> result = new ArrayList<>();
        try (
                FileReader fr = new FileReader(fileName)) {
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] point = line.split(" ");
                Point2D.Double pointOfCycle = new Point2D.Double(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
                result.add(pointOfCycle);
                line = reader.readLine();
            }
        } catch (
                IOException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
