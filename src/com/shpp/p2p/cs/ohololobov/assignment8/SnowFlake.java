package com.shpp.p2p.cs.ohololobov.assignment8;

import acm.graphics.GOval;

import java.awt.*;

public class SnowFlake extends GOval {
    private double vY;
    private Color color;

    private SnowFlake(double offsetX, double offsetY, double diameter1, double diameter2, double vY, Color color){
        super(offsetX,offsetY,diameter1,diameter2);
        this.vY = vY;
        this.color = color;
    }

    public static SnowFlake createSnowFlake(double offsetX, double offsetY, double diameter1, double diameter2, double vY, Color color){
        SnowFlake snowFlake = new SnowFlake(offsetX,offsetY,diameter1,diameter2, vY,color);
        snowFlake.setColor(color);
        snowFlake.setFilled(true);

        return snowFlake;
    }

    public double getVY() {
        return vY;
    }

}
