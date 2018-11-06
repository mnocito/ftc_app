package org.firstinspires.ftc.teamcode.misc;

public class RobotConstants {
    // max range for HS-7955TG is 750-2250uS, so .125-.875
    public static double NOMSERVO_UP = .85;
    public static double NOMSERVO_DOWN = .1;
    public static double NOMSERVO_NEUTRAL = .35;
    public static double MARKERSERVO_DROP = .7;
    public static double MARKERSERVO_HOLD = .3;
    public static double LOWEST_MOTOR_POWER = .2;
    public static double LOWEST_STRAFE_POWER = .3;
    public static double LOWEST_TURN_POWER = .15;
    public static double threshold = .5;
    public static double sensitivity = .9;
    public enum Position {
        LEFT, RIGHT, CENTER
    }
}