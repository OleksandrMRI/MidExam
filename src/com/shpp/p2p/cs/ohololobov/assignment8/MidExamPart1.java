package com.shpp.p2p.cs.ohololobov.assignment8;

import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The program create animation:
 * Circles(snowFlakes) fallen down from top of canvas with sinusoid track und spin. Every circle has own random color, random track
 * with own random amplitude of deviation, own random velocity.
 */
public class MidExamPart1 extends WindowProgram {
    /**
     * instance of RandomGenerator is using in many methods
     */
    private static final RandomGenerator rgen = RandomGenerator.getInstance();
    /**
     * maximal value of diameter of SnowFlakes
     */
    private static final double MAX_DIAMETER = 40.0;
    /**
     * manimal value of diameter of SnowFlakes
     */
    private static final double MIN_DIAMETER = 10.0;
    /**
     * coefficient to increasing value of sinus, to increase swinging of SnowFlakes
     */
    private static final double SINUS_FUNCTION_INCREASE_COEFFICIENT = 10.0;
    /**
     * this coefficient change frequency an amplitude of swinging of SnowFlakes
     */
    private static final double X_SPEED_REPHASE_COEFFICIENT = 5.0;
    /**
     * value of pause duration
     */
    private static final double PAUSE_DURATION = 60;
    /**
     * maximal number of flakes that are creating/langth of array of SnowFlakes
     */
    private static final int NUM_FLAKES = 300;
    /**
     * value vertical velocity consist of two parts immutable velocity part and random mutable velocity part.
     * This is constant of immutable part
     */
    private static final double MIN_Y_VELOCITY = 1.0;
    /**
     * value vertical velocity consist of two parts immutable velocity part and random mutable velocity part.
     * This is constant of base random mutable velocity part
     */
    private static final double Y_VELOCITY_VARIABLE_PART_COEFFICIENT = 1.0;
    public static final int NUM_SCALE_ITERATIONS = 20;
    /**
     * variable for logic of wind imitation activation
     */
    private boolean isWind = false;
    /**
     * value of absolute wind velocity to manage shift velocity during wind imitation
     */
    private double windVelocity = 5;
    /**
     * variable for adding wind to horizontal velocity
     */
    private double windSpeed;

    /**
     * THe method adds mouse interactors and invokes the makeSnowFall() method, that contains logic of program
     */
    public void run() {
        addMouseListeners();
        makeSnowFall();
    }

    /**
     * The method contain logic of program:
     * creating array of SnowFlakes
     * and mowing of SnowFlakes
     */
    private void makeSnowFall() {
        Snowflake[] snowflakes = fillSnowFlakesArray();
        moveSnowflakes(snowflakes);
    }

    /**
     * The method create SnowFlakes, adds tay upper canvas and put they to array
     *
     * @return filled array with SnowFlakes
     */
    private Snowflake[] fillSnowFlakesArray() {
        Snowflake[] snowflakes = new Snowflake[NUM_FLAKES];

        for (int i = 0; i < snowflakes.length; i++) {
            snowflakes[i] = createSnowFlake();
            add(snowflakes[i]);
        }

        return snowflakes;
    }

    /**
     * THe method contains logic of moving, swirling snowflakes and recreating of fallen snowflakes
     *
     * @param snowflakes array with all snowflakes
     */
    private void moveSnowflakes(Snowflake[] snowflakes) {
        while (true) {

            for (int i = 0; i < snowflakes.length; i++) {
                Snowflake curentSnowflake = snowflakes[i];
                double currentSnowflakeOffsetY = curentSnowflake.getY();

                moveSnowflake(curentSnowflake, currentSnowflakeOffsetY);

                swirlSnowflake(curentSnowflake);
                reCreationFallenSnowflakes(snowflakes, i);
            }

            pause(PAUSE_DURATION);
        }
    }

    /**
     * The method contains logic of movement of one snowflake
     *
     * @param curentSnowflake current snowflake from array to move
     * @param currentOffsetY  coordinate y of current snowflake
     */
    private void moveSnowflake(Snowflake curentSnowflake, double currentOffsetY) {

        double nextOffsetY = currentOffsetY + curentSnowflake.getVY();
        double newOffsetX = curentSnowflake.getX() + Math.sin(nextOffsetY / SINUS_FUNCTION_INCREASE_COEFFICIENT)
                * X_SPEED_REPHASE_COEFFICIENT + windSpeed;

        curentSnowflake.setLocation(newOffsetX, nextOffsetY);
    }

    /**
     * The method includs logic of imitation of snowflake swirling, using method scale in x direction
     *
     * @param curentSnowflake current snowflake that is in processing
     */
    private static void swirlSnowflake(Snowflake curentSnowflake) {
        double scalingCoefficient = curentSnowflake.getScalingCoefficient();
        curentSnowflake.scale(curentSnowflake.getScalingCoefficient(), 1);

        if (curentSnowflake.incrementScalingCounter() % NUM_SCALE_ITERATIONS == 0) {
            curentSnowflake.setScalingCoefficient(1 / scalingCoefficient);
        }
    }

    /**
     * logic of snowflake re-creation, if it already lowers bottom of canvas
     *
     * @param snowflakes array of snowflake
     * @param i          index of current snowflake
     */
    private void reCreationFallenSnowflakes(Snowflake[] snowflakes, int i) {
        if (snowflakes[i].getY() >= getHeight()) {
            remove(snowflakes[i]);
            snowflakes[i] = createSnowFlake();
            add(snowflakes[i]);
        }
    }

    /**
     * Mouse click changes boolean variable isWind, that manages activation of wind imitation,
     * and change value of wind as positive? negative or zero
     *
     * @param l the event to be processed
     */
    public void mouseClicked(MouseEvent l) {
        isWind = !isWind;
        if (rgen.nextBoolean()) {
            windVelocity = -windVelocity;
        }
        windSpeed = getWindSpeed();
    }

    /**
     * method return value of wind according to value of boolean isWind
     *
     * @return value of wind speed
     */
    private double getWindSpeed() {
        if (isWind) {
            return windSpeed = windVelocity;
        }
        return 0;
    }

    /**
     * The method creates one instance of SnowFlake
     *
     * @return instance of SnowFlake
     */
    private Snowflake createSnowFlake() {
        double diameter = MAX_DIAMETER * rgen.nextDouble();
        if (diameter < MIN_DIAMETER) diameter = MIN_DIAMETER;
        double vY = MIN_Y_VELOCITY + Y_VELOCITY_VARIABLE_PART_COEFFICIENT * rgen.nextDouble();

        double offsetX = (getWidth() - diameter) * rgen.nextDouble();
        double offsetY = -getHeight() * rgen.nextDouble() - diameter;
        Color colorFlake = rgen.nextColor();

        return Snowflake.createSnowFlake(offsetX, offsetY, diameter, diameter, vY, colorFlake);
    }
}
