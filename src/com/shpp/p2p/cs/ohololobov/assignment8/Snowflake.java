package com.shpp.p2p.cs.ohololobov.assignment8;

import acm.graphics.GOval;

import java.awt.*;

import static com.shpp.p2p.cs.ohololobov.assignment8.MidExamPart1.rgen;

/**
 * This class describe structure and logic of Snowflake. Snowflake extends GOval
 */
public class Snowflake extends GOval {
    public static final int TIMES_OF_SCALING = 10;
    private final double vY;
    private int scalingCounter;
    private double scalingCoefficient = 0.95;
    private double diameter;
    private int scalingLoop;

    /**
     * private constructor of class Snowflake
     *
     * @param offsetX            offset x coordinate
     * @param offsetY            offset y coordinate
     * @param horizontalDiameter horizontal diameter of Snowflake
     * @param verticalDiameter   vertical diameter of Snowflake
     * @param vY                 vertical velocity of snowflake fall
     */
    private Snowflake(double offsetX, double offsetY, double horizontalDiameter, double verticalDiameter, double vY) {
        super(offsetX, offsetY, horizontalDiameter, verticalDiameter);
        this.vY = vY;
        this.scalingCounter = 0;
        this.diameter = verticalDiameter;
        this.scalingLoop = TIMES_OF_SCALING + (int) (TIMES_OF_SCALING * rgen.nextDouble());
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
     * The method sets new value of scaling coefficient, when scaling direction must be changed
     *
     * @param scalingCoefficient coefficient of horizontal scaling of snowflake
     */
    public void setScalingCoefficient(double scalingCoefficient) {
        this.scalingCoefficient = scalingCoefficient;
    }

    /**
     * Getter for value of diameter
     *
     * @return double snowflake diameter
     */
    public double getDiameter() {
        return diameter;
    }

    public int getScalingLoop() {
        return scalingLoop;
    }
}
