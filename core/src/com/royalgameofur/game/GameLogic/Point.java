package com.royalgameofur.game.GameLogic;

import java.awt.geom.Point2D;

/**
 * Created by Kyle on 2/5/2018.
 */

public class Point {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(){
        this.x = 0;
        this.y = 0;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public boolean equals(Object var1) {
        if(!(var1 instanceof Point)) {
            return super.equals(var1);
        } else {
            Point var2 = (Point)var1;
            return this.getX() == var2.getX() && this.getY() == var2.getY();
        }
    }
}
