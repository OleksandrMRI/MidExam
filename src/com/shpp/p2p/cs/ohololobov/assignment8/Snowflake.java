package com.shpp.p2p.cs.ohololobov.assignment8;

import acm.graphics.GOval;

import java.awt.*;

/**
 * This class describe structure and logic of Snowflake. Snowflake extends GOval
 */
public class Snowflake extends GOval {
    private final double vY;
    private int scalingCounter;
    private double scalingCoefficient = 0.95;

    /**
     * private constructor of class Snowflake
     *
     * @param offsetX   offset x coordinate
     * @param offsetY   offset y coordinate
     * @param diameter1 horizontal diameter of Snowflake
     * @param diameter2 vertical diameter of Snowflake
     * @param vY        vertical velocity of snowflake fall
     */
    private Snowflake(double offsetX, double offsetY, double diameter1, double diameter2, double vY) {
        super(offsetX, offsetY, diameter1, diameter2);
        this.vY = vY;
        this.scalingCounter = 0;
    }

    /**
     * The method create instance of class Snowflake
     *
     * @param offsetX   offset x coordinate
     * @param offsetY   offset y coordinate
     * @param diameter1 horizontal diameter of Snowflake
     * @param diameter2 vertical diameter of Snowflake
     * @param vY        vertical velocity of snowflake fall
     * @param color     color of Snowflake instance
     * @return instance of Snowflake
     */
    public static Snowflake createSnowFlake(double offsetX, double offsetY, double diameter1, double diameter2, double vY, Color color) {
        Snowflake snowFlake = new Snowflake(offsetX, offsetY, diameter1, diameter2, vY);
        snowFlake.setColor(color);
        snowFlake.setFilled(true);
        return snowFlake;
    }

    /**
     * getter for snowflake vertical velocity
     *
     * @return double snowflake vertical velocity
     */
    public double getVY() {
        return vY;
    }

    /**
     * The method increments counter of times of scaling
     *
     * @return int counter of times of scaling to control of reverse scaling
     */
    public int incrementScalingCounter() {
        return ++scalingCounter;
    }

    /**
     * scaling is individual in time for any instance of Snowflakes and must be received every time, when scaling direction changed
     *
     * @return double scaling coefficient
     */
    public double getScalingCoefficient() {
        return scalingCoefficient;
    }

    /**
     * The method sets new value of scaling coefficient, when scaling dirrection must be changed
     *
     * @param scalingCoefficient coefficient of horizontal scaling of snowflake
     */
    public void setScalingCoefficient(double scalingCoefficient) {
        this.scalingCoefficient = scalingCoefficient;
    }
}
