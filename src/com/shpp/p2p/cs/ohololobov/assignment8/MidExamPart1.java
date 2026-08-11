package com.shpp.p2p.cs.ohololobov.assignment8;

import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MidExamPart1 extends WindowProgram {
    private static final RandomGenerator rgen = RandomGenerator.getInstance();
    private static final double MAX_DIAMETER = 30.0;
    private static final double MIN_DIAMETER = 5.0;
    public static final double SINUS_FUNCTION_COEFFICIENT = 10.0;
    public static final double X_SPEED_REPHASE_COEFFICIENT = 5.0;
    public static final double PAUSE_DURATION = 0.1;
    public static final int NUM_FLAKES = 30;
    public static final SnowFlake[] snowFlakes = new SnowFlake[NUM_FLAKES];
    public static final double MIN_Y_VELOCITY = 1.0;
    public static final double Y_VELOCITY_VARIABLE_PART_COEFFICIENT = 1.0;
    public static final int RETARDATION_FACTOR = 10;
    private SnowFlake snowFlake;
    private boolean isWind = false;
    private double windVelocity = 5;
    private double windSpeed;
    private double invertingScalingCoefficient = 0.1;
    private double scalingCoefficient=1;

    public void run() {
        addMouseListeners();
        makeSnowFall();
    }

    private void makeSnowFall() {
        for (int i = 0; i < snowFlakes.length; i++) {
            snowFlakes[i] = createSnowFlake();
            add(snowFlakes[i]);
            for (int j = 0; j < RETARDATION_FACTOR; j++) {
                moveSnowFlakes(snowFlakes);
            }
        }
        while (true) {
            moveSnowFlakes(snowFlakes);
        }
    }

    private void moveSnowFlakes(SnowFlake[] snowFlakes) {
        double snowFlakeOffsetY;
        double x;
//        scalingCoefficient -= invertingScalingCoefficient;
//        if(scalingCoefficient <0.3|| scalingCoefficient >0.9) invertingScalingCoefficient = -invertingScalingCoefficient;
        for (int i = 0; i < snowFlakes.length; i++) {
            if (snowFlakes[i] != null) {
                snowFlakeOffsetY = snowFlakes[i].getY();

                x = snowFlakes[i].getX() + Math.sin(snowFlakeOffsetY / SINUS_FUNCTION_COEFFICIENT) * X_SPEED_REPHASE_COEFFICIENT+windSpeed;
                snowFlakeOffsetY += snowFlakes[i].getVY();;
                snowFlakes[i].setLocation(x, snowFlakeOffsetY);
//                snowFlakes[i].scale(scalingCoefficient,1);

                if (snowFlakeOffsetY >= getHeight()) {
                    remove(snowFlakes[i]);
                    snowFlakes[i] = createSnowFlake();
                    add(snowFlakes[i]);
                } else {
                    pause(PAUSE_DURATION);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent l) {
        isWind = !isWind;
        if (rgen.nextBoolean()) {
            windVelocity = -windVelocity;
        }
        windSpeed = getWindSpeed();
    }

    private double getWindSpeed() {
        double windSpeed=0;
        if (isWind) {
            windSpeed = windVelocity;
        }
        return windSpeed;
    }

    private SnowFlake createSnowFlake() {
        double diameter = MAX_DIAMETER * rgen.nextDouble();
        if (diameter < MIN_DIAMETER) {
            diameter = MIN_DIAMETER;
        }
        double vY = MIN_Y_VELOCITY + Y_VELOCITY_VARIABLE_PART_COEFFICIENT * rgen.nextDouble();

        double offsetX = (getWidth() - diameter) * rgen.nextDouble();
        double offsetY = -diameter;
        Color colorFlake = rgen.nextColor();
        SnowFlake snowFlake = SnowFlake.createSnowFlake(offsetX, offsetY, diameter, diameter, vY, colorFlake);

        return snowFlake;
    }

    private void moveSnowFlake(SnowFlake snowFlake) {
        double snowFlakeOffsetY = snowFlake.getY();
        double startSnowFlakeOffsetX = snowFlake.getX();
        double snowFlakeVY = snowFlake.getVY();
        double windSpeed = 0;
        while (true) {
            windSpeed = getWindSpeed();
            startSnowFlakeOffsetX += windSpeed;
            double x = startSnowFlakeOffsetX + Math.sin(snowFlakeOffsetY / SINUS_FUNCTION_COEFFICIENT) * X_SPEED_REPHASE_COEFFICIENT;

            snowFlake.setLocation(x, snowFlakeOffsetY);
            snowFlakeOffsetY += snowFlakeVY;
            if (snowFlakeOffsetY >= getHeight()) {
                remove(snowFlake);
                snowFlake = createSnowFlake();
                add(snowFlake);
                snowFlakeOffsetY = snowFlake.getY();
                startSnowFlakeOffsetX = snowFlake.getX();
                snowFlakeVY = snowFlake.getVY();
            } else {
                pause(PAUSE_DURATION);
            }
        }
    }
}
