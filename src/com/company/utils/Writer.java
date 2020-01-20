package com.company.utils;

import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

    public static void write(ArrayList<Point2D.Double> result, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Point2D.Double e : result) {
                writer.write(e.x + " " + e.y + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
