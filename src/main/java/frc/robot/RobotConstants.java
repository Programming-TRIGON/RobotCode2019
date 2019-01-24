package frc.robot;

/** a class used to store constants related to the robot */
public class RobotConstants {
    /** The measurments of the robot */
    public static class RobotDimensions {
        /**
         * The diameter of the tube in the lift which twists the rope used to change
         * ticks to meters.
         */

        public final static Double LIFT_TUBE_DIAMETER = 4.347;

    }

    /** constants for sensors on the robot */
    public static class Sensors {
        /**
         * The Encoder uses ticks to measure movement. 4 tick = 1 pulse. PPF stands for
         * "pulse per revolution" meaning the amount of pulses the Encoder measures
         * every turn of a circle. this is used to convert ticks to meters.
         */
        public final static Double ENCODER_PPR = 1440.0;
    }
}
