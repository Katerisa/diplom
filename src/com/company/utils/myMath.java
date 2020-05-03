package com.company.utils;

public class myMath {
    public static double sq(double x) {
        return java.lang.Math.pow(x, 2);
    }

    public static double sqrt(double x) {
        return java.lang.Math.pow(x, 0.5);
    }

    public static double pythagoras(double x, double y) {
        return sqrt(sq(x) + sq(y));
    }
}

